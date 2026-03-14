package com.narxoz.rpg.bridge;

import com.narxoz.rpg.composite.CombatNode;

public interface EffectImplementor {
    String getEffectName();
    int calculateDamage(int baseDamage, CombatNode target);
    String getDamageType();
}
