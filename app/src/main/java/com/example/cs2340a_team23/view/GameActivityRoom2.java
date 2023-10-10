package com.example.cs2340a_team23.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team23.R;
import com.example.cs2340a_team23.model.GameState;

public class GameActivityRoom2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_2);
        GameState gameState = GameState.getGameState();
        gameState.startScoreTimer();
        Button nextButton = findViewById(R.id.nextbutton2);

        nextButton.setOnClickListener(view -> {
            Intent room3Screen = new Intent(GameActivityRoom2.this, GameActivityRoom3.class);
            gameState.stopScoreTimer();
            startActivity(room3Screen);
            finish();
        });
    }
}
