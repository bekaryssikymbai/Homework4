package com.narxoz.rpg.composite;

import java.util.ArrayList;
import java.util.List;

public class RaidGroup implements CombatNode {
    private String name;
    private List<CombatNode> groups = new ArrayList<>();

    public RaidGroup(String name) {
        this.name = name;
    }

    @Override
    public String getName() { return name; }

    @Override
    public int getHealth() {
        int total = 0;
        for (CombatNode node : groups) total += node.getHealth();
        return total;
    }

    @Override
    public int getMaxHealth() {
        int total = 0;
        for (CombatNode node : groups) total += node.getMaxHealth();
        return total;
    }

    @Override
    public int getAttackPower() {
        int total = 0;
        for (CombatNode node : groups) total += node.getAttackPower();
        return total;
    }

    @Override
    public void takeDamage(int amount) {
        List<CombatNode> alive = new ArrayList<>();
        for (CombatNode node : groups) {
            if (node.isAlive()) alive.add(node);
        }
        if (alive.isEmpty()) return;

        int damagePerGroup = amount / alive.size();
        int remaining = amount % alive.size();

        for (CombatNode node : alive) {
            node.takeDamage(damagePerGroup);
        }
        if (remaining > 0) alive.get(0).takeDamage(remaining);
    }

    @Override
    public boolean isAlive() {
        for (CombatNode node : groups) {
            if (node.isAlive()) return true;
        }
        return false;
    }

    @Override
    public void add(CombatNode node) { groups.add(node); }

    @Override
    public void remove(CombatNode node) { groups.remove(node); }

    @Override
    public List<CombatNode> getChildren() { return new ArrayList<>(groups); }

    @Override
    public void printTree(String indent) {
        System.out.println(indent + "├── " + name + " [Raid Group] ♥" + getHealth() + "/" + getMaxHealth());
        for (int i = 0; i < groups.size(); i++) {
            if (i == groups.size() - 1) {
                groups.get(i).printTree(indent + "    ");
            } else {
                groups.get(i).printTree(indent + "    ");
            }
        }
    }
}
