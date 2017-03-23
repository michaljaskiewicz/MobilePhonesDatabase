package com.dev.jaskiewicz.mobilephones.ui.mainWindow;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dev.jaskiewicz.mobilephones.R;
import com.dev.jaskiewicz.mobilephones.ui.BasePhoneWindow;
import com.dev.jaskiewicz.mobilephones.ui.add.AddPhoneWindow;
import com.dev.jaskiewicz.mobilephones.ui.edit.EditPhoneWindow;

public class MobilesWindow extends BasePhoneWindow implements MobilesFragment.MobilePhoneClickListener {

    public static final String ID_OF_MOBILE_PHONE_CHOSEN_FOR_UPDATE = "Id of mobile phone chosen for update";

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

    @Override
    public void onMobilePhoneClick(long phoneId) {
        openEditPhoneWindowFor(phoneId);
    }

    private void openEditPhoneWindowFor(long phoneId) {
        Intent intent = new Intent(this, EditPhoneWindow.class);
        Bundle bundle = new Bundle();
        bundle.putLong(ID_OF_MOBILE_PHONE_CHOSEN_FOR_UPDATE, phoneId);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
