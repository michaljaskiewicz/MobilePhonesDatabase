package com.dev.jaskiewicz.mobilephones.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.dev.jaskiewicz.mobilephones.data.database.MobilesDatabaseHelper;
import com.dev.jaskiewicz.mobilephones.data.database.MobilesTable;

import static com.dev.jaskiewicz.mobilephones.data.MobilesContract.AUTHORITY;
import static com.dev.jaskiewicz.mobilephones.data.MobilesContract.MOBILES_CODE;
import static com.dev.jaskiewicz.mobilephones.data.MobilesContract.PATH_TO_MOBILES;
import static com.dev.jaskiewicz.mobilephones.data.MobilesContract.MOBILE_ID_CODE;
import static com.dev.jaskiewicz.mobilephones.data.MobilesContract.PATH_TO_MOBILE_PHONE_SPECIFIED_BY_ID;
import static com.dev.jaskiewicz.mobilephones.data.database.MobilesTable.COLUMN_ID;
import static com.dev.jaskiewicz.mobilephones.data.database.SQLQueryHelper.SELECT_MOBILE_PHONE_BASED_ON_ID;

public class MobilesProvider extends ContentProvider {
    /* value from documentation */
    private static final int ID_RETURNED_WHEN_INSERT_ERROR_OCCURRED = -1;
    private static final int NUMBER_OF_SEGMENT_THAT_CONTAINS_ID = 1;
    private static final UriMatcher uriMatcher = buildUriMatcher();

    private MobilesDatabaseHelper databaseHelper;
    private long insertedId;
    private int amountOfDeletedMobiles;
    private int updatedMobilesAmount;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, PATH_TO_MOBILES, MOBILES_CODE);
        matcher.addURI(AUTHORITY, PATH_TO_MOBILE_PHONE_SPECIFIED_BY_ID, MOBILE_ID_CODE);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        createDatabaseHelper();
        return true;
    }

    private void createDatabaseHelper() {
        databaseHelper = new MobilesDatabaseHelper(getContext());
    }

    @Override
    public Cursor query(
            @NonNull Uri uri,
            String[] projection,
            String selection,
            String[] selectionArgs,
            String sortOrder) {

        final SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor receivedData = null;

        if (matchToMobileId(uri)) {
            receivedData = queryForOneMobilePhone(uri, db);
        } else if (matchToMobiles(uri)) {
            receivedData = db.query(
                    MobilesTable.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder);
        } else {
            throwNoMatchExceptionFor(uri);
        }
        receivedData.setNotificationUri(getContext().getContentResolver(), uri);

        return receivedData;
    }

    private boolean matchToMobileId(Uri uri) {
        return uriMatcher.match(uri) == MOBILE_ID_CODE;
    }

    /**
     * @param uri - określa rekord do pobrania z bazy
     * @param db - referencja do bazy danych, z której należy pobrać rekord
     * @return pobrane rekordy w postaci cursora
     */
    private Cursor queryForOneMobilePhone(@NonNull Uri uri, SQLiteDatabase db) {
        Cursor receivedData;
        receivedData = db.rawQuery(SELECT_MOBILE_PHONE_BASED_ON_ID, prepareIdSelectionArgsBasedOn(uri));
        return receivedData;
    }

    private String[] prepareIdSelectionArgsBasedOn(@NonNull Uri uri) {
        return new String[] {
                getIdFrom(uri)
        };
    }

    private String getIdFrom(@NonNull Uri uri) {
        return uri.getPathSegments().get(NUMBER_OF_SEGMENT_THAT_CONTAINS_ID);
    }

    private void throwNoMatchExceptionFor(Uri uri) {
        throw new UnsupportedOperationException("No match. Unknown uri " + uri);
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        clearIdOfInsertedRowFromPreviousOperation();
        if (matchToMobiles(uri)) {
            insertToMobilesTable(values);
            throwSQLExceptionIfInsertFailed(uri);
        } else {
            throwNoMatchExceptionFor(uri);
        }
         /*Dokumentacja mówi, aby wywołać tą metodę po insercie*/
        getContext().getContentResolver().notifyChange(uri, null);
        return constructUriThatPointsToInsertedRow();
    }

    private void clearIdOfInsertedRowFromPreviousOperation() {
        insertedId = 0;
    }

    private boolean matchToMobiles(Uri uri) {
        return uriMatcher.match(uri) == MOBILES_CODE;
    }

    private void insertToMobilesTable(ContentValues values) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        insertedId = db.insert(MobilesTable.TABLE_NAME, null, values);
    }

    private void throwSQLExceptionIfInsertFailed(Uri uri) {
        if (insertErrorOccurred()) {
            throw new SQLException("Failed to insert row into " + uri);
        }
    }

    private boolean insertErrorOccurred() {
        return insertedId == ID_RETURNED_WHEN_INSERT_ERROR_OCCURRED;
    }

    private Uri constructUriThatPointsToInsertedRow() {
        return ContentUris.withAppendedId(MobilesContract.CONTENT_URI, insertedId);
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        clearAmountOfDeletedMobilesFromPreviousOperation();
        if (matchToMobileId(uri)) {
            deleteMobilePhone(getIdFrom(uri));
        } else {
            throwNoMatchExceptionFor(uri);
        }
        notifyChangeIfAnyMobilePhoneWasDeleted(uri);
        return amountOfDeletedMobiles;
    }

    private void clearAmountOfDeletedMobilesFromPreviousOperation() {
        amountOfDeletedMobiles = 0;
    }


    private void deleteMobilePhone(String mobilePhoneId) {
        final SQLiteDatabase db = databaseHelper.getWritableDatabase();
        amountOfDeletedMobiles = db.delete(MobilesTable.TABLE_NAME, COLUMN_ID + "=?", new String[]{mobilePhoneId});
    }

    private void notifyChangeIfAnyMobilePhoneWasDeleted(Uri uri) {
        if (amountOfDeletedMobiles > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
    }

    /**
     * Zaimplementowany został jedynie przypadek, gdy aktualizowany jest pojedynczy telefon
     */
    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        clearUpdatedMobilesAmountFromPreviousOperation();
        if(matchToMobileId(uri)) {
            updateMobilePhoneWithValues(getIdFrom(uri), values);
        } else {
            throwNoMatchExceptionFor(uri);
        }
        notifyChangeIfAnyMobilePhoneWasUpdated(uri);
        return updatedMobilesAmount;
    }

    private void notifyChangeIfAnyMobilePhoneWasUpdated(@NonNull Uri uri) {
        if (updatedMobilesAmount > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
    }

    private void clearUpdatedMobilesAmountFromPreviousOperation() {
        updatedMobilesAmount = 0;
    }

    private void updateMobilePhoneWithValues(String mobilePhoneId, ContentValues values) {
        final SQLiteDatabase db = databaseHelper.getWritableDatabase();
        updatedMobilesAmount = db.update(
                MobilesTable.TABLE_NAME,
                values,
                COLUMN_ID + "=?",
                new String[]{mobilePhoneId});
    }
}
