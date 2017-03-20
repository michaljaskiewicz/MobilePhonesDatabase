package com.dev.jaskiewicz.mobilephones.utils;


public class UrlStringMaker {

    private static final String HTTP = "http://";
    private static final String HTTPS = "https://";

    private UrlStringMaker() {}

    public static String buildCorrectUrlStringFrom(String url) {
        if (url.startsWith(HTTP) || url.startsWith(HTTPS)) {
            return url;
        } else {
            return HTTP + url;
        }
    }
}
