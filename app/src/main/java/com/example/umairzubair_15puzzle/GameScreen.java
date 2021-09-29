package com.example.umairzubair_15puzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.*;

public class GameScreen extends AppCompatActivity {

    //Holds the textbox which holds the number of moves
    TextView count;

    //Holds the number of moves
    int counter = -1;

    //Holds the current position of the screen and its numbers
    int[][] intGame = new int[4][4];

    //Holds the button grid of the game
    Button[][] btnGame = new Button[4][4];

    //Used to create a random order of numbers 0 to 15
    ArrayList<Integer> intNums;

    //Holds the timer
    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        //Sets the timer on the screen
        chronometer = findViewById(R.id.chronometer);

        //Sets the textbox which holds the number of moves
        count = (TextView) findViewById(R.id.Moves);

        //Calls the setGame() method
        setGame();

        //Calls the setButton() method
        setButton();

        //Calls the updateGame() method
        updateGame();

        //Starts the timer when the gameScreen is opened
        chronometer.setBase(SystemClock.elapsedRealtime());

        //Starts the timer running
        chronometer.start();
    }

    //Sets the screen to the main screen when the exit button is pressed
    public void toFirst(View View) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    //Opens the instructions screen when the Inst button is pressed
    public void onClickInstruction(View View) {
        Intent i = new Intent(this, Instructions.class);
        startActivity(i);
    }

    //Rescrambles the game grid when the restart button is pressed and resets the timer and the number of moves
    public void Restart(View view){
        setGame();
        updateGame();
        counter = 0;
        count.setText(counter + "");
        chronometer.setBase(SystemClock.elapsedRealtime());
    }

    //Creates an array list of numbers 0-15, then scrambles them, and then adds them to a 2 dimensional array, intGame,
    //which holds the current position of the game screen
    public void setGame(){
        intNums = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            intNums.add(i);
        }

        Collections.shuffle(intNums);

        int intIndex = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                intGame[i][j] = intNums.get(intIndex);
                intIndex++;
            }
        }


    }

    //Adds all the buttons on the screen to a 2 dimensional array
    public void setButton(){
        btnGame[0][0] = findViewById(R.id.button1);
        btnGame[0][1] = findViewById(R.id.button2);
        btnGame[0][2] = findViewById(R.id.button3);
        btnGame[0][3] = findViewById(R.id.button4);
        btnGame[1][0] = findViewById(R.id.button5);
        btnGame[1][1] = findViewById(R.id.button6);
        btnGame[1][2] = findViewById(R.id.button7);
        btnGame[1][3] = findViewById(R.id.button8);
        btnGame[2][0] = findViewById(R.id.button9);
        btnGame[2][1] = findViewById(R.id.button10);
        btnGame[2][2] = findViewById(R.id.button11);
        btnGame[2][3] = findViewById(R.id.button12);
        btnGame[3][0] = findViewById(R.id.button13);
        btnGame[3][1] = findViewById(R.id.button14);
        btnGame[3][2] = findViewById(R.id.button15);
        btnGame[3][3] = findViewById(R.id.button16);

    }

    //Compares the intGame 2D array to the btnGame 2D array, and displays the number in that position to that
    //location in the btnGame array. This method is called everytime a number is moved, so it also increases the move
    //count by 1.
    public void updateGame(){

        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4; j++) {
                btnGame[i][j].setText(intGame[i][j] + "");
                if(intGame[i][j] == 0)
                    btnGame[i][j].setText("");
            }
        }

        counter++;
        count.setText(counter + "");
        winCheck();
    }

    //Creates a 2D array which holds the winning positon of the game, and then uses a nested for loop to go through
    //the game array and the winning array. If any position is not the same, the equalsCheck boolean is set to false.
    //After the for loop runs, the equalsCheck boolean is checked. If it is true, the timer is stopped and the winning screen
    //is opened. The current time and number of moves is also sent to the winning screen
    public void winCheck(){
        int[][] intWinGame = {{1, 2, 3, 4},
                              {5, 6, 7, 8},
                              {9, 10, 11, 12},
                              {13, 14, 15, 0}};

        boolean equalsCheck = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(intGame[i][j] != intWinGame[i][j])
                    equalsCheck = false;
            }
        }

        if(equalsCheck){
            chronometer.stop();
            Intent i = new Intent(this, Winner.class);
            String TotalCount = count.getText().toString();
            String TotalTimer  = chronometer.getText().toString();
            i.putExtra("count",TotalCount);
            i.putExtra("timer",TotalTimer);
            startActivity(i);
        }
    }

    //This method is used to move a number in the 2D array into an empty spot if it is available. This
    //runs whenever a number is pressed by the user
    public void changeTile(int x, int y){
        //These if statements check 4 position relative to the given coordiante. Above, below, to the right, and to the left.
        //If any of those spots are open( is a 0) those 2 numbers are swapped.
        if(x > 0 && intGame[x - 1][y]== 0) {
            intGame[x-1][y] = intGame[x][y];
            intGame[x][y] = 0;
            updateGame();
        }
        else if(x < 3 && intGame[x + 1][y]== 0) {
            intGame[x + 1][y] = intGame[x][y];
            intGame[x][y] = 0;
            updateGame();
        }
        else if(y > 0 && intGame[x][y - 1]== 0) {
            intGame[x][y - 1] = intGame[x][y];
            intGame[x][y] = 0;
            updateGame();
        }
        else if(y < 3 && intGame[x][y + 1]== 0) {
            intGame[x][y + 1]  = intGame[x][y];
            intGame[x][y] = 0;
            updateGame();
        }
    }

    //These methods are used to determine which button has been pressed, and sends the
    //coordianates of that button to the changeTile method, which will then update that position.
    public void buttonClick1(View view){
        changeTile(0,0);
    }
    public void buttonClick2(View view){
        changeTile(0,1);
    }
    public void buttonClick3(View view){
        changeTile(0,2);
    }
    public void buttonClick4(View view){
        changeTile(0,3);
    }
    public void buttonClick5(View view){
        changeTile(1,0);
    }
    public void buttonClick6(View view){
        changeTile(1,1);
    }
    public void buttonClick7(View view){
        changeTile(1,2);
    }
    public void buttonClick8(View view){
        changeTile(1,3);
    }
    public void buttonClick9(View view){
        changeTile(2,0);
    }
    public void buttonClick10(View view){
        changeTile(2,1);
    }
    public void buttonClick11(View view){
        changeTile(2,2);
    }
    public void buttonClick12(View view){
        changeTile(2,3);
    }
    public void buttonClick13(View view){
        changeTile(3,0);
    }
    public void buttonClick14(View view){
        changeTile(3,1);
    }
    public void buttonClick15(View view){
        changeTile(3,2);
    }
    public void buttonClick16(View view){
        changeTile(3,3);
    }


}
