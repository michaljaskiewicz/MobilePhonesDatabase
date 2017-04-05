package com.dev.jaskiewicz.mobilephones.ui.validation;

import android.text.TextUtils;
import android.widget.EditText;
import org.apache.commons.validator.routines.UrlValidator;

import static com.dev.jaskiewicz.mobilephones.utils.UrlStringMaker.buildCorrectUrlStringFrom;

/**
 * Waliduje dane wprowadzone do pól typu EditText
 */
public class InputValidator {

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

    private boolean isNotEmpty(EditText editText) {
        return !TextUtils.isEmpty(editText.getText());
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
        String[] schemes = {"http","https"};
        UrlValidator validator = new UrlValidator(schemes);
        return validator.isValid(buildCorrectUrlStringFrom(getUrl()));
    }

    private String getUrl() {
        return url.getText().toString();
    }
}