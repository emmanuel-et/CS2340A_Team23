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
    private MoveBehavior moveBehavior;
    private float PlayerX;
    private float PlayerY;


    private Player(int health, String playerName, String sprite) {

        this.playerName = playerName;
        this.health = health;
        this.sprite = sprite;
        this.moveBehavior = new Walk();
        this.PlayerX = 0;
        this.PlayerY = 0;
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

    public float getPlayerX() {
        return PlayerX;
    }

    public void setPlayerX(float playerX) {
        PlayerX = playerX;
    }

    public float getPlayerY() {
        return PlayerY;
    }

    public void setPlayerY(float playerY) {
        PlayerY = playerY;
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
    public void move(String direction, int screenWidth, int screenHeight) {
        float[] newPos = moveBehavior.move(this.getPlayerX(), this.getPlayerY(), direction, screenWidth, screenHeight,
                this.spriteView.getWidth(), this.spriteView.getHeight());
        this.setPlayerX(newPos[0]);
        this.setPlayerY(newPos[1]);
        this.updatePosition();
    }
    public void updatePosition() {
        this.spriteView.setX(this.getPlayerX());
        this.spriteView.setY(this.getPlayerY());
    }
    public ImageView getSpriteView() {
        return spriteView;
    }

    public static void resetPlayer() {
        player = null;
    }

    public MoveBehavior getMoveBehavior() {
        return moveBehavior;
    }

    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }

    public void initializePlayer(String playerName, int health, String sprite) {
        player.setPlayerName(playerName);
        player.setHealth(health);
        player.setSprite(sprite);
    }
}
