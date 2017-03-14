package com.dev.jaskiewicz.mobilephones.ui;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.jaskiewicz.mobilephones.R;
import com.dev.jaskiewicz.mobilephones.ui.validation.InputValidator;

import static android.content.Intent.ACTION_VIEW;

public class AddOrEditPhoneFragment extends Fragment {
// TODO czy adres www mamy zapisywac w bazie
    private static final boolean DO_NOT_ATTACH_TO_ROOT = false;

    private InputValidator inputValidator;
    private EditText producerEditText;
    private EditText modelEditText;
    private EditText androidVersionEditText;
    private EditText urlEditText;
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
        producerEditText = (EditText) getActivity().findViewById(R.id.producer_edit_text);
        modelEditText = (EditText) getActivity().findViewById(R.id.model_edit_text);
        androidVersionEditText = (EditText) getActivity().findViewById(R.id.android_version_edit_text);
        urlEditText = (EditText) getActivity().findViewById(R.id.www_edit_text);
        searchUrl = (Button) getActivity().findViewById(R.id.search_url_button);
        cancel = (Button) getActivity().findViewById(R.id.cancel_button);
        save = (Button) getActivity().findViewById(R.id.save_button);
    }

    private void createInputValidator() {
        inputValidator = new InputValidator(producerEditText, modelEditText, androidVersionEditText, urlEditText);
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
                        searchPhoneInWebBrowser();
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

    private void searchPhoneInWebBrowser() {
        if (inputValidator.isUrlValid()) {
            openInWebBrowser();
        } else {
            showShortMessageWith(getString(R.string.url_not_valid_message));
        }
    }

    private void openInWebBrowser() {
        Intent intent = new Intent(ACTION_VIEW, Uri.parse(prepareCorrectUrl()));
        startActivity(intent);
    }

    private String prepareCorrectUrl() {
        final String url = urlEditText.getText().toString();
        return UrlMaker.buildCorrectUrlFrom(url);
    }
}
