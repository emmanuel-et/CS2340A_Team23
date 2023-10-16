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
import com.example.cs2340a_team23.model.Player;

public class GameActivityRoom1 extends AppCompatActivity {

    private TextView scoreTextView;
    private GameState gameState;
    private Handler scoreUpdateHandler;
    private Runnable scoreUpdateRunnable;
    private float playerX, playerY;
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
        playerX = screenWidth / 2;
        playerY = screenHeight / 2;
        player.createSpriteView(this, player.getSprite(), playerX, playerY);
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
        int moveDistance = 50;

        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if (playerX - moveDistance >= 0) {
                    playerX -= moveDistance;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if (playerX + moveDistance + player.getSpriteView().getWidth() <= screenWidth) {
                    playerX += moveDistance;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                if (playerY - moveDistance >= 0) {
                    playerY -= moveDistance;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                if (playerY + moveDistance + player.getSpriteView().getHeight() <= screenHeight) {
                    playerY += moveDistance;
                }
                break;
        }
        player.updatePosition(playerX, playerY);
        return true;
    }





}
