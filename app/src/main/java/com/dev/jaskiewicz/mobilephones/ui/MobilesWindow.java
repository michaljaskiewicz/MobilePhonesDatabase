package com.dev.jaskiewicz.mobilephones.ui;

import android.app.ListFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dev.jaskiewicz.mobilephones.R;

public class MobilesWindow extends AppCompatActivity {

    public static final String TITLE_FOR_ADD_OR_EDIT_PHONE_WINDOW = "Title for AddOrEditPhoneWindow";
    private Toolbar toolbar;
    private ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobiles_window);
        findToolbar();
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            addListFragment();
        }
    }

    private void findToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void addListFragment() {
        listFragment = new ListFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, listFragment)
                .commit();
        listFragment.setRetainInstance(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (isActionAddPhone(item)) {
            goToAddPhoneWindowWith();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isActionAddPhone(MenuItem item) {
        return item.getItemId() == R.id.action_add_mobile_phone;
    }

    private void goToAddPhoneWindowWith() {
        Intent intent = new Intent(this, AddOrEditPhoneWindow.class);
        intent.putExtras(createBundleForAddWindow());
        startActivity(intent);
    }

    private Bundle createBundleForAddWindow() {
        Bundle bundle = new Bundle();
        String addPhoneTitle = getString(R.string.add_mobile_phone);
        bundle.putString(TITLE_FOR_ADD_OR_EDIT_PHONE_WINDOW, addPhoneTitle);
        return bundle;
    }
}
