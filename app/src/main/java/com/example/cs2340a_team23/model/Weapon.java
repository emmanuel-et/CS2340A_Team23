package com.example.cs2340a_team23.model;

public abstract class Weapon {
    private int range;

    public int getRange() {
        return range;
    }

    public void attack(Enemy enemy, Weapon weapon) {
        double distance = Math.sqrt(Math.pow(Player.getPlayer().getPlayerX() - enemy.getEnemyX(), 2) + Math.pow(Player.getPlayer().getPlayerY() - enemy.getEnemyY(), 2));

        if (distance <= range) {
            enemy.destroy();
        }
    }
}
