package com.narxoz.rpg.composite;

public class EnemyUnit extends UnitLeaf {
    private String enemyType;

    public EnemyUnit(String name, String enemyType, int health, int attackPower) {
        super(name, health, attackPower);
        this.enemyType = enemyType;
    }

    public String getEnemyType() { return enemyType; }
}