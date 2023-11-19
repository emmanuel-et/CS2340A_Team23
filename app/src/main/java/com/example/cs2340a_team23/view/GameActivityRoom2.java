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
import com.example.cs2340a_team23.model.Enemy;
import com.example.cs2340a_team23.model.GameState;
import com.example.cs2340a_team23.model.HealthPowerup;
import com.example.cs2340a_team23.model.MoltenWaspCreator;
import com.example.cs2340a_team23.model.MoveBehavior;
import com.example.cs2340a_team23.model.Player;
import com.example.cs2340a_team23.model.Run;
import com.example.cs2340a_team23.model.ScorePowerup;
import com.example.cs2340a_team23.model.Walk;
import com.example.cs2340a_team23.model.ZephyrClawCreator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

public class GameActivityRoom2 extends AppCompatActivity {

    private Random random = new Random();
    private TextView scoreTextView;
    private GameState gameState;
    private Handler scoreUpdateHandler;
    private Runnable scoreUpdateRunnable;
    private Handler healthUpdateHandler;
    private Runnable healthUpdateRunnable;
    private Player player = Player.getPlayer();
    private int screenWidth;
    private int screenHeight;

    private ConstraintLayout room2;

    private TextView playerName;

    private TextView playerHealth;

    private TextView gameDifficulty;
    private List<Enemy> enemies = new ArrayList<>();
    private HealthPowerup healthPowerup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_2);
        room2 = findViewById(R.id.room2);
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

        room2.addView(player.getSpriteView());

        playerName = findViewById(R.id.playerName);
        playerName.setText(player.getPlayerName());

        playerHealth = findViewById(R.id.playerHealth);
        playerHealth.setText("Health: " + Integer.toString(player.getHealth()));

        gameDifficulty = findViewById(R.id.gameDifficulty);
        gameDifficulty.setText(gameState.getDifficulty());

        healthPowerup = addHealthPowerup();

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
        healthUpdateRunnable = new Runnable() {
            @Override
            public void run() {
                playerHealth.setText("Health: " + Integer.toString(player.getHealth()));
                healthUpdateHandler.postDelayed(this, 1000); // Update every second
            }
        };
        healthUpdateHandler = new Handler();
        healthUpdateHandler.postDelayed(healthUpdateRunnable, 0);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_LEFT:
            if (player.getPlayerX() == 40) {
                Intent room1Screen = new Intent(GameActivityRoom2.this,
                        GameActivityRoom1.class);
                playerName.setText("");
                room2.removeView(player.getSpriteView());
                player.setPlayerX(990);
                player.removeObservers();
                startActivity(room1Screen);
                finish();
            }
            player.move("left", screenWidth, screenHeight);
            player.updatePosition();
            checkCollisions();
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            if (player.getPlayerX() == 990.0) {
                Intent room3Screen = new Intent(GameActivityRoom2.this,
                        GameActivityRoom3.class);
                playerName.setText("");
                room2.removeView(player.getSpriteView());
                removeEnemies();
                player.setPlayerX(40);
                player.updatePosition();
                checkCollisions();
                player.removeObservers();
                startActivity(room3Screen);
                finish();
            }
            player.move("right", screenWidth, screenHeight);
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
        case KeyEvent.KEYCODE_2:
            if (isCollision(player.getPlayerX(), player.getPlayerY(),
                    healthPowerup.getPosX(), healthPowerup.getPosY())) {
                useHealthPowerup();
            }
            break;
        default:
            return false;
        }
        player.updatePosition();
        checkCollisions();
        return true;
    }

    private HealthPowerup addHealthPowerup() {
        float randomX = random.nextInt(679) + 211;
        float randomY = random.nextInt(1199) + 148;
        HealthPowerup healthPowerup = new HealthPowerup(randomX, randomY);
        healthPowerup.createSpriteView(this);
        healthPowerup.getSpriteView().setId(View.generateViewId());
        room2.addView(healthPowerup.getSpriteView());
        return healthPowerup;
    }

    private void removeHealthPowerup() {
        room2.removeView(healthPowerup.getSpriteView());
    }
    private void useHealthPowerup() {
        removeHealthPowerup();
        player.setHealth(player.getHealth() + 20);
    }
    private void initialiseEnemies() {
        ZephyrClawCreator zephyrClawCreator = new ZephyrClawCreator();
        float randomX = random.nextInt(948) + 42;
        float randomY = random.nextInt(1199) + 148;
        Enemy enemy1 = zephyrClawCreator.createEnemy(randomX, randomY);
        enemies.add(enemy1);

        MoltenWaspCreator moltenWaspCreator = new MoltenWaspCreator();
        randomX = random.nextInt(948) + 42;
        randomY = random.nextInt(1199) + 148;
        Enemy enemy2 = moltenWaspCreator.createEnemy(randomX, randomY);
        enemies.add(enemy2);
    }

    private void drawEnemies() {
        for (Enemy enemy : enemies) {
            enemy.createSpriteView(this);
            enemy.getSpriteView().setId(View.generateViewId());
            room2.addView(enemy.getSpriteView());
        }
    }

    private void removeEnemies() {
        for (Enemy enemy : enemies) {
            room2.removeView(enemy.getSpriteView());
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
                checkEndDueToHealth();
            }
        }
    }

    private void checkEndDueToHealth() {
        if (player.getHealth() <= 0) {
            gameState.setScore(0);
            Intent endScreen = new Intent(GameActivityRoom2.this,
                    EndActivity.class);
            playerName.setText("");
            room2.removeView(player.getSpriteView());
            removeEnemies();
            gameState.setTimeEnd(LocalTime.now());
            gameState.setDate(LocalDate.now());
            startActivity(endScreen);
            finish();
        }
    }

}
