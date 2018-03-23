package com.hfad.euchreai;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Schlenker18 on 3/23/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String stats_Database = "stats.db";

    public static final String table_Name1 = "games_table";
    public static final String col_1 = "Won";
    public static final String col_2 = "Lost";
    public static final String col_3 = "Total";

    public static final String table_Name2 = "hands_table";
    public static final String col_4 = "Won";
    public static final String col_5 = "Lost";
    public static final String col_6 = "Total";

    public static final String table_Name3 = "tricks_table";
    public static final String col_7 = "Won";
    public static final String col_8 = "Lost";
    public static final String col_9 = "Total";

    public static final String table_Name4 = "trumpCalls_table";
    public static final String col_10 = "Won";
    public static final String col_11 = "Lost";
    public static final String col_12 = "Total";

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

        Log.v("--DatabaseHelper49--", "Database has been created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + table_Name1 + "(" + col_1 + " INTEGER,"
                + col_2 +" INTEGER,"+ col_3 +  " INTEGER )");

        db.execSQL("create table " + table_Name2 + "(" + col_4 + " INTEGER,"
                + col_5 +" INTEGER,"+ col_6 +  " INTEGER )");

        db.execSQL("create table " + table_Name3 + "(" + col_7 + " INTEGER,"
                + col_8 +" INTEGER,"+ col_9 +  " INTEGER )");

        db.execSQL("create table " + table_Name4 + "(" + col_10 + " INTEGER,"
                + col_11 +" INTEGER,"+ col_12 +  " INTEGER )");

        db.execSQL("create table " + table_Name5 + "(" + col_13 + " INTEGER,"
                + col_14 +" INTEGER,"+ col_15 +  " INTEGER )");

        db.execSQL("create table " + table_Name6 + "(" + col_16 + " INTEGER,"
                + col_17 +" INTEGER,"+ col_18 +  " INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_Name1);
        db.execSQL("DROP TABLE IF EXISTS " + table_Name2);
        db.execSQL("DROP TABLE IF EXISTS " + table_Name3);
        db.execSQL("DROP TABLE IF EXISTS " + table_Name4);
        db.execSQL("DROP TABLE IF EXISTS " + table_Name5);
        db.execSQL("DROP TABLE IF EXISTS " + table_Name6);
        onCreate(db);
    }

    public boolean initializeGames() {
        SQLiteDatabase db = this.getWritableDatabase();

        //games table
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1, 0);
        contentValues.put(col_2, 0);
        contentValues.put(col_3, 0);
        long result = db.insert(table_Name1, null, contentValues);

        if (result == -1) {
            return false;
        }

        return true;
    }

    public boolean initializeHands() {
        SQLiteDatabase db = this.getWritableDatabase();

        //hands table
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_4, 0);
        contentValues.put(col_5, 0);
        contentValues.put(col_6, 0);
        long result = db.insert(table_Name2, null, contentValues);

        if (result == -1) {
            return false;
        }

        return true;
    }

    public boolean initializeTricks() {
        SQLiteDatabase db = this.getWritableDatabase();

        //tricks table
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_7, 0);
        contentValues.put(col_8, 0);
        contentValues.put(col_9, 0);
        long result = db.insert(table_Name3, null, contentValues);

        if (result == -1) {
            return false;
        }

        return true;
    }

    public boolean initializeTrumpCalls() {
        SQLiteDatabase db = this.getWritableDatabase();

        //trump calls table
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_10, 0);
        contentValues.put(col_11, 0);
        contentValues.put(col_12, 0);
        long result = db.insert(table_Name4, null, contentValues);

        if (result == -1) {
            return false;
        }

        return true;

    }

    public boolean initializeLoners() {
        SQLiteDatabase db = this.getWritableDatabase();

        //loners table
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_13, 0);
        contentValues.put(col_14, 0);
        contentValues.put(col_15, 0);
        long result = db.insert(table_Name5, null, contentValues);

        if (result == -1) {
            return false;
        }

        return true;
    }

    public boolean initializeMisc() {
        SQLiteDatabase db = this.getWritableDatabase();

        //miscellaneous table
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_16, 0);
        contentValues.put(col_17, 0);
        contentValues.put(col_18, 0);
        long result = db.insert(table_Name6, null, contentValues);

        if (result == -1) {
            return false;
        }

        return true;
    }
}
