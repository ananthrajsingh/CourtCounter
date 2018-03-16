package com.example.ananth.courtcounter.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Ananth on 2/8/2018.
 */

public class ScoreContentProvider extends ContentProvider {

    //for whole directory
    public static final int SCORES = 100;
    //for single item
    public static final int SCORES_WITH_ID = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    //define a static buildUriMatcher to map associated URIs with int match

    /**
     Initialize a new matcher object without any matches,
     then use .addURI(String authority, String path, int match) to add matches
     */
    private static UriMatcher buildUriMatcher(){
        //initialization to match no URI
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        /*
          All paths added to the UriMatcher have a corresponding int.
          For each kind of uri you may want to access, add the corresponding match with addURI.
          The two calls below add matches for the task directory and a single item by ID.
         */
        uriMatcher.addURI(ScoreContract.AUTHORITY, ScoreContract.PATH_SCORES, SCORES);
        uriMatcher.addURI(ScoreContract.AUTHORITY, ScoreContract.PATH_SCORES + "/#", SCORES_WITH_ID);
        return uriMatcher;
    }
    /* onCreate() is where you should initialize anything you’ll need to setup
   your underlying data source.
   In this case, you’re working with a SQLite database, so you’ll need to
   initialize a DbHelper to gain access to it.
    */
    private ScoreDbHelper scoreDbHelper;
    @Override
    public boolean onCreate() {
        scoreDbHelper = new ScoreDbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        final SQLiteDatabase db = scoreDbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor retCursor;
        switch (match){
            case SCORES:
                retCursor = db.query(ScoreContract.ScoreEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
        // Set a notification URI on the Cursor and return that Cursor
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);

        // Return the desired Cursor
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("update function not yet implemented");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        //get access to database
        SQLiteDatabase db = scoreDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match){
            case SCORES:
                long id = db.insert(ScoreContract.ScoreEntry.TABLE_NAME, null, contentValues);
                if (id > 0 ){
                    returnUri = ContentUris.withAppendedId(ScoreContract.ScoreEntry.CONTENT_URI, id);
                }
                else{
                    throw new SQLException("Unable to insert row into - " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);

        }
        //tell the resolver that uri may have changed
        // Notify the resolver if the uri has been changed, and return the newly inserted URI
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase db = scoreDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        int tasksDeleted;
        switch(match){
            case SCORES_WITH_ID:
                // Get the task ID from the URI path
                String id = uri.getPathSegments().get(1);
                tasksDeleted = db.delete(ScoreContract.ScoreEntry.TABLE_NAME, "_id=?", new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri : " + uri);
        }
        // Notify the resolver of a change and return the number of items deleted
        if (tasksDeleted != 0) {
            // A task was deleted, set notification
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return tasksDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        throw new UnsupportedOperationException("update function not yet implemented");
    }
}
