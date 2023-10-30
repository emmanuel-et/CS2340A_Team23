package com.example.cs2340a_team23.model;

public class ShadowRevenantCreator extends EnemyCreator {

    @Override
    public ShadowRevenant createEnemy(float enemyX, float enemyY) {
        return new ShadowRevenant(enemyX, enemyY);
    }
}
