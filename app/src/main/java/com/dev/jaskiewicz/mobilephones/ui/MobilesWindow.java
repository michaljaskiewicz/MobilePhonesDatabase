package com.dev.jaskiewicz.mobilephones.ui;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

import com.dev.jaskiewicz.mobilephones.R;
import com.dev.jaskiewicz.mobilephones.data.MobilesContract;
import com.dev.jaskiewicz.mobilephones.data.database.MobilesTable;
import com.dev.jaskiewicz.mobilephones.ui.add.AddPhoneWindow;

public class MobilesWindow extends BasePhoneWindow {

    @Override
    protected String getWindowTitle() {
        return getString(R.string.app_name);
    }

    @Override
    protected Fragment prepareFragmentForThisWindow() {
        ListFragment listFragment = new ListFragment();
        getFragmentManager()
                .beginTransaction()
                .add(getFragmentContainerId(), listFragment)
                .commit();
        listFragment.setRetainInstance(true);
        listFragment.setListAdapter(new SimpleCursorAdapter(
                this,
                R.layout.list_item,
                queryForResult(),
                producerAndModelColumns(),
                labelsIDsForProducerAndModel()
        ));
        return listFragment;
    }

    private int[] labelsIDsForProducerAndModel() {
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
            goToAddPhoneWindow();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isActionAddPhone(MenuItem item) {
        return item.getItemId() == R.id.action_add_mobile_phone;
    }

    private void goToAddPhoneWindow() {
        Intent intent = new Intent(this, AddPhoneWindow.class);
        startActivity(intent);
    }
}
