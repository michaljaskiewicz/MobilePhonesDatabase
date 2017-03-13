package com.dev.jaskiewicz.mobilephones.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dev.jaskiewicz.mobilephones.R;

public class AddPhoneWindow extends AppCompatActivity {

    //TODO otrzymywać w Bundlu tytuł okna "Edytuj telefon" lub "Dodaj telefon" w zależności od tego, z którego miejsca zostało otwarte okno

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_phone_window);
        setUpToolbar();
    }

    private void setUpToolbar() {
        findToolbar();

        if (toolbar != null) {
            toolbar.setTitle(R.string.add_phone);
            setSupportActionBar(toolbar);
        }
    }

    private void findToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }
}
