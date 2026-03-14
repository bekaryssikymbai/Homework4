package com.narxoz.rpg.bridge;

import com.narxoz.rpg.composite.CombatNode;

public class SingleTargetSkill extends Skill {

    public SingleTargetSkill(String name, int baseDamage, EffectImplementor effect) {
        super(name, baseDamage, effect);
    }

    @Override
    public void apply(CombatNode user, CombatNode target) {
        if (!user.isAlive() || !target.isAlive()) return;

        int damage = effect.calculateDamage(baseDamage, target);
        System.out.println("  " + user.getName() + " uses " + getName() + " on " + target.getName());
        System.out.println("  Deals " + damage + " " + effect.getDamageType() + " damage!");
        target.takeDamage(damage);
    }

    @Override
    public String getSkillType() {
        return "Single Target";
    }
}