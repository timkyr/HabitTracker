package com.example.labtech.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labtech.habittracker.data.HabitContract.HabitEntry;
import com.example.labtech.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private void selectCategory() {

    }


    private void insertHabit() {

        String nameString = "bicycle";
        int durationString = 60;
        int repeatabilityString = 1;
        int category = HabitEntry.CATEGORY_EXERCISE;


        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, nameString);
        values.put(HabitEntry.COLUMN_HABIT_CATEGORY, durationString);
        values.put(HabitEntry.COLUMN_HABIT_REPEATABILITY, repeatabilityString);
        values.put(HabitEntry.COLUMN_HABIT_CATEGORY, category);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
        Log.v("MainActivity", "New row ID" + newRowId);

        if (newRowId == -1) {
            Toast.makeText(MainActivity.this, "Error with saving habit", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Habit saved with id:" + newRowId, Toast.LENGTH_LONG).show();
        }
    }

    private void displayDatabaseInfo() {

        // Create and/or open a database to read from it
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        TextView displayView = (TextView) findViewById(R.id.text_view_habit);

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_DURATION,
                HabitEntry.COLUMN_HABIT_REPEATABILITY,
                HabitEntry.COLUMN_HABIT_CATEGORY
        };

        // Filter results WHERE ....
        String selection = HabitEntry.COLUMN_HABIT_CATEGORY + " = ?";
        int[] selectionArgs = {HabitEntry.CATEGORY_EXERCISE};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                HabitEntry.COLUMN_HABIT_CATEGORY + " DESC";

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,                     // The table to query
                null,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );


        try {
            // Create a header in the Text View that looks like this:
            //
            // The habits table contains <number of rows in Cursor> habits.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABIT_NAME + " - " +
                    HabitEntry.COLUMN_HABIT_DURATION + " - " +
                    HabitEntry.COLUMN_HABIT_REPEATABILITY + " - " +
                    HabitEntry.COLUMN_HABIT_CATEGORY + "\n");

            //figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int durationColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DURATION);
            int repeatabilityColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_REPEATABILITY);
            int categoryColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_CATEGORY);

            //Iterate throug all the returned rows in the cusror
            while ((cursor.moveToNext())) {
                //use that index to extract the String or Int value of the word
                //at the current row the cursor is on
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentDuration = cursor.getInt(durationColumnIndex);
                int currentRepeatability = cursor.getInt(repeatabilityColumnIndex);
                int currenCategory = cursor.getInt(categoryColumnIndex);

                //display the valies from each column of the current row in the cursor in the Textview
                displayView.append("\n" + currentID + " - " +
                        currentName + " - " +
                        currentDuration + " - " +
                        currentRepeatability + " - " +
                        currenCategory);
            }


        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }


}