package com.studios.spangyong.numbergame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HowToPlay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
    }

    public void back(View view) {
        Intent goBack = new Intent(this, TitleScreen.class);
        startActivity(goBack);
        finish();
    }

    @Override
    public void onBackPressed(){
        Intent goBack = new Intent(this, TitleScreen.class);
        startActivity(goBack);
        finish();
    }
}
