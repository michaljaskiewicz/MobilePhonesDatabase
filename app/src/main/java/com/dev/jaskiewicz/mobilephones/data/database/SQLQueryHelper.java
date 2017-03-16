package com.dev.jaskiewicz.mobilephones.data.database;


public final class SQLQueryHelper {

    private SQLQueryHelper() {
        // Klasa ma mieć same metody statyczne, więc nie chcę żeby ktokolwiek tworzył jej obiekt
    }

    public static final String CREATE_TABLE_MOBILES = "CREATE TABLE " +
            MobilesTable.TABLE_NAME + "( " +
            MobilesTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MobilesTable.COLUMN_PRODUCER + " TEXT NOT NULL, " +
            MobilesTable.COLUMN_MODEL + " TEXT NOT NULL, " +
            MobilesTable.COLUMN_ANDROID_VERSION + " TEXT NOT NULL, " +
            MobilesTable.COLUMN_WWW + " TEXT NOT NULL);";
}
