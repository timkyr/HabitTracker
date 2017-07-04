package com.example.labtech.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by LABTECH on 4/7/2017.
 */

public final class HabitContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private HabitContract() {}

    /* Inner class that defines the table contents */
    public static class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "habits";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "habitName";
        public static final String COLUMN_HABIT_DURATION = "duration";
        public static final String COLUMN_HABIT_REPEATABILITY = "repeatability";
        public static final String COLUMN_HABIT_CATEGORY = "habitCategory";

        /**
         * Possible values for the category.
         */
        public static final int CATEGORY_VARIOUS= 0;
        public static final int CATEGORY_BASIC_NEEDS = 1;
        public static final int CATEGORY_EXERCISE= 2;
        public static final int CATEGORY_FUN= 3;

    }

}