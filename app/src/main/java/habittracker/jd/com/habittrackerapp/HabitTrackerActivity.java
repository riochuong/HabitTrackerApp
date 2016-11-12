package habittracker.jd.com.habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class HabitTrackerActivity extends AppCompatActivity {

    HabitTrackerSqliteHelper mDbHelper;
    SQLiteDatabase mDb;
    SimpleDateFormat simpleDateFormat;

    String[] projectionToRead = {
            HabitTrackerContract.HabitEntry._ID,
            HabitTrackerContract.HabitEntry.COLUMN_HABIT_DATE,
            HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME,
            HabitTrackerContract.HabitEntry.COLUMN_HABIT_DURATION
    };

    String selectionSoccer = HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME +"=?";
    String [] soccerSelectionArgs = { AppConst.HABIT_PLAY_SOCCER };
    String sortOrder = HabitTrackerContract.HabitEntry.COLUMN_HABIT_DURATION +" DESC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_tracker);
        // create the DB
        mDbHelper = new HabitTrackerSqliteHelper(this);
        // change db to write mode
        mDb = mDbHelper.getWritableDatabase();
        simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        insertDummyDataToDb();
        readDummyDataAndDisplayAsTextview();
    }

    private void insertDummyDataToDb (){

        try {
              // jogging Value
            ContentValues joggingVal = new ContentValues();
            joggingVal.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME,
                                                                    AppConst.HABIT_JOGGING);
            joggingVal.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_DATE,
                                simpleDateFormat.format(
                                        simpleDateFormat.parse("05-10-2016 18:06")));
            joggingVal.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_DURATION,
                                2);

            mDb.insert(HabitTrackerContract.HabitEntry.TABLE_NAME,null, joggingVal);

            // soccer Value
            ContentValues socverVal = new ContentValues();
            socverVal.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME,
                    AppConst.HABIT_PLAY_SOCCER);
            socverVal.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_DATE,
                    simpleDateFormat.format(
                            simpleDateFormat.parse("06-10-2016 19:06")));
            socverVal.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_DURATION,
                    4);
            mDb.insert(HabitTrackerContract.HabitEntry.TABLE_NAME, null, socverVal);





        } catch (ParseException e) {
            Log.e(AppConst.TAG,"Failed to parse Date string");
            e.printStackTrace();
        }
    }

    private void readDummyDataAndDisplayAsTextview (){
        Cursor cursor = mDb.query(
                HabitTrackerContract.HabitEntry.TABLE_NAME,
                projectionToRead,
                selectionSoccer,
                soccerSelectionArgs,
                null,
                null,
                sortOrder
        );

        boolean result = cursor.moveToFirst();

        // only log it for now
        if (result){
            String habit = cursor.getString(
                    cursor.getColumnIndexOrThrow(HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME));
            int duration = cursor.getInt(
                    cursor.getColumnIndexOrThrow(HabitTrackerContract.HabitEntry.COLUMN_HABIT_DURATION)
            );
            Log.d(AppConst.TAG,
                    "HABIT: "+habit +" Duration: "+duration+" hours");
        }



    }
}
