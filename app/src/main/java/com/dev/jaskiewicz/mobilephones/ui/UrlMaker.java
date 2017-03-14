package com.dev.jaskiewicz.mobilephones.ui;


public class UrlMaker {

    private static final String HTTP = "http://";
    private static final String HTTPS = "https://";

    private UrlMaker() {}

    public static String buildCorrectUrlFrom(String url) {
        if (url.startsWith(HTTP) || url.startsWith(HTTPS)) {
            return url;
        } else {
            return HTTP + url;
        }
    }
}
