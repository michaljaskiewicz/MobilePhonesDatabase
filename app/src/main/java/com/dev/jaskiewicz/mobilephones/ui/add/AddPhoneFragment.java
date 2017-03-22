package com.dev.jaskiewicz.mobilephones.ui.add;

import com.dev.jaskiewicz.mobilephones.data.MobilesContract;
import com.dev.jaskiewicz.mobilephones.ui.AddOrEditPhoneFragment;

public class AddPhoneFragment extends AddOrEditPhoneFragment {
    @Override
    protected void savePhoneInDatabase() {
        getActivity().getContentResolver().
                insert(MobilesContract.CONTENT_URI, getPhonesData());
    }

}
