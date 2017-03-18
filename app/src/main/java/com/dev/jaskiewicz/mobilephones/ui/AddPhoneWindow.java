package com.dev.jaskiewicz.mobilephones.ui;

import android.os.Bundle;

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
                .add(getFragmentContainerId(), new AddPhoneFragment())
                .commit();
    }

    @Override
    protected String getWindowTitle() {
        return "Dodaj telefon";
    }

}
