package com.dev.jaskiewicz.mobilephones.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.dev.jaskiewicz.mobilephones.database.SQLQueryHelper.CREATE_TABLE_MOBILES;

public class MobilesDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mobiles.db";
    private static final int DATABASE_VERSION = 1;

    public MobilesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MOBILES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
