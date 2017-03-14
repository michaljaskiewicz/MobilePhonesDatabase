package com.dev.jaskiewicz.mobilephones.ui.validation;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

/**
 * Waliduje dane wprowadzone do pól typu EditText
 */
public class InputValidator {
    // TODO w jaki dokładnie sposób mamy walidować dane w oknie tworzenia/edycji telefonu

    private EditText producer;
    private EditText model;
    private EditText androidVersion;
    private EditText url;

    public InputValidator(EditText producer, EditText model, EditText androidVersion, EditText url) {
        this.producer = producer;
        this.model = model;
        this.androidVersion = androidVersion;
        this.url = url;
    }

    public boolean isValid() {
        return isProducerValid() &&
                isModelValid() &&
                isAndroidVersionValid() &&
                isUrlValid();
    }

    public boolean isProducerValid() {
        return isNotEmpty(producer);
    }

    public boolean isModelValid() {
        return isNotEmpty(model);
    }

    public boolean isAndroidVersionValid() {
        return isNotEmpty(androidVersion);
    }

    public boolean isUrlValid() {
        return isNotEmpty(url) &&
                Patterns.WEB_URL.matcher(url.getText()).matches();
    }

    private boolean isNotEmpty(EditText editText) {
        return !TextUtils.isEmpty(editText.getText());
    }
}