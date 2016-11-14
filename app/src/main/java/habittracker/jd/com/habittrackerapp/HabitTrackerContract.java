package habittracker.jd.com.habittrackerapp;

import android.provider.BaseColumns;

/**
 * Created by chuondao on 11/12/16.
 */

public class HabitTrackerContract {

    /**
     * to prevent some one to initiate the db wrongly
     */
    private HabitTrackerContract() {
    }

    ;

    public static class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "habit_tracker";
        public static final String COLUMN_HABIT_NAME = "habit_name";
        public static final String COLUMN_HABIT_DATE = "habit_date";
        public static final String COLUMN_HABIT_DURATION = "habit_duration";
    }

}
