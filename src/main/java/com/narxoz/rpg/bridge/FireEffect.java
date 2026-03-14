package com.narxoz.rpg.bridge;

import com.narxoz.rpg.composite.CombatNode;

public class FireEffect implements EffectImplementor {

    @Override
    public String getEffectName() {
        return "Fire";
    }

    @Override
    public int calculateDamage(int baseDamage, CombatNode target) {
        // Fire damage - bonus damage
        return (int)(baseDamage * 1.2);
    }

    @Override
    public String getDamageType() {
        return "FIRE";
    }
}