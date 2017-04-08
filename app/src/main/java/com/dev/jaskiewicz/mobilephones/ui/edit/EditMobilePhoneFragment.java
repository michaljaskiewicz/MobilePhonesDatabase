package com.dev.jaskiewicz.mobilephones.ui.edit;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.dev.jaskiewicz.mobilephones.R;
import com.dev.jaskiewicz.mobilephones.data.model.MobilePhone;
import com.dev.jaskiewicz.mobilephones.data.MobilesContract;
import com.dev.jaskiewicz.mobilephones.ui.AddOrEditMobilePhoneFragment;

import static com.dev.jaskiewicz.mobilephones.data.database.MobilesTable.COLUMN_ANDROID_VERSION;
import static com.dev.jaskiewicz.mobilephones.data.database.MobilesTable.COLUMN_ID;
import static com.dev.jaskiewicz.mobilephones.data.database.MobilesTable.COLUMN_MODEL;
import static com.dev.jaskiewicz.mobilephones.data.database.MobilesTable.COLUMN_PRODUCER;
import static com.dev.jaskiewicz.mobilephones.data.database.MobilesTable.COLUMN_WWW;
import static com.dev.jaskiewicz.mobilephones.ui.mainWindow.MobilesWindow.ID_OF_MOBILE_PHONE_CHOSEN_FOR_UPDATE;

public class EditMobilePhoneFragment extends AddOrEditMobilePhoneFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int FIRST_LOADER_ID = 0;

    /**
     * Używam Loadera do pobrania danych telefonu tylko przy pierwszym utworzeniu okna
     *
     * Przy kolejnych przebudowach okna (zmiany konfiguracji urządzenia)
     * nie potrzebuję pobierać tych danych, bo są one przechowywane w polach editText
     *
     * @param savedInstanceState określa czy aktywność tworzona jest po raz pierwszy
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            useLoaderToGetMobilePhoneToEdit();
        }
    }

    private void useLoaderToGetMobilePhoneToEdit() {
        initLoader();
    }

    private void initLoader() {
        getLoaderManager().initLoader(FIRST_LOADER_ID, null, this);
    }

    /**
     * Metoda wywoływana, gdy przycisk "Zapisz" został kliknięty oraz jeśli wprowadzone dane są prawidłowe
     * Określa jaka operacja zapisu do bazy danych ma zostać wykonana
     * W tym przypadku edytowany jest telefon z bazy danych
     */
    @Override
    protected void savePhoneInDatabase() {
        editMobilePhoneFromDatabase();
    }

    private void editMobilePhoneFromDatabase() {
        getActivity().getContentResolver()
                .update(prepareUriForMobilePhoneWith(getIdOfMobilePhoneToEdit()),
                        prepareMobilePhonesDataToSaveInDatabase(),
                        null, null);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        long phoneId = getIdOfMobilePhoneToEdit();
        return new CursorLoader(
                getActivity(),
                prepareUriForMobilePhoneWith(phoneId),
                null,
                null,
                null,
                null);
    }

    private long getIdOfMobilePhoneToEdit() {
        return getArguments().getLong(ID_OF_MOBILE_PHONE_CHOSEN_FOR_UPDATE);
    }

    private Uri prepareUriForMobilePhoneWith(long phoneId) {
        return ContentUris.withAppendedId(MobilesContract.CONTENT_URI, phoneId);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor retrievedData) {
        if (containsOnlyOneMobilePhone(retrievedData)) {
            updateGUIWith(retrievedData);
        } else {
            showErrorMessage();
        }
    }

    private boolean containsOnlyOneMobilePhone(Cursor cursor) {
        return cursor != null && cursor.getCount() == 1;
    }

    private void updateGUIWith(Cursor retrievedData) {
        updateGUIWithMobilePhone(extractMobilePhoneFrom(retrievedData));
    }

    private MobilePhone extractMobilePhoneFrom(Cursor mobilePhoneData) {
        // Przesuwam kursor na pierwszą pozycję w celu odczytania jedynego zwróconego wiersza danych telefonu
        mobilePhoneData.moveToFirst();
        int id = mobilePhoneData.getInt(mobilePhoneData.getColumnIndex(COLUMN_ID));
        String producer = mobilePhoneData.getString(mobilePhoneData.getColumnIndex(COLUMN_PRODUCER));
        String model = mobilePhoneData.getString(mobilePhoneData.getColumnIndex(COLUMN_MODEL));
        String androidVersion = mobilePhoneData.getString(mobilePhoneData.getColumnIndex(COLUMN_ANDROID_VERSION));
        String www = mobilePhoneData.getString(mobilePhoneData.getColumnIndex(COLUMN_WWW));
        return new MobilePhone(id, producer, model, androidVersion, www);
    }

    private void updateGUIWithMobilePhone(MobilePhone phoneToEdit) {
        assignProducerToEditText(phoneToEdit.getProducer());
        assignModelToEditText(phoneToEdit.getModel());
        assignAndroidVersionToEditText(phoneToEdit.getAndroidVersion());
        assignUrlToEditText(phoneToEdit.getWWW());
    }

    private void showErrorMessage() {
        Toast.makeText(getActivity(), R.string.error_no_phone_to_edit, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // nie potrzebuję implementacji taj metody
    }
}
