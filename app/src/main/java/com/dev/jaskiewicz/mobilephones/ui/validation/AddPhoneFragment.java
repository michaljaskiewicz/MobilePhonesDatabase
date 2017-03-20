package com.dev.jaskiewicz.mobilephones.ui.validation;


import android.net.Uri;
import android.widget.Toast;

import com.dev.jaskiewicz.mobilephones.data.MobilesContract;
import com.dev.jaskiewicz.mobilephones.ui.PhoneFragment;

public class AddPhoneFragment extends PhoneFragment {

    @Override
    protected void savePhoneInDatabase() {
        Uri uriOfTheInsertedRow = getActivity().getContentResolver().insert(MobilesContract.CONTENT_URI, getPhonesData());

        // TODO
        // DELETE THIS TOAST
        // FOR TEST ONLY
        Toast.makeText(getActivity(), uriOfTheInsertedRow.toString(), Toast.LENGTH_SHORT).show();
    }

}
