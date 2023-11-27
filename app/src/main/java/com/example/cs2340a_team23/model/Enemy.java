package com.example.cs2340a_team23.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
    private boolean isRed = false;
    private Drawable originalBackground;
    public void destroy(ConstraintLayout room, AppCompatActivity context) {
        room.removeView(this.getSpriteView());
        spriteView = null;
        Player.getPlayer().removeObserver(this);
        GameState.getGameState().setScore(GameState.getGameState().getScore() + 30);

        if (context != null) {
            View rootView = context.getWindow().getDecorView().getRootView();

            // Store the original background only if it hasn't been stored yet
            if (originalBackground == null) {
                originalBackground = rootView.getBackground().getConstantState().newDrawable();
            }

            if (!isRed) {
                rootView.setBackgroundColor(Color.RED);
                isRed = true;

                new Handler().postDelayed(() -> {
                    rootView.setBackground(originalBackground); // Revert back to the original background
                    isRed = false;
                }, 500); // Reset the background after 500 milliseconds (adjust the duration as needed)
            }
        }

    }

}
