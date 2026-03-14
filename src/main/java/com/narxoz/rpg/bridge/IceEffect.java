package com.narxoz.rpg.bridge;

import com.narxoz.rpg.composite.CombatNode;

public class IceEffect implements EffectImplementor {

    @Override
    public String getEffectName() {
        return "Ice";
    }

    @Override
    public int calculateDamage(int baseDamage, CombatNode target) {
        // Ice damage - reduced but can slow
        return (int)(baseDamage * 0.9);
    }

    @Override
    public String getDamageType() {
        return "ICE";
    }
}