package com.studios.spangyong.numbergame;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent gameOverScreen = getIntent();
        String win = gameOverScreen.getStringExtra(MainActivity.WONORNOT);
        String guessstring = gameOverScreen.getStringExtra(MainActivity.GUESSNUMBER);
        String guesscount = gameOverScreen.getStringExtra(MainActivity.GUESSCOUNT); //This is the first statistic out of more that I plan to program in.
        TextView gameOvrtxt = findViewById(R.id.gameOverTxt);
        TextView gotright = findViewById(R.id.uGotItRight);
        TextView correctnum = findViewById(R.id.correctNum);
        ConstraintLayout bgcolor = findViewById(R.id.bgcolor);
        bgcolor.setBackgroundColor(Color.parseColor("#FFA200"));
        if (win.equals("true")) { //checks to see if win(passed from previous intent) equals true and sets text accordingly
            gameOvrtxt.setText("Well done!");
            gotright.setText("You got it right!");
            if (guesscount.equals("1")) {
                correctnum.setText("You took " + guesscount + " guess.");
            }
            else {
                correctnum.setText("You took " + guesscount + " guesses.");
            }
        }
        else if (win.equals("false")) {
            gameOvrtxt.setText("Game over!");
            gotright.setText("You got it wrong! :(");
            correctnum.setText("The correct number was " + guessstring + "!");
        }
        else {
            gameOvrtxt.setText("The was an error. I don't know why though.. hmm..");
        }
    }

    @Override
    public void onBackPressed(){ //checks to see if the back button is pressed (physical back button)
        SharedPreferences stats = getSharedPreferences("label", 0); //gets all saved data
        int gamesPlayed = stats.getInt("gameplayed", 0);
        if (gamesPlayed == 1000) {
            AlertDialog.Builder builder = new AlertDialog.Builder(GameOver.this);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent statsload = new Intent(GameOver.this, StatsScreen.class);
                    startActivity(statsload);
                    finish();
                }
            });
            builder.setTitle("Well done!");
            builder.setMessage("You reached 1000 games! You've played a lot!");
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            Intent goBack = new Intent(this, TitleScreen.class);
            startActivity(goBack);
            finish();
        }
    }

    public void goHome(View view){ //checks to see if the back button is pressed (ingame button)
        SharedPreferences stats = getSharedPreferences("label", 0); //gets all saved data
        int gamesPlayed = stats.getInt("gameplayed", 0);
        if (gamesPlayed == 1000) {
            AlertDialog.Builder builder = new AlertDialog.Builder(GameOver.this);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent statsload = new Intent(GameOver.this, StatsScreen.class);
                    startActivity(statsload);
                    finish();
                }
            });
            builder.setTitle("Well done!");
            builder.setMessage("You reached 1000 games! You've played a lot!");
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            Intent goBack = new Intent(this, TitleScreen.class);
            startActivity(goBack);
            finish();
        }
    }
}
