package com.dev.jaskiewicz.mobilephones.data;

import android.net.Uri;

/**
 * Klasa przechowuje definicje stałych potrzebnych do poprawnego działania MobilesProvider
 * Za pomocą tych stałych możliwe jest operowanie na danych dostarczanych przez MobilesProvider
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

    /* Identyfikator Content Providera */
    static final String AUTHORITY = "com.dev.jaskiewicz.mobilephones.data.MobilesProvider";

    static final String PATH_TO_MOBILES = "mobiles";
    static final String PATH_TO_MOBILE_PHONE_SPECIFIED_BY_ID = PATH_TO_MOBILES + "/#";
    static final String CONTENT = "content://";

    /* Sciezka do glownej zawartosci MobilesProvidera*/
    static final Uri BASE_CONTENT_URI = Uri.parse(CONTENT + AUTHORITY);

    /* Uri, za pomocą którego możemy uzyskać dostęp do wszystkich telefonów */
    public static final Uri CONTENT_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH_TO_MOBILES).build();
}
