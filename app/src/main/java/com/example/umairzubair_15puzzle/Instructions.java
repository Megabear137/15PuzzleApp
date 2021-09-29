package com.example.umairzubair_15puzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
    }

    //Returns to the game screen when the exit button is pressed
    public void onClickExit(View View) {
        Intent i = new Intent(this, GameScreen.class);
        startActivity(i);
    }
}
