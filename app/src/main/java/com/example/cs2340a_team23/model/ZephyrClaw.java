package com.example.cs2340a_team23.model;

import android.content.Context;
import android.widget.ImageView;

public class ZephyrClaw extends Enemy {
    private String name;
    private ImageView spriteView;
    private int speed;
    private MoveBehavior moveBehavior;
    private float enemyX;
    private float enemyY;
    public ZephyrClaw(float enemyX, float enemyY) {
        this.name = "ZephyrClaw";
        //Check the player strategy for MOVE and RUN only difference is a number that would
        // be useful when giving different speeds for enemies
        this.speed = 40;
        this.moveBehavior = new EnemyWalk(this.speed);
        this.enemyX = enemyX;
        this.enemyY = enemyY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public float getEnemyX() {
        return enemyX;
    }

    public void setEnemyX(float enemyX) {
        this.enemyX = enemyX;
    }

    public float getEnemyY() {
        return enemyY;
    }

    public void setEnemyY(float enemyY) {
        this.enemyY = enemyY;
    }

    public ImageView getSpriteView() {
        return spriteView;
    }

    public void createSpriteView(Context context) {
        this.spriteView = new ImageView(context);
        int resourceId = context.getResources().getIdentifier("zephyr_claw",
                "drawable", context.getPackageName());
        spriteView.setImageResource(resourceId);
        this.spriteView.setX(this.getEnemyX());
        this.spriteView.setY(this.getEnemyY());
    }

    public void move(String direction, int screenWidth, int screenHeight) {
        float[] newPos = moveBehavior.move(this.getEnemyX(), this.getEnemyY(), direction,
                screenWidth, screenHeight, this.spriteView.getWidth(), this.spriteView.getHeight());
        this.setEnemyX(newPos[0]);
        this.setEnemyY(newPos[1]);
        this.updatePosition();
    }
    public void updatePosition() {
        this.spriteView.setX(this.getEnemyX());
        this.spriteView.setY(this.getEnemyY());
    }
}
