package com.dev.jaskiewicz.mobilephones.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.jaskiewicz.mobilephones.R;

public class AddPhoneFragment extends Fragment {

    private static final boolean DO_NOT_ATTACH_TO_ROOT = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_phone_fragment, container, DO_NOT_ATTACH_TO_ROOT);
    }
}
