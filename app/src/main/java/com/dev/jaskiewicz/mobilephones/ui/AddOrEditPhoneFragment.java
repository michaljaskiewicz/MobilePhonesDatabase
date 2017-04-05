package com.dev.jaskiewicz.mobilephones.ui;

import android.app.Fragment;
import android.content.ContentValues;
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
import com.dev.jaskiewicz.mobilephones.data.database.MobilesTable;
import com.dev.jaskiewicz.mobilephones.utils.UrlStringMaker;
import com.dev.jaskiewicz.mobilephones.ui.validation.InputValidator;

import static android.content.Intent.ACTION_VIEW;

public abstract class AddOrEditPhoneFragment extends Fragment implements View.OnClickListener{
    private static final boolean DO_NOT_ATTACH_TO_ROOT = false;

    private InputValidator inputValidator;
    private EditText producerEditText;
    private EditText modelEditText;
    private EditText androidVersionEditText;
    private EditText urlEditText;
    private Button urlSearchButton;
    private Button cancelButton;
    private Button saveButton;

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
        urlEditText = (EditText) getActivity().findViewById(R.id.url_edit_text);
        urlSearchButton = (Button) getActivity().findViewById(R.id.url_search_button);
        cancelButton = (Button) getActivity().findViewById(R.id.cancel_button);
        saveButton = (Button) getActivity().findViewById(R.id.save_button);
    }

    private void createInputValidator() {
        inputValidator = new InputValidator(producerEditText, modelEditText, androidVersionEditText, urlEditText);
    }

    private void setUpOnClickListener() {
        saveButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        urlSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (isUrlSearchButton(view)) {
            searchPhoneInWebBrowser();
        } else if (isCancelButton(view)) {
            closeWindow();
        } else if (isSaveButton(view)) {
            tryToSavePhone();
        }
    }

    private boolean isUrlSearchButton(View view) {
        return view.getId() == R.id.url_search_button;
    }

    private void searchPhoneInWebBrowser() {
        if (inputValidator.isUrlValid()) {
            openUrlInWebBrowser();
        } else {
            showShortMessageWith(getString(R.string.url_not_valid_message));
        }
    }

    private boolean isCancelButton(View view) {
        return view.getId() == R.id.cancel_button;
    }

    private void closeWindow() {
        getActivity().finish();
    }

    private boolean isSaveButton(View view) {
        return view.getId() == R.id.save_button;
    }

    private void tryToSavePhone() {
        if (inputIsValid()) {
            savePhoneInDatabase();
            closeWindow();
        } else {
            tellUserThatInputIsNotValid();
        }
    }

    private boolean inputIsValid() {
        return inputValidator.isValid();
    }

    /**
     * Metoda wywoływana, gdy przycisk "Zapisz" został kliknięty oraz jeśli wprowadzone dane są prawidłowe
     *
     * Określa jaka operacja zapisu do bazy danych ma zostać wykonana
     */
    protected abstract void savePhoneInDatabase();

    private void  tellUserThatInputIsNotValid() {
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

    /**
     * Metoda do użycia w konkretnej implementacji savePhoneInDatabase()
     *
     * @return ContentValues zawierający wszystkie dane telefonu pobrane z pól Edit Text
     */
    protected ContentValues preparePhonesDataToSaveInDatabase() {
        final ContentValues values = new ContentValues();
        values.put(MobilesTable.COLUMN_PRODUCER, getProducerFromEditText());
        values.put(MobilesTable.COLUMN_MODEL, getModelFromEditText());
        values.put(MobilesTable.COLUMN_ANDROID_VERSION, getAndroidVersionFromEditText());
        values.put(MobilesTable.COLUMN_WWW, getUrlFromEditText());
        return values;
    }

    protected String getProducerFromEditText() {
        return producerEditText.getText().toString();
    }

    protected String getModelFromEditText() {
        return modelEditText.getText().toString();
    }

    protected String getAndroidVersionFromEditText() {
        return androidVersionEditText.getText().toString();
    }

    protected String getUrlFromEditText() {
        return urlEditText.getText().toString();
    }

    protected void assignProducer(String producer) {
        producerEditText.setText(producer);
    }

    protected void assignModel(String model) {
        modelEditText.setText(model);
    }

    protected void assignAndroidVersion(String androidVersion) {
        androidVersionEditText.setText(androidVersion);
    }

    protected void assignUrl(String url) {
        urlEditText.setText(url);
    }

    private void showShortMessageWith(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    private void openUrlInWebBrowser() {
        Intent intent = new Intent(ACTION_VIEW, Uri.parse(prepareCorrectUrlString()));
        startActivity(intent);
    }

    private String prepareCorrectUrlString() {
        final String url = getUrlFromEditText();
        return UrlStringMaker.buildCorrectUrlStringFrom(url);
    }
}
