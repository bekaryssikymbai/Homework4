package com.narxoz.rpg.bridge;

import com.narxoz.rpg.composite.CombatNode;

public class ShadowEffect implements EffectImplementor {

    @Override
    public String getEffectName() {
        return "Shadow";
    }

    @Override
    public int calculateDamage(int baseDamage, CombatNode target) {
        // Shadow damage - extra damage to low health targets
        double healthPercent = (double)target.getHealth() / target.getMaxHealth();
        if (healthPercent < 0.3) {
            return (int)(baseDamage * 1.5); // Execute range
        }
        return baseDamage;
    }

    @Override
    public String getDamageType() {
        return "SHADOW";
    }
}