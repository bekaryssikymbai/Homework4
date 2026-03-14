package com.narxoz.rpg.bridge;

import com.narxoz.rpg.composite.CombatNode;

public class PhysicalEffect implements EffectImplementor {

    @Override
    public String getEffectName() {
        return "Physical";
    }

    @Override
    public int calculateDamage(int baseDamage, CombatNode target) {
        // Physical damage - no modification
        return baseDamage;
    }

    @Override
    public String getDamageType() {
        return "PHYSICAL";
    }
}