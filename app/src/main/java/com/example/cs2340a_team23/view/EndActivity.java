package com.example.cs2340a_team23.view;

import androidx.appcompat.app.AppCompatActivity;


import android.widget.ListView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.cs2340a_team23.R;
import com.example.cs2340a_team23.model.*;

import java.util.ArrayList;
import java.util.Comparator;

public class EndActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(view -> {
//            TODO: Call player reset method
            Intent gameConfig = new Intent(EndActivity.this, GameConfigurationActivity.class);
            startActivity(gameConfig);
            finish();
        });

        LeaderboardEntry mario = new LeaderboardEntry("Mario", 100);
        LeaderboardEntry sonic = new LeaderboardEntry("Sonic", 200);
        LeaderboardEntry megaman = new LeaderboardEntry("Megaman", 50);
        Leaderboard leaderboard = Leaderboard.getLeaderboard();
        leaderboard.addEntry(mario);
        leaderboard.addEntry(megaman);
        leaderboard.addEntry(sonic);
        leaderboard.sortEntriesByScoreDescending();


        ListView leaderboardListView = findViewById(R.id.leaderboardListView);
        LeaderboardAdapter adapter = new LeaderboardAdapter(this, leaderboard.getEntries()); // Replace with your data source
        leaderboardListView.setAdapter(adapter);


    }


}
