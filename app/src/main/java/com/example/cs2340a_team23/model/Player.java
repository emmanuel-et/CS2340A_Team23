package com.example.cs2340a_team23.model;

public class Player {
    private int health;
    private String playerName;
    private String sprite;


    public Player(int health, String playerName, String sprite) {

        this.playerName = playerName;
        this.health = health;
        this.sprite = sprite;

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

}
