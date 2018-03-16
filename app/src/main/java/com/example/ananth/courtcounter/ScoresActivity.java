package com.example.ananth.courtcounter;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.example.ananth.courtcounter.Data.ScoreContract;

/**
 * Created by Ananth on 2/9/2018.
 */

public class ScoresActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int SCORES_LOADER_ID = 0;

    RecyclerView mRecyclerView;
    private ScoresAdapter mScoresAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_scores);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mScoresAdapter = new ScoresAdapter(this);
        mRecyclerView.setAdapter(mScoresAdapter);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int id = (int) viewHolder.itemView.getTag();
                String stringId = Integer.toString(id);
                Uri uri = ScoreContract.ScoreEntry.CONTENT_URI;
                uri = uri.buildUpon().appendPath(stringId).build();
                getContentResolver().delete(uri , null, null);
                getSupportLoaderManager().restartLoader(SCORES_LOADER_ID, null, ScoresActivity.this);
            }
        }).attachToRecyclerView(mRecyclerView);

        getSupportLoaderManager().initLoader(SCORES_LOADER_ID, null, this);
    }

    public static final int INDEX_TEAM_A_NAME = 0;
    public static final int INDEX_TEAM_B_NAME = 1;
    public static final int INDEX_TEAM_A_SCORE = 2;
    public static final int INDEX_TEAM_B_SCORE = 3;

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Cursor>(this) {

            Cursor mScoresData;

            @Override
            protected void onStartLoading() {
                if (mScoresData != null){
                    deliverResult(mScoresData);
                }
                else {
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground() {
                try{
                    return getContentResolver().query(ScoreContract.ScoreEntry.CONTENT_URI,
                            null,
                            null,
                            null,
                            null);
                } catch(Exception e){
                    e.printStackTrace();
                    Log.e("ScoresActivity", "failed to load data asynchronously");
                    return null;
                }
            }
            public void deliverResult(Cursor data) {
                mScoresData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mScoresAdapter.swap(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
