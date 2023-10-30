package com.example.cs2340a_team23.model;

import android.content.Context;
import android.widget.ImageView;

public class GordonWarden extends Enemy {
    private String name;
    private ImageView spriteView;
    private int speed;
    private float enemyX;
    private float enemyY;
    public GordonWarden(float enemyX, float enemyY) {
        this.name = "GordonWarden";
        //Check the player strategy for MOVE and RUN only difference is a number that
        // would be useful when giving different speeds for enemies
        this.speed = 50;
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
        int resourceId = context.getResources().getIdentifier("gordon_warden",
                "drawable", context.getPackageName());
        spriteView.setImageResource(resourceId);
        this.spriteView.setX(this.getEnemyX());
        this.spriteView.setY(this.getEnemyY());
    }
}
