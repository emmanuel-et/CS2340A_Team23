package com.example.cs2340a_team23.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
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

    private ConstraintLayout room3;

    private TextView playerName;

    private TextView playerHealth;

    private TextView gameDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_3);
        room3 = findViewById(R.id.room3);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        gameState = GameState.getGameState();
        room3.addView(player.getSpriteView());

        playerName = findViewById(R.id.playerName);
        playerName.setText(player.getPlayerName());

        playerHealth = findViewById(R.id.playerHealth);
        playerHealth.setText("Health: " + Integer.toString(player.getHealth()));

        gameDifficulty = findViewById(R.id.gameDifficulty);
        gameDifficulty.setText(gameState.getDifficulty());


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
            if (player.getPlayerX() == 40) {
                Intent room2Screen = new Intent(GameActivityRoom3.this,
                        GameActivityRoom2.class);
                playerName.setText("");
                room3.removeView(player.getSpriteView());
                player.setPlayerX(990);
                startActivity(room2Screen);
                finish();
            }
            player.move("left", screenWidth, screenHeight);
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            if (player.getPlayerX() + 50 > 870) {
                return true;
            }
            player.move("right", screenWidth, screenHeight);
            if (player.getPlayerX() == 840.0) {
                Intent endScreen = new Intent(GameActivityRoom3.this, EndActivity.class);
                playerName.setText("");
                room3.removeView(player.getSpriteView());
                gameState.setTimeEnd(LocalTime.now());
                gameState.setDate(LocalDate.now());
                startActivity(endScreen);
                finish();
            }
            break;
        case KeyEvent.KEYCODE_DPAD_UP:
            if (player.getPlayerY() - 50 < 147) {
                return true;
            }
            player.move("up", screenWidth, screenHeight);
            break;
        case KeyEvent.KEYCODE_DPAD_DOWN:
            if (player.getPlayerY() + 50 > 1347) {
                return true;
            }
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
