package com.example.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HistoryList extends AppCompatActivity {

    ListView listView;
    Gson gson = new GsonBuilder().create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        listView = findViewById(R.id.LV_id);
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("history", "{items:[]}");
        HistoryPojoAdapter adapter = new HistoryPojoAdapter(gson.fromJson(s1,HistoryHolder.class), this);
        listView.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(screen ->{
            if (screen.getItemId() == R.id.add_player_view){
                Intent intent = new Intent(this, AddPlayers.class);
                startActivity(intent);
            }
            return true;
        });
    }
}