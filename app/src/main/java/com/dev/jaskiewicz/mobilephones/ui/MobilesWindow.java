package com.dev.jaskiewicz.mobilephones.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dev.jaskiewicz.mobilephones.R;

public class MobilesWindow extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobiles_window);
        findToolbar();
        setSupportActionBar(toolbar);
    }

    private void findToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (isActionAddMobilePhone(item)) {
            Toast.makeText(this, "Add phone", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isActionAddMobilePhone(MenuItem item) {
        return item.getItemId() == R.id.action_add_mobile_phone;
    }
}
