package com.narxoz.rpg.composite;

public class HeroUnit extends UnitLeaf {
    private String heroClass;

    public HeroUnit(String name, String heroClass, int health, int attackPower) {
        super(name, health, attackPower);
        this.heroClass = heroClass;
    }

    public String getHeroClass() { return heroClass; }
}
