package com.example.cs2340a_team23.model;

import android.content.Context;
import android.widget.ImageView;

public abstract class Enemy {
    private String name;
    private ImageView spriteView;
    private int speed;
    private MoveBehavior moveBehavior;
    private float enemyX;
    private float enemyY;

    public float getEnemyX() {
        return enemyX;
    }

    public float getEnemyY() {
        return enemyY;
    }

    public abstract void createSpriteView(Context context);
    public abstract ImageView getSpriteView();
    public abstract void move(String direction, int screenWidth, int screenHeight);

    public void updatePlayerPosition(float playerX, float playerY) {
        if (isCollision(playerX, playerY)) {
            handleCollision(GameState.getGameState().getDifficulty());
        }

    }

    public void handleCollision(String difficulty) {
        int healthDeduction;
        switch (difficulty.toLowerCase()) {

        case "superhard":
            healthDeduction = 60;
            break;

        case "hard":
            healthDeduction = 50;
            break;
        case "medium":
            healthDeduction = 40;
            break;
        case "easy":
            healthDeduction = 30;
            break;

        default:
            healthDeduction = 30;
            break;

        }
        Player player = Player.getPlayer();
        player.setHealth(player.getHealth() - healthDeduction);
    }


    private boolean isCollision(float playerX, float playerY) {
        float playerLeft = playerX;
        float playerRight = playerX + 30;
        float playerTop = playerY;
        float playerBottom = playerY + 40;

        float enemyLeft = enemyX;
        float enemyRight = enemyX + 32;
        float enemyTop = enemyY;
        float enemyBottom = enemyY + 32;

        boolean horizontalCollision = playerRight > enemyLeft && playerLeft < enemyRight;
        boolean verticalCollision = playerBottom > enemyTop && playerTop < enemyBottom;

        return horizontalCollision && verticalCollision;

    }

    public void destroy() {
        spriteView = null;
        Player.getPlayer().removeObservers();
    }

}
