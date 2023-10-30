package com.example.cs2340a_team23.model;

public class GorgonWardenCreator extends EnemyCreator {

    @Override
    public GordonWarden createEnemy(float enemyX, float enemyY) {
        return new GordonWarden(enemyX, enemyY);
    }
}
