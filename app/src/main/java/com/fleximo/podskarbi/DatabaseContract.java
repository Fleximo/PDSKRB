package com.fleximo.podskarbi;

import android.provider.BaseColumns;

/**
 * Created by fleximo on 15.08.16.
 */
public final class DatabaseContract {
        public DatabaseContract() {}

    public static abstract class CategoriesTable implements BaseColumns {
        public static final String TABLE_NAME = "CATEGORIES";
        public static final String COLUMN_NAME_PURCHASE_PLACE = "purchase_place";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_CURRENCY = "currency";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_PRIORITY = "priority";
    }
}
