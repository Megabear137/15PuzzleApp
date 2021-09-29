package com.example.umairzubair_15puzzle;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //This method opens the game screen when the start button is pressed
    public void startGame(View view){
        Intent i = new Intent(this, GameScreen.class);
        startActivity(i);
    }
}
