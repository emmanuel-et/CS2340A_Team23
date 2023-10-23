package com.example.cs2340a_team23.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.cs2340a_team23.R;
import com.example.cs2340a_team23.model.GameState;
import com.example.cs2340a_team23.model.MoveBehavior;
import com.example.cs2340a_team23.model.Player;
import com.example.cs2340a_team23.model.Run;
import com.example.cs2340a_team23.model.Walk;
public class GameActivityRoom1 extends AppCompatActivity {

    private TextView scoreTextView;
    private GameState gameState;
    private Handler scoreUpdateHandler;
    private Runnable scoreUpdateRunnable;
    private Player player = Player.getPlayer();
    private int screenWidth;
    private int screenHeight;

    private ConstraintLayout room1;

    private TextView playerName;

    private TextView playerHealth;

    private TextView gameDifficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_1);
        room1 = findViewById(R.id.room1);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        player.setPlayerX(screenWidth / 2);
        player.setPlayerY(screenHeight / 2);
        player.createSpriteView(this, player.getSprite(), player.getPlayerX(),
                player.getPlayerY());
        player.getSpriteView().setId(View.generateViewId());
        room1.addView(player.getSpriteView());
        gameState = GameState.getGameState();
        gameState.startScoreTimer();

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
            if (player.getPlayerX() - 50 < 210) {
                return true;
            }
            player.move("left", screenWidth, screenHeight);
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            if (player.getPlayerX() == 990.0) {
                Intent room2Screen = new Intent(GameActivityRoom1.this,
                        GameActivityRoom2.class);
                playerName.setText("");
                room1.removeView(player.getSpriteView());
                player.setPlayerX(40);
                startActivity(room2Screen);
                finish();
            }
            player.move("right", screenWidth, screenHeight);
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
