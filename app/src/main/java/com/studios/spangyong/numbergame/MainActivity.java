package com.studios.spangyong.numbergame;
import java.util.Random;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //gets difficulty from title screen
    //int difficulty = Integer.parseInt(diffstring)

    public static final String WONORNOT = "com.studios.spangyong.numbergame.WON";
    public static final String GUESSNUMBER = "com.studios.spangyong.numbergame.NUMBER";
    public static final String GUESSCOUNT = "com.studios.spangyong.numbergame.GUESSCOUNT";
    public static final String ERRORCODE = "con.studios.spangyong.numbergame.ERRORCODE";
    int guesses = 0;
    int maxguess = 10;
    int minguess = 1;
    int boilh = 0;
    int boill = 0;
    int vhot = 0;
    int hot = 0;
    int warm = 0;
    int cold = 0;
    int randnum = 0;
    int guessestook = 0;
    int txtlength = 0;

    String win = "false"; //string that detects is true if user has one, false if not. parsed to the game over screen. (booleans can not be easily parsed)


    @Override
    protected void onCreate(Bundle savedInstanceState) { //defines all the elements, checks the parsed difficulty and sets everything accordingly.
        //setNumbers()
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String diffstring = intent.getStringExtra(TitleScreen.DIFF);
        TextView guessText = (TextView) findViewById(R.id.guessPrint);
        int difficulty = Integer.parseInt(diffstring);
        guessText.setTextColor(Color.parseColor("#707070"));
        ConstraintLayout bgcolor = findViewById(R.id.bgcolor);
        bgcolor.setBackgroundColor(Color.BLACK);
        TextView guessprint = (TextView) findViewById(R.id._and_);
        if (difficulty == 1) {
            guesses = 5;
            //minguess = 1;
            maxguess = 10;
            boilh = 1;
            boill = -1;
            vhot = 2;
            hot = 3;
            warm = 5;
            cold = 7;
            txtlength = 3;
        }
        else if (difficulty == 2) {
            //minguess = 1;
            maxguess = 20;
            guesses = 4;
            boilh = 1;
            boill = -1;
            vhot = 3;
            hot = 7;
            warm = 9;
            cold = 12;
            txtlength = 3;
        }
        else if (difficulty == 3) {
            //minguess = 1;
            maxguess = 50;
            guesses = 5;
            boilh = 2;
            boill = -2;
            vhot = 6;
            hot = 10;
            warm = 15;
            cold = 20;
            txtlength = 3;
        }
        else if (difficulty == 4) {
            //minguess = 1;
            maxguess = 100;
            guesses = 7;
            boilh = 5;
            boill = -5;
            vhot = 10;
            hot = 20;
            warm = 50;
            cold = 70;
            txtlength = 4;
        }
        guessText.setText("You have " + guesses + " guesses.");
        guessprint.setText(minguess + " and " + maxguess + "?");

        gen();
        EditText numGuess = (EditText) findViewById(R.id.editText);
        numGuess.setInputType(InputType.TYPE_NULL);
        numGuess.setHintTextColor(Color.parseColor("#707070"));

    }

    public void gen() { //called as an easy way to generate a random number between 1 and maxnumber. (is a public void so can be used in other functions)
        Random rand = new Random();
        randnum = rand.nextInt(maxguess) +1;
    }

    @Override
    public void onBackPressed(){
        //Intent goBack = new Intent(this, TitleScreen.class);
        //startActivity(goBack);
        //finish();
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setPositiveButton("Give up", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences stats = getSharedPreferences("label", 0);
                int gamesPlayed = stats.getInt("gameplayed", 0);
                int gamesLost = stats.getInt("gamelost", 0);

                gamesPlayed++;
                gamesLost++;
                SharedPreferences.Editor editor = stats.edit();
                editor.putInt("gameplayed", gamesPlayed).commit();
                editor.putInt("gamelost", gamesLost).commit();
                gotogameover();
            }
        });

        builder.setNegativeButton("Never!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //cancelled
            }
        });
        builder.setTitle("Give up?");
        builder.setMessage("It will count as a loss!");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void gotogameover(){
        win = "false";
        String guessstring = Integer.toString(randnum);
        Intent gameOverScreen = new Intent(this, GameOver.class);
        gameOverScreen.putExtra(WONORNOT, win);
        gameOverScreen.putExtra(GUESSNUMBER, guessstring);
        startActivity(gameOverScreen);
        finish();
    }

    //public void setNumbers() {

   // }

    //EditText numGuess = (EditText) findViewById(R.id.editText);

    //these are the onclick actions for the keypad full of buttons
    //fairly obvious what these do
    //plan to change to switch case to make it more efficient down the line
    public void butt1(View view) {
        EditText numGuess = (EditText) findViewById(R.id.editText);
        if (numGuess.length() < txtlength) {
            numGuess.append("1");
        }
    }
    public void butt2(View view) {
        EditText numGuess = (EditText) findViewById(R.id.editText);
        if (numGuess.length() < txtlength) {
            numGuess.append("2");
        }
    }
    public void butt3(View view) {
        EditText numGuess = (EditText) findViewById(R.id.editText);
        if (numGuess.length() < txtlength) {
            numGuess.append("3");
        }
    }
    public void butt4(View view) {
        EditText numGuess = (EditText) findViewById(R.id.editText);
        if (numGuess.length() < txtlength) {
            numGuess.append("4");
        }
    }
    public void butt5(View view) {
        EditText numGuess = (EditText) findViewById(R.id.editText);
        if (numGuess.length() < txtlength) {
            numGuess.append("5");
        }
    }
    public void butt6(View view) {
        EditText numGuess = (EditText) findViewById(R.id.editText);
        if (numGuess.length() < txtlength) {
            numGuess.append("6");
        }
    }
    public void butt7(View view) {
        EditText numGuess = (EditText) findViewById(R.id.editText);
        if (numGuess.length() < txtlength) {
            numGuess.append("7");
        }
    }
    public void butt8(View view) {
        EditText numGuess = (EditText) findViewById(R.id.editText);
        if (numGuess.length() < txtlength) {
            numGuess.append("8");
        }
    }
    public void butt9(View view) {
        EditText numGuess = (EditText) findViewById(R.id.editText);
        if (numGuess.length() < txtlength) {
            numGuess.append("9");
        }
    }
    public void butt0(View view) {
        EditText numGuess = (EditText) findViewById(R.id.editText);
        if (numGuess.length() < txtlength) {
            numGuess.append("0");
        }
    }

    public void buttdel(View view) {
        EditText numGuess = (EditText) findViewById(R.id.editText);
        if (numGuess.length() != 0) {
            numGuess.setText(numGuess.getText().delete(numGuess.getText().length()-1, numGuess.getText().length()));
        }
    }
    //}


    public void sendGuess(View view) {//the button calls this function. checks to see if the guess is higher or lower than the number. then calls the other check.
        int prevnum = 0;
        ConstraintLayout bgcolor = findViewById(R.id.bgcolor);
        TextView guessText = (TextView) findViewById(R.id.guessPrint);
        TextView lohiText = (TextView) findViewById(R.id.textView2);
        TextView guessprint = (TextView) findViewById(R.id._and_);
        lohiText.setTextColor(Color.BLACK);
        guessText.setTextColor(Color.parseColor("#8a8a8a"));
        bgcolor.setBackgroundColor(Color.BLACK);
        EditText numGuess = (EditText) findViewById(R.id.editText);
        numGuess.setTextColor(Color.WHITE);
        numGuess.setHintTextColor(Color.parseColor("#707070"));
        try { //catches any errors (putting nothing in the box counts as a string)
            int message = Integer.parseInt(numGuess.getText().toString());
            prevnum = message;
            guessprint.setText("Previous number: " + prevnum);
            if (message < minguess || message > maxguess) {
                guessprint.setText("");
                lohiText.setText("Enter guess between " + minguess + " and " + maxguess + "!!");
                numGuess.setText("");
            }
            else if (message == randnum){
                guessText.setTextColor(Color.BLACK);
                numGuess.setTextColor(Color.BLACK);
                numGuess.setHintTextColor(Color.BLACK);
                lohiText.setText("Well done, you got it right!");
                numGuess.setText("");
                guessestook++;
                win = "true";
                checkGuess();
            }
            else if (message >= randnum - boilh && message <= randnum + boilh) {
                lohiText.setText("Boiling!");
                bgcolor.setBackgroundColor(Color.parseColor("#8b0000"));
                //lohiText.setTextColor(Color.WHITE);
                guessText.setTextColor(Color.WHITE);
                numGuess.setTextColor(Color.WHITE);
                numGuess.setHintTextColor(Color.WHITE);
                numGuess.setText("");
                guesses--;
                guessestook++;
                checkGuess();
            }
            else if (message >= randnum - vhot && message <= randnum + vhot) {
                lohiText.setText("Very hot!");
                //lohiText.setTextColor(Color.WHITE);
                guessText.setTextColor(Color.BLACK);
                numGuess.setTextColor(Color.BLACK);
                numGuess.setHintTextColor(Color.BLACK);
                bgcolor.setBackgroundColor(Color.parseColor("#cc6600"));
                numGuess.setText("");
                guesses--;
                guessestook++;
                checkGuess();
            }
            else if (message >= randnum - hot && message <= randnum + hot) {
                lohiText.setText("Hot.");
                bgcolor.setBackgroundColor(Color.parseColor("#e69500"));
                //lohiText.setTextColor(Color.BLACK);
                guessText.setTextColor(Color.WHITE);
                numGuess.setTextColor(Color.WHITE);
                numGuess.setHintTextColor(Color.WHITE);
                numGuess.setText("");
                guesses--;
                guessestook++;
                checkGuess();
            }
            else if (message >= randnum - cold && message <= randnum + cold) {
                lohiText.setText("Cold.");
                bgcolor.setBackgroundColor(Color.parseColor("#0099cc"));
                //lohiText.setTextColor(Color.WHITE);
                guessText.setTextColor(Color.WHITE);
                numGuess.setTextColor(Color.WHITE);
                numGuess.setHintTextColor(Color.WHITE);
                numGuess.setText("");
                guesses--;
                guessestook++;
                checkGuess();
            }
            else {
                lohiText.setText("Freezing.. Brr..");
                bgcolor.setBackgroundColor(Color.parseColor("#00cddf"));
                //lohiText.setTextColor(Color.WHITE);
                guessText.setTextColor(Color.WHITE);
                numGuess.setTextColor(Color.WHITE);
                numGuess.setHintTextColor(Color.WHITE);
                numGuess.setText("");
                guesses--;
                guessestook++;
                checkGuess();
            }
        }
        catch (NumberFormatException e){
            guessprint.setText("");
            lohiText.setText("Please enter a number into the box.");
        }
        if (guesses == 1) {
            guessText.setText("You have " + guesses + " guess.");
        }
        else {
            guessText.setText("You have " + guesses + " guesses.");
        }

    }

    public void checkGuess() {//this calls the new intent, and checks to see if guesses=0.
        if (win.equals("true")) {
            try {
                SharedPreferences stats = getSharedPreferences("label", 0);
                int gamesPlayed = stats.getInt("gameplayed", 0);
                int gamesWon = stats.getInt("gamewon", 0);
                int currentstreak = stats.getInt("streakcurrent", 0);
                int winstreak = stats.getInt("streakwin", 0);

                if (currentstreak <= 0) {
                    currentstreak = 0;
                    currentstreak++;
                }
                else if (currentstreak > 0) {
                    currentstreak++;
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage("Hey! You shouldn't be seeing this! If you do, there was an issue with the current streak!");
                    builder.show();
                }

                if (winstreak < currentstreak) {
                    winstreak = currentstreak;
                }

                gamesPlayed++;
                gamesWon++;
                SharedPreferences.Editor editor = stats.edit();
                editor.putInt("gameplayed", gamesPlayed).commit();
                editor.putInt("gamewon", gamesWon).commit();
                editor.putInt("streakcurrent", currentstreak).commit();
                editor.putInt("streakwin", winstreak).commit();

                //AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //builder.setTitle(currentstreak);
                //builder.show();

            }
            catch (Exception e) {
                Context context = getApplicationContext();
                CharSequence errormsg = "Unable to save!! " + e;
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, errormsg, duration);
                toast.show();
            }
            String guessstring = Integer.toString(randnum);
            String guessescount = Integer.toString(guessestook);
            Intent gameOverScreen = new Intent(this, GameOver.class);
            gameOverScreen.putExtra(WONORNOT, win);
            gameOverScreen.putExtra(GUESSNUMBER, guessstring);
            gameOverScreen.putExtra(GUESSCOUNT, guessescount);
            startActivity(gameOverScreen);
            finish();
        }
        else if (guesses == 0) {
            try {
                SharedPreferences stats = getSharedPreferences("label", 0);
                int gamesPlayed = stats.getInt("gameplayed", 0);
                int gamesLost = stats.getInt("gamelost", 0);
                int currentstreak = stats.getInt("streakcurrent", 0);
                int losestreak = stats.getInt("streaklose", 0);

                gamesPlayed++;
                gamesLost++;
                if (currentstreak >= 0) {
                    currentstreak = 0;
                    currentstreak--;
                }
                else if (currentstreak <0) {
                    currentstreak--;
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage("Hey! You shouldn't be seeing this! If you do, there was an issue with the current streak!");
                }

                if (losestreak > currentstreak) {
                    losestreak = currentstreak;
                }
                SharedPreferences.Editor editor = stats.edit();
                editor.putInt("gameplayed", gamesPlayed).commit();
                editor.putInt("gamelost", gamesLost).commit();
                editor.putInt("streakcurrent", currentstreak).commit();
                editor.putInt("streaklose", losestreak).commit();

            }
            catch (Exception e) {
                Context context = getApplicationContext();
                CharSequence errormsg = "Unable to save!! " + e;
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, errormsg, duration);
                toast.show();
            }
            win = "false";
            String guessstring = Integer.toString(randnum);
            Intent gameOverScreen = new Intent(this, GameOver.class);
            gameOverScreen.putExtra(WONORNOT, win);
            gameOverScreen.putExtra(GUESSNUMBER, guessstring);
            startActivity(gameOverScreen);
            finish();
        }
    }
}

