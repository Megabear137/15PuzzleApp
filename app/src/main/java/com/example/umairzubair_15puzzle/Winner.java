package com.example.umairzubair_15puzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

public class Winner extends AppCompatActivity {

    //Holds the time
    private Chronometer chronometer;

    //Holds the number of moves
    TextView Counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        //Sets the text box which holds the time and number of moves
        chronometer = findViewById(R.id.chronometer2);
        Counter = findViewById(R.id.Moves2);
        //Collects the time and count from the game screen using the key word
        String moves = getIntent().getStringExtra("count");
        String timer = getIntent().getStringExtra("timer");
        //Sets the time and moves to the text box
        chronometer.setText(timer + "");
        Counter.setText(moves + "");
    }

    //Returns to the game screen when the restart button is pressed
    public void onClickRestart(View View){
        Intent i = new Intent(this, GameScreen.class);
        startActivity(i);
    }
    //Returns to the main screen when the exit button is pressed
    public void onClickExit(View View){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
