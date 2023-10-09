package com.example.cs2340a_team23.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.cs2340a_team23.R;
import com.example.cs2340a_team23.model.GameState;
import com.example.cs2340a_team23.model.Player;

public class EndActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Log.d("TAG4", Integer.toString(GameState.getGameState().getScore()));
        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(view -> {
            Player.resetPlayer();
            GameState.resetGameState();
            Intent gameConfig = new Intent(EndActivity.this, GameConfigurationActivity.class);
            startActivity(gameConfig);
            finish();
        });
    }
}
