package com.dev.jaskiewicz.mobilephones.ui.edit;


import android.app.Fragment;

import com.dev.jaskiewicz.mobilephones.R;
import com.dev.jaskiewicz.mobilephones.ui.BasePhoneWindow;

public class EditPhoneWindow extends BasePhoneWindow {
    @Override
    protected String getWindowTitle() {
        return getString(R.string.edit_phone);
    }

    @Override
    protected Fragment prepareFragmentForThisWindow() {
        return new EditPhoneFragment();
    }
}
