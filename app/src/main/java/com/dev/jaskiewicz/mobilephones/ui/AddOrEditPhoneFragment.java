package com.dev.jaskiewicz.mobilephones.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.jaskiewicz.mobilephones.R;
import com.dev.jaskiewicz.mobilephones.ui.validation.InputValidator;

public class AddOrEditPhoneFragment extends Fragment {
// TODO czy adres www mamy zapisywac w bazie
    private static final boolean DO_NOT_ATTACH_TO_ROOT = false;

    private InputValidator inputValidator;
    private EditText producer;
    private EditText model;
    private EditText androidVersion;
    private EditText url;
    private Button searchUrl;
    private Button cancel;
    private Button save;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_or_edit_phone_fragment, container, DO_NOT_ATTACH_TO_ROOT);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findAllViews();
        createInputValidator();
        setUpOnClickListener();
    }

    private void findAllViews() {
        producer = (EditText) getActivity().findViewById(R.id.producer_edit_text);
        model = (EditText) getActivity().findViewById(R.id.model_edit_text);
        androidVersion = (EditText) getActivity().findViewById(R.id.android_version_edit_text);
        url = (EditText) getActivity().findViewById(R.id.www_edit_text);
        searchUrl = (Button) getActivity().findViewById(R.id.search_url_button);
        cancel = (Button) getActivity().findViewById(R.id.cancel_button);
        save = (Button) getActivity().findViewById(R.id.save_button);
    }

    private void createInputValidator() {
        inputValidator = new InputValidator(producer, model, androidVersion, url);
    }

    private void setUpOnClickListener() {
        final View.OnClickListener onClickListener = createOnClickListener();
        save.setOnClickListener(onClickListener);
        cancel.setOnClickListener(onClickListener);
        searchUrl.setOnClickListener(onClickListener);
    }

    private View.OnClickListener createOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.save_button:
                        savePhone();
                        break;
                    case R.id.search_url_button:
                        searchPhone();
                        break;
                    case R.id.cancel_button:
                        //TODO dowiedzieć się co ma robić ten przycisk
                        break;
                }
            }
        };
    }

    private void savePhone() {
        if (inputValidator.isValid()) {
            savePhoneToDatabase();
        } else {
            tellUserThatInputIsNotValid();
        }
    }

    private void savePhoneToDatabase() {

    }

    private void tellUserThatInputIsNotValid() {
        if (!inputValidator.isProducerValid()) {
            showShortMessageWith(getString(R.string.producer_not_valid_message));
        }
        if (!inputValidator.isModelValid()) {
            showShortMessageWith(getString(R.string.model_not_valid_message));
        }
        if (!inputValidator.isAndroidVersionValid()) {
            showShortMessageWith(getString(R.string.android_version_not_valid_message));
        }
        if (!inputValidator.isUrlValid()) {
            showShortMessageWith(getString(R.string.url_not_valid_message));
        }
    }

    private void showShortMessageWith(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    private void searchPhone() {

    }
}
