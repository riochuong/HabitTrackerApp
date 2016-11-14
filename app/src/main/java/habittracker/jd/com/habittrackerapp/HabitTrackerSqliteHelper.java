package habittracker.jd.com.habittrackerapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by chuondao on 11/12/16.
 */

public class HabitTrackerSqliteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "HabitTracker.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String CREATE_DATABASE =
            "CREATE TABLE " + HabitTrackerContract.HabitEntry.TABLE_NAME
                    + " (" + HabitTrackerContract.HabitEntry._ID + " INTEGER PRIMARY KEY,"
                    + HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME + TEXT_TYPE + COMMA_SEP
                    + HabitTrackerContract.HabitEntry.COLUMN_HABIT_DATE + TEXT_TYPE + COMMA_SEP
                    + HabitTrackerContract.HabitEntry.COLUMN_HABIT_DURATION + " INTEGER )";

    public HabitTrackerSqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DATABASE);
        Log.d(AppConst.TAG, "Create DB command: " + CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
