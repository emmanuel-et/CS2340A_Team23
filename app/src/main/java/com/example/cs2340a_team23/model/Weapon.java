package com.example.cs2340a_team23.model;

public abstract class Weapon {
    private int damage;
    private int range;

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public void attack(Enemy enemy) {

    }
}
