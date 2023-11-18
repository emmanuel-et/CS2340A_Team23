package com.example.cs2340a_team23.model;

import android.content.Context;
import android.widget.ImageView;

public abstract class Powerup {
    private String name;
    private ImageView spriteView;
    private float posX;
    private float posY;
    public abstract void createSpriteView(Context context);
    public abstract ImageView getSpriteView();
}
