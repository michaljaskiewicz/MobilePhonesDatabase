package com.dev.jaskiewicz.mobilephones.database;

import android.provider.BaseColumns;

public class MobilesTable implements BaseColumns {

    private MobilesTable(){
        // Klasa ma mieć same metody statyczne, więc nie chcę żeby ktokolwiek tworzył jej obiekt
    }

    public static final String TABLE_NAME = "mobiles";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_PRODUCER = "producer";
    public static final String COLUMN_MODEL = "model";
    public static final String COLUMN_ANDROID_VERSION = "android_version";
}
