package com.studios.spangyong.numbergame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TitleScreen extends AppCompatActivity {
    public static final String DIFF = "com.studios.spangyong.numbergame.DIFF";
    int difficulty = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);
        bgColorSet();
    }

    public void bgColorSet() { //programmed way of changing the background and text colors to black (using xml is quite difficult)
        ConstraintLayout bgcolor = findViewById(R.id.bgcolor);
        bgcolor.setBackgroundColor(Color.BLACK);
        TextView titletext = (TextView) findViewById(R.id.textView);
        titletext.setTextColor(Color.BLACK);
        TextView extratext = (TextView) findViewById(R.id.textView3);
        extratext.setTextColor(Color.parseColor("#707070"));
    }

    //these are the procedures that the buttons are linked to
    //they have the same code but change the difficulty
    //most likely possible to have all the code afterwards except the difficulty

    public void veryeasy(View view) {
        Intent maingame = new Intent(this, MainActivity.class);
        difficulty = 1;
        String diffstring = Integer.toString(difficulty);
        maingame.putExtra(DIFF, diffstring);
        startActivity(maingame);
        finish();
    }
    public void easy(View view) {
        Intent maingame = new Intent(this, MainActivity.class);
        difficulty = 2;
        String diffstring = Integer.toString(difficulty);
        maingame.putExtra(DIFF, diffstring);
        startActivity(maingame);
        finish();
    }
    public void normal(View view) {
        Intent maingame = new Intent(this, MainActivity.class);
        difficulty = 3;
        String diffstring = Integer.toString(difficulty);
        maingame.putExtra(DIFF, diffstring);
        startActivity(maingame);
        finish();
    }
    public void hard(View view) {
        Intent maingame = new Intent(this, MainActivity.class);
        difficulty = 4;
        String diffstring = Integer.toString(difficulty);
        maingame.putExtra(DIFF, diffstring);
        startActivity(maingame);
        finish();
    }
    public void statsstart(View view) {
        Intent goBack = new Intent(this, StatsScreen.class);
        startActivity(goBack);
        finish();
    }
    public void how2play(View view) {
        Intent how2play = new Intent(this, HowToPlay.class);
        startActivity(how2play);
        finish();
    }

    @Override
    public void onBackPressed(){
        finish();
    } //quits the app if back button pressed
}
