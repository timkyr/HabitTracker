package com.example.labtech.habittracker.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.labtech.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by LABTECH on 4/7/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "everyday.db";
    //generally not a good idea to fully drop a table
    private static final String SQL_DELETE_HABITS_TABLE =
            "DROP TABLE IF EXISTS " + HabitEntry.TABLE_NAME;
    public Cursor cursor;

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
                        HabitEntry.COLUMN_HABIT_CATEGORY + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    //a method which reads all of the information from the database and returns a Cursor object.
    public Cursor queryAllHabits() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_DURATION,
                HabitEntry.COLUMN_HABIT_REPEATABILITY,
                HabitEntry.COLUMN_HABIT_CATEGORY
        };

        return db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        int curVer = oldVersion;
        while (curVer < newVersion) {
            curVer++;
            switch (curVer) {
                case 2: {
                    // Upgrade from V1 to V2
                    //TODO smthg like this: db.execSQL("ALTER TABLE habits ADD COLUMN satisfaction INTEGER");
                    break;
                }
                case 3: {
                    // Upgrade from V2 to V3
                    break;
                }
                case 4: {
                    // Upgrade from V3 to V4
                    break;
                }
            }
        }
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}