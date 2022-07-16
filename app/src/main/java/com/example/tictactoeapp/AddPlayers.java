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

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddPlayers extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(screen ->{
            if (screen.getItemId() == R.id.history_view){
                Intent intent = new Intent(this, HistoryList.class);
                startActivity(intent);
            }
            return true;
        });

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
                    Toast.makeText(AddPlayers.this,getString(R.string.enter_player_names_popup) , Toast.LENGTH_SHORT).show();
                } else {

                    //Please enter Player Names
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
    public void onPause() {
        // play background music
        super.onPause();
        Intent backgroundMusic = new Intent(AddPlayers.this, SoundService.class);
        stopService(backgroundMusic);
        finish();
    }

    @Override
    public void onResume() {
        // play background music
        super.onResume();
        Intent backgroundMusic = new Intent(AddPlayers.this, SoundService.class);
        startService(backgroundMusic);
    }

    @Override
    public void onDestroy() {
        // play background music
        super.onDestroy();
        Intent backgroundMusic = new Intent(AddPlayers.this, SoundService.class);
        stopService(backgroundMusic);
        finish();
    }



}