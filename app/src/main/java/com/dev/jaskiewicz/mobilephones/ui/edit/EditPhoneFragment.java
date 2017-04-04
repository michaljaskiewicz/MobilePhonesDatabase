package com.dev.jaskiewicz.mobilephones.ui.edit;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.dev.jaskiewicz.mobilephones.data.model.MobilePhone;
import com.dev.jaskiewicz.mobilephones.data.MobilesContract;
import com.dev.jaskiewicz.mobilephones.ui.AddOrEditPhoneFragment;

import static com.dev.jaskiewicz.mobilephones.data.database.MobilesTable.COLUMN_ANDROID_VERSION;
import static com.dev.jaskiewicz.mobilephones.data.database.MobilesTable.COLUMN_ID;
import static com.dev.jaskiewicz.mobilephones.data.database.MobilesTable.COLUMN_MODEL;
import static com.dev.jaskiewicz.mobilephones.data.database.MobilesTable.COLUMN_PRODUCER;
import static com.dev.jaskiewicz.mobilephones.data.database.MobilesTable.COLUMN_WWW;
import static com.dev.jaskiewicz.mobilephones.ui.mainWindow.MobilesWindow.ID_OF_MOBILE_PHONE_CHOSEN_FOR_UPDATE;

public class EditPhoneFragment extends AddOrEditPhoneFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MobilePhone phoneToEdit = getMobilePhoneToEdit();
        updateGUIWith(phoneToEdit);
    }

    private MobilePhone getMobilePhoneToEdit() {
        MobilePhone phone = null;
        long phoneId = getIdOfMobilePhoneToEdit();
        Cursor retrievedData = queryForMobilePhoneData(phoneId);
        if (containsOnlyOneMobilePhone(retrievedData)) {
            phone = createMobilePhoneFrom(retrievedData);
        }
        return phone;
    }

    private long getIdOfMobilePhoneToEdit() {
        return getArguments().getLong(ID_OF_MOBILE_PHONE_CHOSEN_FOR_UPDATE);
    }

    private MobilePhone createMobilePhoneFrom(Cursor mobilePhoneData) {
        // Przesuwam kursor na pierwszą pozycję w celu odczytania jedynego zwróconego wiersza danych telefonu
        mobilePhoneData.moveToFirst();

        int id = mobilePhoneData.getInt(mobilePhoneData.getColumnIndex(COLUMN_ID));
        String producer = mobilePhoneData.getString(mobilePhoneData.getColumnIndex(COLUMN_PRODUCER));
        String model = mobilePhoneData.getString(mobilePhoneData.getColumnIndex(COLUMN_MODEL));
        String androidVersion = mobilePhoneData.getString(mobilePhoneData.getColumnIndex(COLUMN_ANDROID_VERSION));
        String www = mobilePhoneData.getString(mobilePhoneData.getColumnIndex(COLUMN_WWW));

        return new MobilePhone(id, producer, model, androidVersion, www);
    }

    private boolean containsOnlyOneMobilePhone(Cursor cursor) {
        return cursor != null && cursor.getCount() == 1;
    }

    private Cursor queryForMobilePhoneData(long phoneId) {
        return getActivity().getContentResolver()
                .query(prepareUriForMobilePhoneWith(phoneId), null, null, null, null);
    }

    private Uri prepareUriForMobilePhoneWith(long phoneId) {
        return ContentUris.withAppendedId(MobilesContract.CONTENT_URI, phoneId);
    }

    private void updateGUIWith(MobilePhone phoneToEdit) {
        assignProducer(phoneToEdit.getProducer());
        assignModel(phoneToEdit.getModel());
        assignAndroidVersion(phoneToEdit.getAndroidVersion());
        assignUrl(phoneToEdit.getWWW());
    }

    @Override
    protected void savePhoneInDatabase() {
        // TODO updatePhoneInDatabase();
    }


}
