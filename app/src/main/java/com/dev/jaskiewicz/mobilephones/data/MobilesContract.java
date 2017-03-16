package com.dev.jaskiewicz.mobilephones.data;

import android.net.Uri;

/**
 * Klasa przechowuje definicje stałych potrzebnych do poprawnego działania MobilesProvider
 * Za pomocą tych stałych zapewnia poprawny dostęp do Providera
 *
 * Dla większości stałych stosuję zasięg pakietowy, ponieważ wykorzystuje je tylko w ContentProviderze
 */
public final class MobilesContract {
    private MobilesContract(){}

    /**
     * Zgodnie z konwencją kody tabeli są wyrażane jako kolejne setki - 100, 200, 300, ...
     * a kody kolumn tej tabeli jako kolejne jedności - 101, 102, ...
     */
    static final int MOBILES_CODE = 100;
    static final int MOBILE_ID_CODE = 101;
    static final String AUTHORITY = "com.dev.jaskiewicz.mobilephones.data.MobilesProvider";
    static final String MOBILES_PATH = "mobiles";
    static final String MOBILE_ID_PATH = MOBILES_PATH + "/#";
    static final String CONTENT = "content://";
    static final Uri BASE_CONTENT_URI = Uri.parse(CONTENT + AUTHORITY);

    public static final Uri CONTENT_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(MOBILES_PATH).build();
}
