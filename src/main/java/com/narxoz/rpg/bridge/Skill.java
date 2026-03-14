package com.narxoz.rpg.bridge;

import com.narxoz.rpg.composite.CombatNode;

public abstract class Skill {
    protected String name;
    protected int baseDamage;
    protected EffectImplementor effect;

    public Skill(String name, int baseDamage, EffectImplementor effect) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.effect = effect;
    }

    public String getName() {
        return name + " (" + effect.getEffectName() + ")";
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public EffectImplementor getEffect() {
        return effect;
    }

    public void setEffect(EffectImplementor effect) {
        this.effect = effect;
    }

    public abstract void apply(CombatNode user, CombatNode target);
    public abstract String getSkillType();
}