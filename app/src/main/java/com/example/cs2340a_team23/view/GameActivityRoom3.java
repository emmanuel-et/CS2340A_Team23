package com.example.cs2340a_team23.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs2340a_team23.R;
import com.example.cs2340a_team23.model.GameState;
import com.example.cs2340a_team23.model.Player;

import java.time.LocalTime;

public class GameActivityRoom3 extends AppCompatActivity {
    private TextView scoreTextView;
    private GameState gameState;
    private Handler scoreUpdateHandler;
    private Runnable scoreUpdateRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_3);
        GameState gameState = GameState.getGameState();
        gameState.startScoreTimer();
        Player player = Player.getPlayer();


        TextView playerName = findViewById(R.id.playerName);
        playerName.setText(player.getPlayerName());

        TextView playerHealth = findViewById(R.id.playerHealth);
        playerHealth.setText("Health: " + Integer.toString(player.getHealth()));

        TextView gameDifficulty = findViewById(R.id.gameDifficulty);
        gameDifficulty.setText(gameState.getDifficulty());

        ImageView playerSprite = findViewById(R.id.playerSprite);
        String spriteName = player.getSprite();

        int resID = getResources().getIdentifier(spriteName, "drawable", getPackageName());
        playerSprite.setImageResource(resID);

        Button endButton = findViewById(R.id.endButton);
        endButton.setOnClickListener(view -> {
            Intent endScreen = new Intent(GameActivityRoom3.this, EndActivity.class);
            gameState.stopScoreTimer();
            gameState.setTimeEnd(LocalTime.now());
            startActivity(endScreen);
            finish();
        });

        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + gameState.getScore());
        scoreUpdateRunnable = new Runnable() {
            @Override
            public void run() {
                int newScore = gameState.getScore();
                scoreTextView.setText("Score: "+ newScore);
                scoreUpdateHandler.postDelayed(this,1000);
            }
        };
        scoreUpdateHandler = new Handler();
        scoreUpdateHandler.postDelayed(scoreUpdateRunnable,1000);
    }

}
