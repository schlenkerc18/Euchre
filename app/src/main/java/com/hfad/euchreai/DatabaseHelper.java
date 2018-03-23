package com.hfad.euchreai;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Schlenker18 on 3/23/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String stats_Database = "stats.db";

    public static final String table_Name1 = "games_table";
    public static final String col1 = "Won";
    public static final String col2 = "Lost";
    public static final String col3 = "Total";

    public static final String table_Name2 = "hands_table";
    public static final String col4 = "Won";
    public static final String col5 = "Lost";
    public static final String col6 = "Total";

    public static final String table_Name3 = "tricks_table";
    public static final String col7 = "Won";
    public static final String col8 = "Lost";
    public static final String col9 = "Total";

    public static final String table_Name4 = "trumpCalls_table";
    public static final String col10 = "Won";
    public static final String col11 = "Lost";
    public static final String col12 = "Total";

    public static final String table_Name5 = "loners_table";
    public static final String col_13 = "Won";
    public static final String col_14 = "Lost";
    public static final String col_15 = "Total";

    public static final String table_Name6 = "misc_table";
    public static final String col_16 = "Euchred Opponents";
    public static final String col_17 = "Took all 5 Tricks";
    public static final String col_18 = "Lost all 5 Tricks";

    public DatabaseHelper(Context context) {
        super(context, stats_Database, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
