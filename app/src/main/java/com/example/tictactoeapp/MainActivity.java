package com.example.tictactoeapp;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    private final Context context = this;

    // Initialize combinations to check user win
    private final List<int[]> combinationsList = new ArrayList<>();

    // every box position will be acquired by either Player One or Player Two
    private int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    // playerTurn = 1 means Player One is turn and playerTurn = 2 means Player Two is turn
    private int playerTurn = 1;

    // total selected boxes either by Player One or Player Two
    private int totalSelectedBoxes = 1;

    private LinearLayout playerOneLayout, playerTwoLayout;
    private ImageView imageOne, imageTwo, imageThree, imageFour, imageFive, imageSix, imageSeven, imageEight, imageNine;
    private TextView playerOneName, playerTwoName;

    Gson gson = new GsonBuilder().create();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // playing sound in this screen
        playSound(R.raw.bouncing_ball);

        // Initialize widgets from activity_main.xml file
        playerOneLayout = findViewById(R.id.player1Layout);
        playerTwoLayout = findViewById(R.id.player2Layout);
        playerOneName = findViewById(R.id.player1TV);
        playerTwoName = findViewById(R.id.player2TV);
        imageOne = findViewById(R.id.image1);
        imageTwo = findViewById(R.id.image2);
        imageThree = findViewById(R.id.image3);
        imageFour = findViewById(R.id.image4);
        imageFive = findViewById(R.id.image5);
        imageSix = findViewById(R.id.image6);
        imageSeven = findViewById(R.id.image7);
        imageEight = findViewById(R.id.image8);
        imageNine = findViewById(R.id.image9);

        // add all possible winning combinations to List
        combinationsList.add(new int[]{0, 1, 2});
        combinationsList.add(new int[]{3, 4, 5});
        combinationsList.add(new int[]{6, 7, 8});
        combinationsList.add(new int[]{0, 3, 6});
        combinationsList.add(new int[]{1, 4, 7});
        combinationsList.add(new int[]{2, 5, 8});
        combinationsList.add(new int[]{2, 4, 6});
        combinationsList.add(new int[]{0, 4, 8});

        // Get Player Names from AddPlayers.java activity via intent
        final String firstPlayerName = getIntent().getStringExtra("playerOne");
        final String secondPlayerName = getIntent().getStringExtra("playerTwo");

        // Set Player Names to TextViews
        playerOneName.setText(firstPlayerName);
        playerTwoName.setText(secondPlayerName);

        imageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if this box is not selected before neither Player One or player Two
                if (isBoxSelectable(0)) {

                    // convert View To ImageView
                    performAction((ImageView) v, 0);
                }
            }
        });
        imageTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if this box is not selected before neither Player One or player Two
                if (isBoxSelectable(1)) {

                    // convert View To ImageView
                    performAction((ImageView) v, 1);
                }
            }
        });
        imageThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if this box is not selected before neither Player One or player Two
                if (isBoxSelectable(2)) {

                    // convert View To ImageView
                    performAction((ImageView) v, 2);
                }
            }
        });
        imageFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if this box is not selected before neither Player One or player Two
                if (isBoxSelectable(3)) {

                    // convert View To ImageView
                    performAction((ImageView) v, 3);
                }
            }
        });
        imageFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if this box is not selected before neither Player One or player Two
                if (isBoxSelectable(4)) {

                    // convert View To ImageView
                    performAction((ImageView) v, 4);
                }
            }
        });
        imageSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if this box is not selected before neither Player One or player Two
                if (isBoxSelectable(5)) {

                    // convert View To ImageView
                    performAction((ImageView) v, 5);
                }
            }
        });
        imageSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if this box is not selected before neither Player One or player Two
                if (isBoxSelectable(6)) {

                    // convert View To ImageView
                    performAction((ImageView) v, 6);
                }
            }
        });
        imageEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if this box is not selected before neither Player One or player Two
                if (isBoxSelectable(7)) {

                    // convert View To ImageView
                    performAction((ImageView) v, 7);
                }
            }
        });
        imageNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if this box is not selected before neither Player One or player Two
                if (isBoxSelectable(8)) {

                    // convert View To ImageView
                    performAction((ImageView) v, 8);
                }
            }
        });


        SharedPreferences sh = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String s1 = sh.getString("history", "{items:[]}");
        HistoryHolder holder = gson.fromJson(s1, HistoryHolder.class);
        HistoryPojo item = new HistoryPojo();
        item.player1= playerOneName.getText().toString();
        item.player2= playerTwoName.getText().toString();
        if(checkPlayerWin()){
            item.winner = playerOneName.getText().toString();
        }else{
            item.winner = playerTwoName.getText().toString();

        }
        holder.items.add(item);
        SharedPreferences.Editor myEdit = sh.edit();
        myEdit.putString("history", gson.toJson(holder));
        myEdit.commit();
    }



    /**
     * @param imageView           image box on which we are going to set cross or zero image according to player turn
     * @param selectedBoxPosition user selected box position to make this box non selectable again
     */
    private void performAction(ImageView imageView, int selectedBoxPosition) {

        // acquire box position by player who's turn (either 1 or 2)
        boxPositions[selectedBoxPosition] = playerTurn;

        // Check if its Player One is turn then set cross image to ImageView else set zero to ImageView
        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.close_thick);

            // checkPlayerWin function will return true if Player One win
            if (checkPlayerWin()) {

                // show win dialog with a message along with winner name
                final WinDialog winDialog = new WinDialog(MainActivity.this, playerOneName.getText().toString() + " has won the game");
                winDialog.show();
            } else if (totalSelectedBoxes == 9) { // over this game if there is no box left to be select

                // show win dialog with 'draw' message
                final WinDialog winDialog = new WinDialog(MainActivity.this, "It is a Draw!");
                winDialog.show();

            } else {

                // set Player Two as next turn
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }

        } else {
            imageView.setImageResource(R.drawable.circle);

            // checkPlayerWin function will return true if Player Two win
            if (checkPlayerWin()) {

                // show win dialog with a message
                final WinDialog winDialog = new WinDialog(MainActivity.this, playerTwoName.getText().toString() + " has won the game");
                winDialog.setCancelable(false);
                winDialog.show();
            } else if (totalSelectedBoxes == 9) { // over this game if there is no box left to be select

                // show win dialog with 'draw' message
                final WinDialog winDialog = new WinDialog(MainActivity.this, "It is a Draw!");
                winDialog.setCancelable(false);
                winDialog.show();

            } else {
                // set Player One as next turn
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }

        }
    }

    /**
     * @param currentPlayerTurn new player's turn either 1 or 2
     */
    private void changePlayerTurn(int currentPlayerTurn) {

        // select other player.
        playerTurn = currentPlayerTurn;

        //Change Layout Design according to selected player
        if (playerTurn == 1) {
            playerOneLayout.setBackgroundResource(R.drawable.round_bg_dark_purple); // select Player One
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_purple_20); // deSelect Player Two
        } else {
            playerTwoLayout.setBackgroundResource(R.drawable.round_bg_dark_purple); // select Player Two
            playerOneLayout.setBackgroundResource(R.drawable.round_back_purple_20); // deSelect Player One
        }
    }

    /**
     * @param boxPosition current selected box position
     */
    private boolean isBoxSelectable(int boxPosition) {
        boolean response = false;

        if (boxPositions[boxPosition] == 0) {
            response = true;
        }

        return response;
    }

    public boolean checkPlayerWin() {

        boolean response = false;
        for (int i = 0; i < combinationsList.size(); i++) {
            final int[] combination = combinationsList.get(i);
            if (boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn && boxPositions[combination[2]] == playerTurn) {
                response = true;
            }
        }
        return response;
    }

    public void startMatchAgain() {

        // reset all boxes positions which acquired by players in previous match
        boxPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

        // set Player One as first turn
        playerTurn = 1;

        totalSelectedBoxes = 1;

        // set empty transparent image to Every Box
        imageOne.setImageResource(R.drawable.transparent_bg);
        imageTwo.setImageResource(R.drawable.transparent_bg);
        imageThree.setImageResource(R.drawable.transparent_bg);
        imageFour.setImageResource(R.drawable.transparent_bg);
        imageFive.setImageResource(R.drawable.transparent_bg);
        imageSix.setImageResource(R.drawable.transparent_bg);
        imageSeven.setImageResource(R.drawable.transparent_bg);
        imageEight.setImageResource(R.drawable.transparent_bg);
        imageNine.setImageResource(R.drawable.transparent_bg);

    }

    @Override
    public void onPause() {
        // play background music
        super.onPause();
        Intent backgroundMusic = new Intent(MainActivity.this, SoundService.class);
        stopService(backgroundMusic);
    }

    @Override
    public void onDestroy() {
        // play background musicA
        super.onDestroy();
        Intent backgroundMusic = new Intent(MainActivity.this, SoundService.class);
        stopService(backgroundMusic);
        finish();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        // playing sound in this screen
        playSound(R.raw.bouncing_ball);
    }

    public void playSound(int raw) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context.getApplicationContext(), raw);
        }
        mediaPlayer.start();
        mediaPlayer = null;
    }

}