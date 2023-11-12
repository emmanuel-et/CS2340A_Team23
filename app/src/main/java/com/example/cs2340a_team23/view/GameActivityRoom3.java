package com.example.cs2340a_team23.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.cs2340a_team23.R;
import com.example.cs2340a_team23.model.Enemy;
import com.example.cs2340a_team23.model.GameState;
import com.example.cs2340a_team23.model.MoveBehavior;
import com.example.cs2340a_team23.model.Player;
import com.example.cs2340a_team23.model.Run;
import com.example.cs2340a_team23.model.ShadowRevenantCreator;
import com.example.cs2340a_team23.model.Walk;
import com.example.cs2340a_team23.model.ZephyrClawCreator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

public class GameActivityRoom3 extends AppCompatActivity {

    private Random random = new Random();
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
    private List<Enemy> enemies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_3);
        room3 = findViewById(R.id.room3);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        initialiseEnemies();
        drawEnemies();
        gameState = GameState.getGameState();
        gameState.getScoreTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                moveEnemies();
            }
        }, 0, 500);
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
            player.updatePosition();
            checkCollisions();
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
                removeEnemies();
                player.updatePosition();
                checkCollisions();
                gameState.setTimeEnd(LocalTime.now());
                gameState.setDate(LocalDate.now());
                startActivity(endScreen);
                finish();
            }
            break;
        case KeyEvent.KEYCODE_DPAD_UP:
            if (player.getPlayerY() - 50 < 147) {
                player.updatePosition();
                checkCollisions();
                return true;
            }
            player.move("up", screenWidth, screenHeight);
            break;
        case KeyEvent.KEYCODE_DPAD_DOWN:
            if (player.getPlayerY() + 50 > 1347) {
                player.updatePosition();
                checkCollisions();
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
        player.updatePosition();
        checkCollisions();
        return true;
    }

    private void initialiseEnemies() {
        ShadowRevenantCreator shadowRevenantCreator = new ShadowRevenantCreator();
        float randomX = random.nextInt(798) + 42;
        float randomY = random.nextInt(1199) + 148;
        Enemy enemy1 = shadowRevenantCreator.createEnemy(randomX, randomY);
        enemies.add(enemy1);

        ZephyrClawCreator zephyrClawCreator = new ZephyrClawCreator();
        randomX = random.nextInt(798) + 42;
        randomY = random.nextInt(1199) + 148;
        Enemy enemy2 = zephyrClawCreator.createEnemy(randomX, randomY);
        enemies.add(enemy2);
    }

    private void drawEnemies() {
        for (Enemy enemy : enemies) {
            enemy.createSpriteView(this);
            enemy.getSpriteView().setId(View.generateViewId());
            room3.addView(enemy.getSpriteView());
        }
    }

    private void removeEnemies() {
        for (Enemy enemy : enemies) {
            room3.removeView(enemy.getSpriteView());
        }
    }

    private void moveEnemies() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (Enemy enemy : enemies) {
                    int movementDirection = random.nextInt(4) + 1;
                    switch (movementDirection) {
                        case 1:
                            enemy.move("left", screenWidth, screenHeight);
                            break;
                        case 2:
                            enemy.move("up", screenWidth, screenHeight);
                            break;
                        case 3:
                            enemy.move("right", screenWidth, screenHeight);
                            break;
                        case 4:
                            enemy.move("down", screenWidth, screenHeight);
                            break;
                        default:
                            break;
                    }
                    playerHealth.setText("Health: " + Integer.toString(player.getHealth()));
                }
            }
        });
    }
    private boolean isCollision(float playerX, float playerY, float enemyX, float enemyY) {
        float playerWidth = 30;
        float playerHeight = 40;
        float enemyWidth = 32;
        float enemyHeight = 32;

        return playerX < enemyX + enemyWidth
                && playerX + playerWidth > enemyX
                && playerY < enemyY + enemyHeight
                && playerY + playerHeight > enemyY;
    }

    private void checkCollisions() {
        float playerX = player.getPlayerX();
        float playerY = player.getPlayerY();

        for (Enemy enemy : enemies) {
            float enemyX = enemy.getEnemyX();
            float enemyY = enemy.getEnemyY();

            if (isCollision(playerX, playerY, enemyX, enemyY)) {
                enemy.handleCollision(gameState.getDifficulty());
            }
        }
    }
}
