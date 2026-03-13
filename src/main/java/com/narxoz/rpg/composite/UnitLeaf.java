package com.narxoz.rpg.composite;

import java.util.ArrayList;
import java.util.List;

public abstract class UnitLeaf implements CombatNode {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int attackPower;

    public UnitLeaf(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.attackPower = attackPower;
    }

    @Override
    public String getName() { return name; }

    @Override
    public int getHealth() { return health; }

    @Override
    public int getMaxHealth() { return maxHealth; }

    @Override
    public int getAttackPower() { return isAlive() ? attackPower : 0; }

    @Override
    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) health = 0;
        System.out.println("  " + name + " получил " + amount + " урона, осталось " + health);
    }

    @Override
    public boolean isAlive() { return health > 0; }

    @Override
    public void add(CombatNode node) { }

    @Override
    public void remove(CombatNode node) { }

    @Override
    public List<CombatNode> getChildren() { return new ArrayList<>(); }

    @Override
    public void printTree(String indent) {
        System.out.println(indent + "├── " + name + " [♥" + health + "/" + maxHealth + "]");
    }
}