package com.example.ananth.courtcounter;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananth.courtcounter.Data.ScoreContract;

import static com.example.ananth.courtcounter.R.id.tv_teamB;

public class TeamsActivity extends AppCompatActivity {

    private TextView textViewScoreTeamA;
    private TextView textViewScoreTeamB;
    private TextView textViewNameTeamA;
    private TextView textViewNameTeamB;
    private int scoreTeamA;
    private int scoreTeamB;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.teams, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_scores_teams){
            startActivity(new Intent(this, ScoresActivity.class));
        }
        else if (id == R.id.action_save_teams){
            saveScoreToDatabase();
        }
        else if (id == R.id.action_refresh_teams){
            textViewScoreTeamA.setText("0");
            textViewScoreTeamB.setText("0");
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveScoreToDatabase(){
        int scoreTeamA = Integer.parseInt(textViewScoreTeamA.getText().toString());
        int scoreTeamB = Integer.parseInt(textViewScoreTeamB.getText().toString());
        int intNameTeamA = convertNameToInt(textViewNameTeamA.getText().toString());
        int intNameTeamB = convertNameToInt(textViewNameTeamB.getText().toString());

        ContentValues contentValues = new ContentValues();
        contentValues.put(ScoreContract.ScoreEntry.COLUMN_SCORE_A, scoreTeamA);
        contentValues.put(ScoreContract.ScoreEntry.COLUMN_SCORE_B, scoreTeamB);
        contentValues.put(ScoreContract.ScoreEntry.COLUMN_TEAM_A, intNameTeamA);
        contentValues.put(ScoreContract.ScoreEntry.COLUMN_TEAM_B, intNameTeamB);

        Uri uri = getContentResolver().insert(ScoreContract.ScoreEntry.CONTENT_URI, contentValues);
        // Display the URI that's returned with a Toast
        // [Hint] Don't forget to call finish() to return to MainActivity after this insert is complete
        if(uri != null) {
            Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
        }

        // Finish activity (this returns back to MainActivity)
        finish();
    }

    private int convertNameToInt(String stringName){
        int returnInt = 0;
        if (stringName.equals(this.getString(R.string.team1))){
            returnInt = 1;
        }
        if (stringName.equals(this.getString(R.string.team2))){
            returnInt = 2;
        }
        if (stringName.equals(this.getString(R.string.team3))){
            returnInt = 3;
        }
        if (stringName.equals(this.getString(R.string.team4))){
            returnInt = 4;
        }
        return returnInt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        String teamA = getIntent().getStringExtra("TEAM_A");
        String teamB = getIntent().getStringExtra("TEAM_B");

        textViewNameTeamA = (TextView) findViewById(R.id.tv_teamA);
        textViewNameTeamA.setText(teamA);
        textViewNameTeamB = (TextView) findViewById(tv_teamB);
        textViewNameTeamB.setText(teamB);


        textViewScoreTeamA = (TextView) findViewById(R.id.tv_score_teamA);
        textViewScoreTeamB = (TextView) findViewById(R.id.tv_score_teamB);
        textViewScoreTeamA.setText("0");
        textViewScoreTeamB.setText("0");

        TextView plusThreeTeamA = (TextView) findViewById(R.id.button_3_teamA);
        TextView plusTwoTeamA = (TextView) findViewById(R.id.button_2_teamA);
        TextView plusOneTeamA = (TextView) findViewById(R.id.button_1_teamA);
        TextView minusOneTeamA = (TextView) findViewById(R.id.button_neg1_teamA);

        TextView plusThreeTeamB = (TextView) findViewById(R.id.button_3_teamB);
        TextView plusTwoTeamB = (TextView) findViewById(R.id.button_2_teamB);
        TextView plusOneTeamB = (TextView) findViewById(R.id.button_1_teamB);
        TextView minusOneTeamB = (TextView) findViewById(R.id.button_neg1_teamB);


        plusThreeTeamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreTeamA += 3;
                textViewScoreTeamA.setText(Integer.toString(scoreTeamA));
            }
        });
        plusTwoTeamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreTeamA += 2;
                textViewScoreTeamA.setText(Integer.toString(scoreTeamA));
            }
        });
        plusOneTeamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreTeamA += 1;
                textViewScoreTeamA.setText(Integer.toString(scoreTeamA));
            }
        });
        minusOneTeamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreTeamA -= 1;
                textViewScoreTeamA.setText(Integer.toString(scoreTeamA));
            }
        });




        plusThreeTeamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreTeamB += 3;
                textViewScoreTeamB.setText(Integer.toString(scoreTeamB));
            }
        });
        plusTwoTeamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreTeamB += 2;
                textViewScoreTeamB.setText(Integer.toString(scoreTeamB));
            }
        });
        plusOneTeamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreTeamB += 1;
                textViewScoreTeamB.setText(Integer.toString(scoreTeamB));
            }
        });
        minusOneTeamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreTeamB -= 1;
                textViewScoreTeamB.setText(Integer.toString(scoreTeamB));
            }
        });





    }
}

