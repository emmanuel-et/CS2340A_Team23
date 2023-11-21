package com.example.cs2340a_team23.model;

import android.content.Context;
import android.widget.ImageView;

public class SpeedPowerup extends Powerup {
    private String name;
    private ImageView spriteView;
    private float posX;
    private float posY;
    public SpeedPowerup(float posX, float posY) {
        this.name = "Speed Boost";
        this.posX = posX;
        this.posY = posY;
    }
    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }
    @Override
    public void createSpriteView(Context context) {
        this.spriteView = new ImageView(context);
        int resourceId = context.getResources().getIdentifier("fastpowerup",
                "drawable", context.getPackageName());
        spriteView.setImageResource(resourceId);
        this.spriteView.setX(this.posX);
        this.spriteView.setY(this.posY);
    }

    @Override
    public ImageView getSpriteView() {
        return this.spriteView;
    }
}
