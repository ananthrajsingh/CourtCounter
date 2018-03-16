





package com.example.ananth.courtcounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Ananth on 2/6/2018.
 */

public class MainActivity extends AppCompatActivity {

//    public  final String TEAM_1 = getString(R.string.team1);
//    public  final String TEAM_2 = getString(R.string.team2);
//    public  final String TEAM_3 = getString(R.string.team3);
//    public final String TEAM_4 = getString(R.string.team4);
//    public final String TEAM_5 = getString(R.string.team5);
    private String selectedTeamA;
    private String selectedTeamB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv_team1 = (TextView) findViewById(R.id.tv_team1);
        final TextView tv_team2 = (TextView) findViewById(R.id.tv_team2);
        final TextView tv_team3 = (TextView) findViewById(R.id.tv_team3);
        final TextView tv_team4 = (TextView) findViewById(R.id.tv_team4);
        final TextView tv_team5 = (TextView) findViewById(R.id.tv_team5);

        tv_team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTeamA == null){
                    selectedTeamA = tv_team1.getText().toString();
                }
                else {
                    selectedTeamB = tv_team1.getText().toString();
                    Intent intent = new Intent(getBaseContext(), TeamsActivity.class);
                    intent.putExtra("TEAM_A",selectedTeamA);
                    intent.putExtra("TEAM_B", selectedTeamB);
                    selectedTeamA = null;
                    selectedTeamB = null;
                    startActivity(intent);
                }


            }
        });
        tv_team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTeamA == null){
                    selectedTeamA = tv_team2.getText().toString();
                }
                else {
                    selectedTeamB = tv_team2.getText().toString();
                    Intent intent = new Intent(getBaseContext(), TeamsActivity.class);
                    intent.putExtra("TEAM_A", selectedTeamA);
                    intent.putExtra("TEAM_B", selectedTeamB);
                    selectedTeamA = null;
                    selectedTeamB = null;
                    startActivity(intent);
                }


            }
        });
        tv_team3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTeamA == null){
                    selectedTeamA = tv_team3.getText().toString();
                }
                else {
                    selectedTeamB = tv_team3.getText().toString();
                    Intent intent = new Intent(getBaseContext(), TeamsActivity.class);
                    intent.putExtra("TEAM_A",selectedTeamA);
                    intent.putExtra("TEAM_B", selectedTeamB);
                    selectedTeamA = null;
                    selectedTeamB = null;
                    startActivity(intent);
                }


            }
        });
        tv_team4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTeamA == null){
                    selectedTeamA = tv_team4.getText().toString();
                }
                else {
                    selectedTeamB = tv_team4.getText().toString();
                    Intent intent = new Intent(getBaseContext(), TeamsActivity.class);
                    intent.putExtra("TEAM_A",selectedTeamA);
                    intent.putExtra("TEAM_B", selectedTeamB);
                    selectedTeamA = null;
                    selectedTeamB = null;
                    startActivity(intent);
                }


            }
        });
        tv_team5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTeamA == null){
                    selectedTeamA = tv_team5.getText().toString();
                }
                else {
                    selectedTeamB = tv_team5.getText().toString();
                    Intent intent = new Intent(getBaseContext(), TeamsActivity.class);
                    intent.putExtra("TEAM_A",selectedTeamA);
                    intent.putExtra("TEAM_B", selectedTeamB);
                    selectedTeamA = null;
                    selectedTeamB = null;
                    startActivity(intent);
                }


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if( id == R.id.action_scores_main){
            startActivity(new Intent(this, ScoresActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



