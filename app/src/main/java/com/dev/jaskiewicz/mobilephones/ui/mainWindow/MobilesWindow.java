package com.dev.jaskiewicz.mobilephones.ui.mainWindow;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dev.jaskiewicz.mobilephones.R;
import com.dev.jaskiewicz.mobilephones.ui.BasePhoneWindow;
import com.dev.jaskiewicz.mobilephones.ui.add.AddMobilePhoneWindow;
import com.dev.jaskiewicz.mobilephones.ui.edit.EditMobilePhoneWindow;

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
        if (isActionAddMobilePhone(item)) {
            goToAddMobilePhoneWindow();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isActionAddMobilePhone(MenuItem item) {
        return item.getItemId() == R.id.action_add_mobile_phone;
    }

    private void goToAddMobilePhoneWindow() {
        Intent intent = new Intent(this, AddMobilePhoneWindow.class);
        startActivity(intent);
    }

    @Override
    public void onMobilePhoneClick(long phoneId) {
        openEditMobilePhoneWindowFor(phoneId);
    }

    private void openEditMobilePhoneWindowFor(long phoneId) {
        Intent intent = new Intent(this, EditMobilePhoneWindow.class);
        Bundle bundle = new Bundle();
        bundle.putLong(ID_OF_MOBILE_PHONE_CHOSEN_FOR_UPDATE, phoneId);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
