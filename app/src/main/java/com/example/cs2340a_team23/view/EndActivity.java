package com.example.cs2340a_team23.view;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ListView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.cs2340a_team23.R;
import com.example.cs2340a_team23.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

import com.example.cs2340a_team23.model.GameState;
import com.example.cs2340a_team23.model.Player;


public class EndActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(view -> {
            Player.resetPlayer();
            GameState.resetGameState();
            Intent gameConfig = new Intent(EndActivity.this, GameConfigurationActivity.class);
            startActivity(gameConfig);
            finish();
        });

        String playerName = Player.getPlayer().getPlayerName();
        LocalDate date = GameState.getGameState().getDate();
        int timeHour = GameState.getGameState().getTimeEnd().getHour();
        int timeMinute = GameState.getGameState().getTimeEnd().getMinute();
        LocalTime endTime = LocalTime.of(timeHour, timeMinute);
        int playerScore = GameState.getGameState().getScore();

        Leaderboard leaderboard = Leaderboard.getLeaderboard();
        LeaderboardEntry currentGameEntry = new LeaderboardEntry(playerName, date, endTime, playerScore);
        leaderboard.addEntry(currentGameEntry);
        leaderboard.sortEntriesByScoreDescending();



        ListView leaderboardListView = findViewById(R.id.leaderboardListView);
        LeaderboardAdapter adapter = new LeaderboardAdapter(this, leaderboard.getEntries()); // Replace with your data source
        leaderboardListView.setAdapter(adapter);

        View headerView = getLayoutInflater().inflate(R.layout.activity_leaderboard_header, null);
        leaderboardListView.addHeaderView(headerView);


    }


}
