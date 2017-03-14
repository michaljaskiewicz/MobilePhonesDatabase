package com.dev.jaskiewicz.mobilephones.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dev.jaskiewicz.mobilephones.R;

import static com.dev.jaskiewicz.mobilephones.ui.MobilesWindow.TITLE_FOR_ADD_OR_EDIT_PHONE_WINDOW;

public class AddOrEditPhoneWindow extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_or_edit_phone_window);
        setUpToolbar();
    }

    private void setUpToolbar() {
        findToolbar();

        if (toolbar != null) {
            setTitle();
            setSupportActionBar(toolbar);
        }
    }

    private void findToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void setTitle() {
        String title = getTitleFromMobilesWindow();
        toolbar.setTitle(title);
    }

    private String getTitleFromMobilesWindow() {
        return getIntent().getStringExtra(TITLE_FOR_ADD_OR_EDIT_PHONE_WINDOW);
    }
}
