package com.dev.jaskiewicz.mobilephones.ui.edit;

import android.os.Bundle;
import android.widget.Toast;

import com.dev.jaskiewicz.mobilephones.ui.AddOrEditPhoneFragment;

import static com.dev.jaskiewicz.mobilephones.ui.mainWindow.MobilesWindow.ID_OF_MOBILE_PHONE_CHOSEN_FOR_UPDATE;

public class EditPhoneFragment extends AddOrEditPhoneFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        long phoneId = getArguments().getLong(ID_OF_MOBILE_PHONE_CHOSEN_FOR_UPDATE);
        Toast.makeText(getActivity(), "Okno edycji dla telefonu o id: " + phoneId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void savePhoneInDatabase() {
        // TODO updatePhoneInDatabase();
    }


}
