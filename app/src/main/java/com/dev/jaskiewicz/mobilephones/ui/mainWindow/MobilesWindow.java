package com.dev.jaskiewicz.mobilephones.ui.mainWindow;

import android.app.Fragment;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.dev.jaskiewicz.mobilephones.R;
import com.dev.jaskiewicz.mobilephones.ui.BasePhoneWindow;
import com.dev.jaskiewicz.mobilephones.ui.add.AddPhoneWindow;

public class MobilesWindow extends BasePhoneWindow {

    @Override
    protected String getWindowTitle() {
        return getString(R.string.app_name);
    }

    @Override
    protected Fragment prepareFragmentForThisWindow() {
        return new MobilesFragment();
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
