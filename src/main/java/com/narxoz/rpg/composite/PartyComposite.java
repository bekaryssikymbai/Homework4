package com.narxoz.rpg.composite;

import java.util.ArrayList;
import java.util.List;

public class PartyComposite implements CombatNode {
    private String name;
    private List<CombatNode> members = new ArrayList<>();

    public PartyComposite(String name) {
        this.name = name;
    }

    @Override
    public String getName() { return name; }

    @Override
    public int getHealth() {
        int total = 0;
        for (CombatNode node : members) total += node.getHealth();
        return total;
    }

    @Override
    public int getMaxHealth() {
        int total = 0;
        for (CombatNode node : members) total += node.getMaxHealth();
        return total;
    }

    @Override
    public int getAttackPower() {
        int total = 0;
        for (CombatNode node : members) total += node.getAttackPower();
        return total;
    }

    @Override
    public void takeDamage(int amount) {
        List<CombatNode> alive = new ArrayList<>();
        for (CombatNode node : members) {
            if (node.isAlive()) alive.add(node);
        }
        if (alive.isEmpty()) return;

        int damagePerNode = amount / alive.size();
        int remaining = amount % alive.size();

        for (CombatNode node : alive) {
            node.takeDamage(damagePerNode);
        }
        if (remaining > 0) alive.get(0).takeDamage(remaining);
    }

    @Override
    public boolean isAlive() {
        for (CombatNode node : members) {
            if (node.isAlive()) return true;
        }
        return false;
    }

    @Override
    public void add(CombatNode node) { members.add(node); }

    @Override
    public void remove(CombatNode node) { members.remove(node); }

    @Override
    public List<CombatNode> getChildren() { return new ArrayList<>(members); }

    @Override
    public void printTree(String indent) {
        System.out.println(indent + "├── " + name + " [Party] ♥" + getHealth() + "/" + getMaxHealth());
        for (int i = 0; i < members.size(); i++) {
            if (i == members.size() - 1) {
                members.get(i).printTree(indent + "    ");
            } else {
                members.get(i).printTree(indent + "    ");
            }
        }
    }
}