package com.example.cs2340a_team23.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import com.example.cs2340a_team23.R;
import com.example.cs2340a_team23.model.GameState;
import com.example.cs2340a_team23.model.MoveBehavior;
import com.example.cs2340a_team23.model.Player;
import com.example.cs2340a_team23.model.Run;
import com.example.cs2340a_team23.model.Walk;

import java.time.LocalDate;
import java.time.LocalTime;

public class GameActivityRoom3 extends AppCompatActivity {
    private TextView scoreTextView;
    private GameState gameState;
    private Handler scoreUpdateHandler;
    private Player player = Player.getPlayer();
    private Runnable scoreUpdateRunnable;
    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_3);
        ConstraintLayout room3 = findViewById(R.id.room3);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        GameState gameState = GameState.getGameState();
        gameState.startScoreTimer();
        room3.addView(player.getSpriteView());

        TextView playerName = findViewById(R.id.playerName);
        playerName.setText(player.getPlayerName());

        TextView playerHealth = findViewById(R.id.playerHealth);
        playerHealth.setText("Health: " + Integer.toString(player.getHealth()));

        TextView gameDifficulty = findViewById(R.id.gameDifficulty);
        gameDifficulty.setText(gameState.getDifficulty());

        Button endButton = findViewById(R.id.endButton);
        endButton.setOnClickListener(view -> {
            Intent endScreen = new Intent(GameActivityRoom3.this, EndActivity.class);
            gameState.stopScoreTimer();
            playerName.setText("");
            room3.removeView(player.getSpriteView());
            gameState.setTimeEnd(LocalTime.now());
            gameState.setDate(LocalDate.now());
            startActivity(endScreen);
            finish();
        });

        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + gameState.getScore());
        scoreUpdateRunnable = new Runnable() {
            @Override
            public void run() {
                playerName.setX(player.getPlayerX() - 20);
                playerName.setY(player.getPlayerY() + 50);
                int newScore = gameState.getScore();
                scoreTextView.setText("Score: " + newScore);
                scoreUpdateHandler.postDelayed(this, 0);
            }
        };
        scoreUpdateHandler = new Handler();
        scoreUpdateHandler.postDelayed(scoreUpdateRunnable, 0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_LEFT:
            player.move("left", screenWidth, screenHeight);
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            player.move("right", screenWidth, screenHeight);
            break;
        case KeyEvent.KEYCODE_DPAD_UP:
            player.move("up", screenWidth, screenHeight);
            break;
        case KeyEvent.KEYCODE_DPAD_DOWN:
            player.move("down", screenWidth, screenHeight);
            break;
        case KeyEvent.KEYCODE_1:
            MoveBehavior currMoveBehavior = player.getMoveBehavior();
            if (currMoveBehavior.getClass().getSimpleName().equals("Walk")) {
                player.setMoveBehavior(new Run());
            } else {
                player.setMoveBehavior(new Walk());
            }
            break;
        default:
            return false;
        }
        return true;
    }
}
