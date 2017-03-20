package com.dev.jaskiewicz.mobilephones.ui.add;


import android.net.Uri;
import android.widget.Toast;

import com.dev.jaskiewicz.mobilephones.data.MobilesContract;
import com.dev.jaskiewicz.mobilephones.ui.AddOrEditPhoneFragment;

public class AddPhoneFragment extends AddOrEditPhoneFragment {

    @Override
    protected void savePhoneInDatabase() {
        Uri uriOfTheInsertedRow = getActivity().getContentResolver().insert(MobilesContract.CONTENT_URI, getPhonesData());

        // TODO
        // DELETE THIS TOAST
        // FOR TEST ONLY
        Toast.makeText(getActivity(), uriOfTheInsertedRow.toString(), Toast.LENGTH_SHORT).show();
    }

}
