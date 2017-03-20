package com.dev.jaskiewicz.mobilephones.ui.add;

import android.app.Fragment;

import com.dev.jaskiewicz.mobilephones.ui.BaseWindow;

public class AddPhoneWindow extends BaseWindow {
    @Override
    protected String getWindowTitle() {
        return "Dodaj telefon";
    }

    @Override
    protected Fragment prepareFragmentForThisWindow() {
        return new AddPhoneFragment();
    }

}
