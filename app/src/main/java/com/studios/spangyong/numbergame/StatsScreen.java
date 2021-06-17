package com.studios.spangyong.numbergame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StatsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_screen);
        ConstraintLayout bgcolor = findViewById(R.id.statcolor);
        bgcolor.setBackgroundColor(Color.BLACK);
        try {
            SharedPreferences stats = getSharedPreferences("label", 0); //gets all saved data
            int gamesPlayed = stats.getInt("gameplayed", 0);
            int gamesLost = stats.getInt("gamelost", 0);
            int gamesWon = stats.getInt("gamewon", 0);
            int currentstreak = stats.getInt("streakcurrent", 0);
            int winstreak = stats.getInt("streakwin", 0);
            int losestreak = stats.getInt("streaklose", 0);

            if (gamesPlayed == 0) {
                TextView getplaying = findViewById(R.id.gamesplayedtxt);
                getplaying.setText("Hey! You haven't played the game yet!\nStats will become available once you've played a game!");
            }
            else {
                int wonpercent = (gamesWon*100)/gamesPlayed;
                int lostpercent = (gamesLost*100)/gamesPlayed;
                if (wonpercent + lostpercent < 100) {
                    wonpercent += 1;
                }
                else if (wonpercent + lostpercent > 100) {
                    wonpercent -= 1;
                }
                TextView txtgameplayed = findViewById(R.id.gamesplayedtxt);
                if (gamesPlayed == 1) {
                    txtgameplayed.setText(gamesPlayed + " game played");
                } else {
                    txtgameplayed.setText(gamesPlayed + " games played");
                }

                TextView txtgamewon = findViewById(R.id.gameswontxt);
                if (gamesWon == 1) {
                    txtgamewon.setText(gamesWon + " game won - " + wonpercent + "%");
                } else {
                    txtgamewon.setText(gamesWon + " games won - " + wonpercent + "%");
                }

                TextView txtgamelost = findViewById(R.id.gameslosttxt);
                if (gamesLost == 1) {
                    txtgamelost.setText(gamesLost + " game lost - " + lostpercent + "%");
                } else {
                    txtgamelost.setText(gamesLost + " games lost - " + lostpercent + "%");
                }

                TextView txtcurrentstreak = findViewById(R.id.currentstreaktxt);
                if (currentstreak < 0) {
                    int displayedcurrentstreak = currentstreak;
                    displayedcurrentstreak = displayedcurrentstreak - currentstreak;
                    displayedcurrentstreak = displayedcurrentstreak - currentstreak;
                    if (currentstreak == -1) {
                        txtcurrentstreak.setText("Current streak: " + displayedcurrentstreak + " loss");
                    }
                    else {
                        txtcurrentstreak.setText("Current streak: " + displayedcurrentstreak + " losses");
                    }
                } else {
                    if (currentstreak == 1) {
                        txtcurrentstreak.setText("Current streak: " + currentstreak + " win");
                    }
                    else {
                        txtcurrentstreak.setText("Current streak: " + currentstreak + " wins");
                    }
                }

                TextView txtwinstreak = findViewById(R.id.winstreak);
                TextView txtlosestreak = findViewById(R.id.losestreak);
                txtwinstreak.setText("Win streak: " + winstreak);
                int displayedlosestreak = losestreak;
                displayedlosestreak = displayedlosestreak - losestreak;
                displayedlosestreak = displayedlosestreak - losestreak;
                txtlosestreak.setText("Lose streak: " + displayedlosestreak);

            }
        }
        catch (Exception e) {
            TextView errortxt = findViewById(R.id.gamesplayedtxt);
            errortxt.setText("Unable to get your save files!\nHere is the error:\n" + e);
        }

    }

    @Override
    public void onBackPressed(){
        Intent goBack = new Intent(this, TitleScreen.class);
        startActivity(goBack);
        finish();
    }
}
