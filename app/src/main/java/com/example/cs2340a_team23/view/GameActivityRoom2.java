package com.example.cs2340a_team23.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.cs2340a_team23.R;
import com.example.cs2340a_team23.model.GameState;
import com.example.cs2340a_team23.model.MoveBehavior;
import com.example.cs2340a_team23.model.Player;
import com.example.cs2340a_team23.model.Run;
import com.example.cs2340a_team23.model.Walk;

public class GameActivityRoom2 extends AppCompatActivity {
    private TextView scoreTextView;
    private GameState gameState;
    private Handler scoreUpdateHandler;
    private Runnable scoreUpdateRunnable;
    private Player player = Player.getPlayer();
    private int screenWidth;
    private int screenHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_2);
        ConstraintLayout room2 = findViewById(R.id.room2);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        GameState gameState = GameState.getGameState();
        gameState.startScoreTimer();
        room2.addView(player.getSpriteView());
        Button nextButton = findViewById(R.id.nextbutton2);

        TextView playerName = findViewById(R.id.playerName);
        playerName.setText(player.getPlayerName());

        TextView playerHealth = findViewById(R.id.playerHealth);
        playerHealth.setText("Health: " + Integer.toString(player.getHealth()));

        TextView gameDifficulty = findViewById(R.id.gameDifficulty);
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


        nextButton.setOnClickListener(view -> {
            Intent room3Screen = new Intent(GameActivityRoom2.this,
                    GameActivityRoom3.class);
            gameState.stopScoreTimer();
            playerName.setText("");
            room2.removeView(player.getSpriteView());
            startActivity(room3Screen);
            finish();
        });
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
