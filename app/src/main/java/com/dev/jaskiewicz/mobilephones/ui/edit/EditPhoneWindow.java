package com.dev.jaskiewicz.mobilephones.ui.edit;

import android.app.Fragment;
import android.os.Bundle;

import com.dev.jaskiewicz.mobilephones.R;
import com.dev.jaskiewicz.mobilephones.ui.BasePhoneWindow;

public class EditPhoneWindow extends BasePhoneWindow {

    @Override
    protected String getWindowTitle() {
        return getString(R.string.edit_phone);
    }

    @Override
    protected Fragment prepareFragmentForThisWindow() {
        final EditPhoneFragment editPhoneFragment = new EditPhoneFragment();
        editPhoneFragment.setArguments(getBundleWithPhoneId());
        return editPhoneFragment;
    }

    private Bundle getBundleWithPhoneId() {
        return getIntent().getExtras();
    }
}
