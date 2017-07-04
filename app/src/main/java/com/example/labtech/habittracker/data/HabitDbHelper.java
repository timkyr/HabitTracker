package com.example.labtech.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.labtech.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by LABTECH on 4/7/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "shelter.db";

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_HABITS_TABLE =
                "CREATE TABLE " + HabitEntry.TABLE_NAME + "(" +
                        HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL," +
                        HabitEntry.COLUMN_HABIT_DURATION + " INTEGER," +
                        HabitEntry.COLUMN_HABIT_REPEATABILITY + " INTEGER NOT NULL," +
                        HabitEntry.COLUMN_HABIT_CATEGORY + " INTEGER NOT NULL DEFAULT 0);" ;

        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_HABITS_TABLE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    private static final String SQL_DELETE_HABITS_TABLE =
            "DROP TABLE IF EXISTS " + HabitEntry.TABLE_NAME;

}


