package com.example.cs2340a_team23.model;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cs2340a_team23.R;

public class Player {
    private static Player player = null;
    private int health;
    private String playerName;
    private String sprite;
    private ImageView spriteView;


    private Player(int health, String playerName, String sprite) {

        this.playerName = playerName;
        this.health = health;
        this.sprite = sprite;

    }
    public static Player getPlayer() {
        if (player == null) {
            player = new Player(100, "DefaultPlayer", "DefaultSprint");
        }
        return player;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }
    public void createSpriteView(Context context, String sprite, float X, float Y) {
        this.spriteView = new ImageView(context);
        int resourceId = context.getResources().getIdentifier(sprite, "drawable",
                context.getPackageName());
        spriteView.setImageResource(resourceId);
        this.spriteView.setX(X);
        this.spriteView.setY(Y);
    }
    public void updatePosition(float newX, float newY) {
        this.spriteView.setX(newX);
        this.spriteView.setY(newY);
    }
    public ImageView getSpriteView() {
        return spriteView;
    }

    public static void resetPlayer() {
        player = null;
    }
    public void initializePlayer(String playerName, int health, String sprite) {
        player.setPlayerName(playerName);
        player.setHealth(health);
        player.setSprite(sprite);
    }
}
