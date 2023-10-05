package com.example.cs2340a_team23.model;

public class Player {
    private static Player player = null;
    private int health;
    private String playerName;
    private String sprite;


    private Player(int health, String playerName, String sprite) {

        this.playerName = playerName;
        this.health = health;
        this.sprite = sprite;

    }
    public static Player getPlayer() {
        if (player == null) {
            // Create a new instance if one does not exist
            player = new Player(100, "DefaultPlayer", "DefaultSprite");
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
    public static void resetPlayer() {
        player = null;
    }

}
