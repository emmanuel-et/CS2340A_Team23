package com.example.cs2340a_team23.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team23.R;
import com.example.cs2340a_team23.model.GameState;

public class GameActivityRoom1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_1);
        GameState gameState = GameState.getGameState();
        gameState.startScoreTimer();


        Button nextButton = findViewById(R.id.nextbutton);

        nextButton.setOnClickListener(view -> {
            Intent room2Screen = new Intent(GameActivityRoom1.this, GameActivityRoom2.class);
            gameState.stopScoreTimer();
            startActivity(room2Screen);
            finish();
        });
    }
}
