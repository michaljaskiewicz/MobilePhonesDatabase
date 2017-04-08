package com.dev.jaskiewicz.mobilephones.ui.validation;

import android.text.TextUtils;
import android.widget.EditText;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.Arrays;

import static com.dev.jaskiewicz.mobilephones.utils.UrlStringMaker.buildCorrectUrlStringFrom;

/**
 * Waliduje dane wprowadzone do pól typu EditText
 */
public class InputValidator {
    private static final String[] ALL_ANDROID_RELEASES = new String[] {
            "1.0", "1.1", "1.5", "1.6",
            "2.0", "2.0.1",
            "2.1", "2.2", "2.2.1", "2.2.2", "2.2.3",
            "2.3", "2.3.1", "2.3.2", "2.3.3", "2.3.4", "2.3.5", "2.3.6", "2.3.7",
            "3.0",
            "3.1",
            "3.2", "3.2.1", "3.2.2", "3.2.3", "3.2.4", "3.2.5", "3.2.6",
            "4.0", "4.0.1", "4.0.2", "4.0.3", "4.0.4",
            "4.1", "4.1.1", "4.1.2",
            "4.2", "4.2.1", "4.2.2",
            "4.3", "4.3.1",
            "4.4", "4.4.1", "4.4.2", "4.4.3", "4.4.4",
            "5.0", "5.0.1", "5.0.2",
            "5.1", "5.1.1",
            "6.0", "6.0.1",
            "7.0", "7.1", "7.1.1", "7.1.2",
            "8.0"
    };

    private EditText producerEditText;
    private EditText modelEditText;
    private EditText androidVersionEditText;
    private EditText urlEditText;

    public InputValidator(EditText producerEditText, EditText modelEditText,
                          EditText androidVersionEditText, EditText urlEditText) {
        this.producerEditText = producerEditText;
        this.modelEditText = modelEditText;
        this.androidVersionEditText = androidVersionEditText;
        this.urlEditText = urlEditText;
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
        return isNotEmpty(producerEditText);
    }

    public boolean isModelValid() {
        return isNotEmpty(modelEditText);
    }

    public boolean isAndroidVersionValid() {
        return Arrays.asList(ALL_ANDROID_RELEASES).contains(getAndroidVersion());
    }

    private String getAndroidVersion() {
        return androidVersionEditText.getText().toString();
    }

    public boolean isUrlValid() {
        String[] schemes = {"http","https"};
        UrlValidator validator = new UrlValidator(schemes);
        return validator.isValid(buildCorrectUrlStringFrom(getUrl()));
    }

    private String getUrl() {
        return urlEditText.getText().toString();
    }
}