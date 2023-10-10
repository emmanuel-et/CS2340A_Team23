package com.example.cs2340a_team23.view;

import androidx.appcompat.app.AppCompatActivity;


import android.widget.ListView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.cs2340a_team23.R;
import com.example.cs2340a_team23.model.*;

import java.util.ArrayList;
import java.util.Collections;
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

        ArrayList<LeaderboardEntry> entries = new ArrayList<>();
        LeaderboardEntry mario = new LeaderboardEntry("Mario", 100);
        LeaderboardEntry sonic = new LeaderboardEntry("Sonic", 200);
        entries.add(mario);
        entries.add(sonic);

        Comparator<LeaderboardEntry> scoreComparator = new Comparator<LeaderboardEntry>() {
            @Override
            public int compare(LeaderboardEntry entry1, LeaderboardEntry entry2) {
                // Compare in descending order by score
                return Integer.compare(entry2.getScore(), entry1.getScore());
            }
        };

// Sort the leaderboardEntries list using the custom Comparator
        Collections.sort(entries, scoreComparator);


        ListView leaderboardListView = findViewById(R.id.leaderboardListView);
        LeaderboardAdapter adapter = new LeaderboardAdapter(this, entries); // Replace with your data source
        leaderboardListView.setAdapter(adapter);


    }


}
