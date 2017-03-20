package com.dev.jaskiewicz.mobilephones.utils;


public class UrlStringMaker {

    private static final String HTTP = "http://";
    private static final String HTTPS = "https://";

    private UrlStringMaker() {}


    /**
     * Użycie tej metody zapewnia, że adres url jest prawidłowy
     * tzn, że zaczyna się on od protokołu http lub https
     * @param url zwracany jest jeśli jest prawidłowy
     * @return poprawny adres url rozpoczynający się od protokołu http/https
     */
    public static String buildCorrectUrlStringFrom(String url) {
        if (url.startsWith(HTTP) || url.startsWith(HTTPS)) {
            return url;
        } else {
            return HTTP + url;
        }
    }
}
