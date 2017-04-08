package com.dev.jaskiewicz.mobilephones.ui.add;

import com.dev.jaskiewicz.mobilephones.data.MobilesContract;
import com.dev.jaskiewicz.mobilephones.ui.AddOrEditMobilePhoneFragment;

public class AddMobilePhoneFragment extends AddOrEditMobilePhoneFragment {
    @Override
    protected void savePhoneInDatabase() {
        addMobilePhoneToDatabase();
    }

    private void addMobilePhoneToDatabase() {
        getActivity().getContentResolver().
                insert(MobilesContract.CONTENT_URI, prepareMobilePhonesDataToSaveInDatabase());
    }

}
