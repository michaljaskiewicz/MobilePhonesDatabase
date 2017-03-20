package com.dev.jaskiewicz.mobilephones.ui;

import android.os.Bundle;

import com.dev.jaskiewicz.mobilephones.ui.validation.AddPhoneFragment;

public class AddPhoneWindow extends BaseWindow {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // nie ustawiam layoutu, ponieważ korzystam z układu dla BaseWindow
        if (savedInstanceState == null) {
            createFragment();
        }
    }

    private void createFragment() {
        getFragmentManager()
                .beginTransaction()
                .replace(getFragmentContainerId(), new AddPhoneFragment())
                .commit();
    }

    @Override
    protected String getWindowTitle() {
        return "Dodaj telefon";
    }

}
