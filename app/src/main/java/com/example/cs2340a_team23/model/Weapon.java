package com.example.cs2340a_team23.model;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public class Weapon {
    private int range;

    public Weapon() {
        this.range = 100;
    }

    public int getRange() {
        return range;
    }

    public void attack(Player player, List<Enemy> enemies, ConstraintLayout room) {
        Enemy enemy = checkAttackDistance(player, enemies);
        if (enemy != null) {
            enemy.destroy(room);
        }
    }

    private Enemy checkAttackDistance(Player player, List<Enemy> enemies) {
        float playerWidth = 30;
        float playerHeight = 40;
        float enemyWidth = 32;
        float enemyHeight = 32;
        float playerCenterX = player.getPlayerX() + playerWidth / 2;
        float playerCenterY = player.getPlayerY() + playerHeight / 2;
        for (Enemy enemy : enemies) {
            float enemyCenterX = enemy.getEnemyX() + enemyWidth / 2;
            float enemyCenterY = enemy.getEnemyY() + enemyHeight / 2;

            // Calculate the distance between player and enemy centers
            double distance = Math.sqrt(Math.pow(playerCenterX - enemyCenterX, 2) + Math.pow(playerCenterY - enemyCenterY, 2));

            if (distance <= player.getWeapon().getRange()) {
                return enemy;
            }
        }
        return null;
    }
}
