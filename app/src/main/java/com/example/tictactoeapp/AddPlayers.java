package com.example.tictactoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlayers extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        // play background music
        Intent backgroundMusic = new Intent(AddPlayers.this, SoundService.class);
        startService(backgroundMusic);

        // Initialize widgets from activity_add_players
        final EditText playerOne = findViewById(R.id.playerOne);
        final EditText playerTwo = findViewById(R.id.playerTwo);
        final AppCompatButton startBtn = findViewById(R.id.startGameBtn);

        // Click listener when user press Start Game Button
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get Values of players from EditText to a String Variables
                final String playerOneName = playerOne.getText().toString();
                final String playerTwoName = playerTwo.getText().toString();

                // Check if user not entered Player Names
                if (playerOneName.isEmpty() || playerTwoName.isEmpty()) {
                    Toast.makeText(AddPlayers.this, "Please enter Player Names", Toast.LENGTH_SHORT).show();
                } else {

                    // Create Object of Intent class to Open GameView Screen (MainActivity.java)
                    Intent intent = new Intent(AddPlayers.this, MainActivity.class);

                    // put both players names along with intent
                    intent.putExtra("playerOne", playerOneName);
                    intent.putExtra("playerTwo", playerTwoName);

                    // open MainActivity.java Activity
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try{
            getMenuInflater().inflate(R.menu.add_player_activity_menu, menu);
            return true;
        }catch (RuntimeException e){
            System.out.println("App Crashed due to: " + e);
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.history_view){
            Intent intent = new Intent(this, HistoryList.class);
            startActivityForResult(intent, 0);
            return true;
        }
        return false;
    }

    @Override
    public void onPause() {
        // play background music
        super.onPause();
        Intent backgroundMusic = new Intent(AddPlayers.this, SoundService.class);
        stopService(backgroundMusic);
    }

    @Override
    public void onDestroy() {
        // play background music
        super.onDestroy();
        Intent backgroundMusic = new Intent(AddPlayers.this, SoundService.class);
        stopService(backgroundMusic);
    }

    public void onClickHistory(View view) {
        Intent intent = new Intent(this, HistoryList.class);
        startActivity(intent);
    }



}