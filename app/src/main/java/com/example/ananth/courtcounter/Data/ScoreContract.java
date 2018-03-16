package com.example.ananth.courtcounter.Data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Ananth on 2/7/2018.
 */

public class ScoreContract {

    // We provide constants used by ContentProvider in this class

    //Authority tells the code which ContentProvider to access
    public static final String AUTHORITY = "com.example.ananth.courtcounter";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_SCORES = "scores";


    public static final class ScoreEntry implements BaseColumns{

        //ScoreEntry content URI = base content uri + path
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_SCORES).build();


        //Score Table and column names
        public static final String TABLE_NAME = "scores";
        // Since TaskEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below
        public static final String COLUMN_TEAM_A = "team_a";
        public static final String COLUMN_TEAM_B = "team_b";
        public static final String COLUMN_SCORE_A = "score_a";
        public static final String COLUMN_SCORE_B = "score_b";

    }
}
