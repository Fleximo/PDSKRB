package com.fleximo.podskarbi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fleximo on 15.08.16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "RPodskarbi.db";
    private static DBHelper sInstance = null;

    //-----------------   SEPARATION SYMBOLS AND TYPES   -----------------//
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    //----------------- CREATE CATEGORIES DATABASE TABLE -----------------//
    private static final String SQL_CREATE_CATEGORIES =
            "CREATE TABLE IF NOT EXISTS " + DatabaseContract.CategoriesTable.TABLE_NAME +
            " (" +
            DatabaseContract.CategoriesTable._ID + "INTEGER PRIMARY KEY," +
            DatabaseContract.CategoriesTable.COLUMN_NAME_PURCHASE_PLACE  + TEXT_TYPE + COMMA_SEP +
            DatabaseContract.CategoriesTable.COLUMN_NAME_PRICE  + REAL_TYPE + COMMA_SEP +
            DatabaseContract.CategoriesTable.COLUMN_NAME_CURRENCY  + INT_TYPE + COMMA_SEP +
            DatabaseContract.CategoriesTable.COLUMN_NAME_CATEGORY  + TEXT_TYPE + COMMA_SEP +
            DatabaseContract.CategoriesTable.COLUMN_NAME_PRIORITY + INT_TYPE +
            " )";

    //----------------- DELETE CATEGORIES DATABASE TABLE -----------------//
    private static final String SQL_DELETE_CATEGORIES =
            "DROP TABLE IF EXISTS " + DatabaseContract.CategoriesTable.TABLE_NAME;

    public static synchronized DBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CATEGORIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_CATEGORIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
