package com.example.ananth.courtcounter.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.ananth.courtcounter.Data.ScoreContract.ScoreEntry;

/**
 * Created by Ananth on 2/7/2018.
 */

public class ScoreDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "scoreDb.db";

    //You have to change database version if you change schema
    public static final int DATABASE_VERSION = 1;


    public ScoreDbHelper(Context context){super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    /**
     * This function is called when the database is created for the first time
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_TABLE = "CREATE TABLE " + ScoreEntry.TABLE_NAME + " (" +
                ScoreEntry._ID               + " INTEGER PRIMARY KEY, " +
                ScoreEntry.COLUMN_TEAM_A     + " INTEGER NON NULL, " +
                ScoreEntry.COLUMN_TEAM_B     + " INTEGER NON NULL, " +
                ScoreEntry.COLUMN_SCORE_A    + " INTEGER, " +
                ScoreEntry.COLUMN_SCORE_B    + " INTEGER);";

        sqLiteDatabase.execSQL(CREATE_TABLE);


    }
    /**
     * This method discards the old table of data and calls onCreate to recreate a new one.
     * This only occurs when the version number for this database (DATABASE_VERSION) is incremented.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ScoreEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
