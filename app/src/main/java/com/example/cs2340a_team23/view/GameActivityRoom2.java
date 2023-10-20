package com.example.cs2340a_team23.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team23.R;
import com.example.cs2340a_team23.model.GameState;
import com.example.cs2340a_team23.model.Player;

public class GameActivityRoom2 extends AppCompatActivity {
    private TextView scoreTextView;
    private GameState gameState;
    private Handler scoreUpdateHandler;
    private Runnable scoreUpdateRunnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_2);
        GameState gameState = GameState.getGameState();
        gameState.startScoreTimer();
        Button nextButton = findViewById(R.id.nextbutton2);

        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + gameState.getScore());

        TextView playerHealth = findViewById(R.id.playerHealth);
        Player player = Player.getPlayer();
        playerHealth.setText("Health: " + Integer.toString(player.getHealth()));

        TextView gameDifficulty = findViewById(R.id.gameDifficulty);
        gameDifficulty.setText(gameState.getDifficulty());


        scoreUpdateRunnable = new Runnable() {
            @Override
            public void run() {
                int newScore = gameState.getScore();
                scoreTextView.setText("Score: " + newScore);
                scoreUpdateHandler.postDelayed(this, 1000);
            }
        };
        scoreUpdateHandler = new Handler();
        scoreUpdateHandler.postDelayed(scoreUpdateRunnable, 1000);


        nextButton.setOnClickListener(view -> {
            Intent room3Screen = new Intent(GameActivityRoom2.this, GameActivityRoom3.class);
            gameState.stopScoreTimer();
            startActivity(room3Screen);
            finish();
        });
    }




}
