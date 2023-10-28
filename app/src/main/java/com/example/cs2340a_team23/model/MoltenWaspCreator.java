package com.example.cs2340a_team23.model;

public class MoltenWaspCreator extends EnemyCreator {

    @Override
    public MoltenWasp createEnemy(float enemyX, float enemyY) {
        return new MoltenWasp(enemyX, enemyY);
    }
}
