package com.dev.jaskiewicz.mobilephones.ui;

import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

import com.dev.jaskiewicz.mobilephones.R;
import com.dev.jaskiewicz.mobilephones.data.MobilesContract;
import com.dev.jaskiewicz.mobilephones.data.database.MobilesTable;

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
        listFragment.setListAdapter(new SimpleCursorAdapter(
                this,
                R.layout.list_item,
                queryForResult(),
                producerAndModelColumns(),
                labelsIdsForProducerAndModel()
        ));
    }

    private int[] labelsIdsForProducerAndModel() {
        return new int[] {R.id.list_item_producer_label, R.id.list_item_model_label};
    }

    private String[] producerAndModelColumns() {
        return new String[] {MobilesTable.COLUMN_PRODUCER, MobilesTable.COLUMN_MODEL};
    }

    private Cursor queryForResult() {
        return getContentResolver().query(
                MobilesContract.CONTENT_URI,
                MobilesTable.namesOfColumns(),
                null,
                null,
                null);
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
