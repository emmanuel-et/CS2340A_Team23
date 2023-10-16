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

public class GameActivityRoom1 extends AppCompatActivity {

    private TextView scoreTextView;
    private GameState gameState;
    private Handler scoreUpdateHandler;
    private Runnable scoreUpdateRunnable;
    Player player = Player.getPlayer();
    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_1);
        ConstraintLayout room1 = findViewById(R.id.room1);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        player.setPlayerX(screenWidth / 2);
        player.setPlayerY(screenHeight / 2);
        player.createSpriteView(this, player.getSprite(), player.getPlayerX(), player.getPlayerY());
        room1.addView(player.getSpriteView());
        GameState gameState = GameState.getGameState();
        gameState.startScoreTimer();

        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + gameState.getScore());
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



        Button nextButton = findViewById(R.id.nextbutton);

        nextButton.setOnClickListener(view -> {
            Intent room2Screen = new Intent(GameActivityRoom1.this, GameActivityRoom2.class);
            gameState.stopScoreTimer();
            startActivity(room2Screen);
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
        }
        return true;
    }





}
