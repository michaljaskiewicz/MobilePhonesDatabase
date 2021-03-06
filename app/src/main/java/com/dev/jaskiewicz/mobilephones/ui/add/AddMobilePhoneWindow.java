package com.dev.jaskiewicz.mobilephones.ui.add;

import android.app.Fragment;

import com.dev.jaskiewicz.mobilephones.ui.BasePhoneWindow;

public class AddMobilePhoneWindow extends BasePhoneWindow {
    @Override
    protected String getWindowTitle() {
        return "Dodaj telefon";
    }

    @Override
    protected Fragment prepareFragmentForThisWindow() {
        return new AddMobilePhoneFragment();
    }
}
