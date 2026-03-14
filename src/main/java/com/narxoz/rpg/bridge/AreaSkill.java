package com.narxoz.rpg.bridge;

import com.narxoz.rpg.composite.CombatNode;
import java.util.List;

public class AreaSkill extends Skill {
    private int radius;

    public AreaSkill(String name, int baseDamage, EffectImplementor effect, int radius) {
        super(name, baseDamage, effect);
        this.radius = radius;
    }

    @Override
    public void apply(CombatNode user, CombatNode target) {
        if (!user.isAlive()) return;

        System.out.println("  " + user.getName() + " uses AREA skill " + getName() + " (radius " + radius + ")");

        // If target is composite, damage all children
        if (target.getChildren() != null && !target.getChildren().isEmpty()) {
            applyToGroup(target);
        } else {
            // Single target
            applyToSingle(target);
        }
    }

    private void applyToGroup(CombatNode group) {
        for (CombatNode member : group.getChildren()) {
            if (member.isAlive()) {
                int damage = effect.calculateDamage(baseDamage, member);
                System.out.println("    Hits " + member.getName() + " for " + damage + " " + effect.getDamageType() + " damage!");
                member.takeDamage(damage);
            }
        }
    }

    private void applyToSingle(CombatNode target) {
        int damage = effect.calculateDamage(baseDamage, target);
        System.out.println("    Hits " + target.getName() + " for " + damage + " " + effect.getDamageType() + " damage!");
        target.takeDamage(damage);
    }

    @Override
    public String getSkillType() {
        return "Area of Effect";
    }

    public int getRadius() {
        return radius;
    }
}