package com.example.cs2340a_team23.model;

public class ZephyrClawCreator extends EnemyCreator {

    @Override
    public ZephyrClaw createEnemy(float enemyX, float enemyY) {
        return new ZephyrClaw(enemyX, enemyY);
    }
}
