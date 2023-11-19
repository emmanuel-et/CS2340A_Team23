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
import com.example.cs2340a_team23.model.GorgonWardenCreator;
import com.example.cs2340a_team23.model.MoveBehavior;
import com.example.cs2340a_team23.model.Player;
import com.example.cs2340a_team23.model.Run;
import com.example.cs2340a_team23.model.ScorePowerup;
import com.example.cs2340a_team23.model.ShadowRevenantCreator;
import com.example.cs2340a_team23.model.Walk;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

public class GameActivityRoom1 extends AppCompatActivity {

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

    private ConstraintLayout room1;

    private TextView playerName;

    private TextView playerHealth;

    private TextView gameDifficulty;
    private ScorePowerup scorePowerup;
    private List<Enemy> enemies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_1);
        room1 = findViewById(R.id.room1);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        initialiseEnemies();
        drawEnemies();
        player.setPlayerX(screenWidth / 2);
        player.setPlayerY(screenHeight / 2);
        player.createSpriteView(this, player.getSprite(), player.getPlayerX(),
                player.getPlayerY());
        player.getSpriteView().setId(View.generateViewId());
        room1.addView(player.getSpriteView());
        gameState = GameState.getGameState();
        gameState.startScoreTimer();
        gameState.getScoreTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                moveEnemies();
            }
        }, 0, 500);

        playerName = findViewById(R.id.playerName);
        playerName.setText(player.getPlayerName());

        playerHealth = findViewById(R.id.playerHealth);
        playerHealth.setText("Health: " + Integer.toString(player.getHealth()));

        gameDifficulty = findViewById(R.id.gameDifficulty);
        gameDifficulty.setText(gameState.getDifficulty());

        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + gameState.getScore());

        scorePowerup = addScorePowerup();


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
                healthUpdateHandler.postDelayed(this, 1000);
            }
        };
        healthUpdateHandler = new Handler();
        healthUpdateHandler.postDelayed(healthUpdateRunnable, 0);



    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_LEFT:
            if (player.getPlayerX() - 50 < 210) {
                return true;
            }
            player.move("left", screenWidth, screenHeight);
            checkCollisions();
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            if (player.getPlayerX() == 990.0) {
                Intent room2Screen = new Intent(GameActivityRoom1.this,
                        GameActivityRoom2.class);
                playerName.setText("");
                room1.removeView(player.getSpriteView());
                removeEnemies();
                player.setPlayerX(40);
                player.updatePosition();
                checkCollisions();
                player.removeObservers();
                startActivity(room2Screen);
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
                    scorePowerup.getPosX(), scorePowerup.getPosY())) {
                useScorePowerup();
            }
            break;
        default:
            return false;
        }
        player.updatePosition();
        checkCollisions();
        return true;
    }
    private ScorePowerup addScorePowerup() {
        float randomX = random.nextInt(679) + 211;
        float randomY = random.nextInt(1199) + 148;
        ScorePowerup scorePowerup = new ScorePowerup(randomX, randomY);
        scorePowerup.createSpriteView(this);
        scorePowerup.getSpriteView().setId(View.generateViewId());
        room1.addView(scorePowerup.getSpriteView());
        return scorePowerup;
    }

    private void removeScorePowerup() {
        room1.removeView(scorePowerup.getSpriteView());
    }
    private void useScorePowerup() {
        removeScorePowerup();
        gameState.setScore(gameState.getScore() + 20);
    }

    private void initialiseEnemies() {
        ShadowRevenantCreator shadowRevenantCreator = new ShadowRevenantCreator();
        float randomX = random.nextInt(679) + 211;
        float randomY = random.nextInt(1199) + 148;
        Enemy enemy1 = shadowRevenantCreator.createEnemy(randomX, randomY);
        enemies.add(enemy1);

        GorgonWardenCreator gorgonWardenCreator = new GorgonWardenCreator();
        randomX = random.nextInt(679) + 211;
        randomY = random.nextInt(1199) + 148;
        Enemy enemy2 = gorgonWardenCreator.createEnemy(randomX, randomY);
        enemies.add(enemy2);
    }

    private void drawEnemies() {
        for (Enemy enemy : enemies) {
            enemy.createSpriteView(this);
            enemy.getSpriteView().setId(View.generateViewId());
            room1.addView(enemy.getSpriteView());
        }
    }

    private void removeEnemies() {
        for (Enemy enemy : enemies) {
            room1.removeView(enemy.getSpriteView());
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
                    if (isCollision(player.getPlayerX(), player.getPlayerY(),
                            enemy.getEnemyX(), enemy.getEnemyY())) {
                        playerHealth.setText("Health: " + Integer.toString(player.getHealth()));
                    }


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
            Intent endScreen = new Intent(GameActivityRoom1.this,
                    EndActivity.class);
            playerName.setText("");
            room1.removeView(player.getSpriteView());
            removeEnemies();
            gameState.setTimeEnd(LocalTime.now());
            gameState.setDate(LocalDate.now());
            startActivity(endScreen);
            finish();
        }
    }


}
