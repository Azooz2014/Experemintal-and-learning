package com.example.android.practiceset2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/* Team A Control*/

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void ButtonPlus3(View view) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreTeamA = scoreTeamA + 3;
        scoreView.setText("" + scoreTeamA);
    }

    public void ButtonPlus2(View view) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreTeamA = scoreTeamA + 2;
        scoreView.setText("" + scoreTeamA);
    }

    public void ButtonFreeThrow(View view) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreTeamA = scoreTeamA + 1;
        scoreView.setText("" + scoreTeamA);
    }

    /*Team B Control*/

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void ButtonPlus3b(View view) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreTeamB = scoreTeamB + 3;
        scoreView.setText("" + scoreTeamB);
    }

    public void ButtonPlus2b(View view) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreTeamB = scoreTeamB + 2;
        scoreView.setText("" + scoreTeamB);
    }

    public void ButtonFreeThrowb(View view) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreTeamB = scoreTeamB + 1;
        scoreView.setText("" + scoreTeamB);
    }

    public void ResetButton(View v){
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }
}
