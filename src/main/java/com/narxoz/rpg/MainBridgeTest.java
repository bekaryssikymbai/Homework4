package com.narxoz.rpg;

import com.narxoz.rpg.bridge.*;
import com.narxoz.rpg.composite.*;

public class MainBridgeTest {
    public static void main(String[] args) {
        System.out.println("=== BRIDGE PATTERN TEST ===\n");

        // Создаем эффекты
        EffectImplementor physical = new PhysicalEffect();
        EffectImplementor fire = new FireEffect();
        EffectImplementor ice = new IceEffect();
        EffectImplementor shadow = new ShadowEffect();

        // Создаем скиллы с разными комбинациями
        System.out.println("--- Skill Combinations ---");

        Skill fireball = new SingleTargetSkill("Fireball", 30, fire);
        Skill iceShard = new SingleTargetSkill("Ice Shard", 25, ice);
        Skill shadowStrike = new SingleTargetSkill("Shadow Strike", 35, shadow);
        Skill whirlwind = new AreaSkill("Whirlwind", 20, physical, 3);

        System.out.println("Fireball: " + fireball.getName());
        System.out.println("Ice Shard: " + iceShard.getName());
        System.out.println("Shadow Strike: " + shadowStrike.getName());
        System.out.println("Whirlwind: " + whirlwind.getName());
        System.out.println();

        // Создаем цели
        HeroUnit warrior = new HeroUnit("Артур", "Warrior", 120, 25);
        EnemyUnit goblin = new EnemyUnit("Гоблин", "Goblin", 60, 12);
        EnemyUnit orc = new EnemyUnit("Орк", "Orc", 100, 18);

        PartyComposite enemies = new PartyComposite("Враги");
        enemies.add(goblin);
        enemies.add(orc);

        // Тестируем Single Target
        System.out.println("--- Single Target Skills ---");
        fireball.apply(warrior, goblin);
        System.out.println();

        iceShard.apply(warrior, orc);
        System.out.println();

        shadowStrike.apply(warrior, goblin);
        System.out.println();

        // Тестируем Area Skill на группе
        System.out.println("--- Area Skill on Group ---");
        whirlwind.apply(warrior, enemies);
        System.out.println();

        // Демонстрация Bridge - меняем эффект у скилла
        System.out.println("--- Bridge Pattern: Changing Effect ---");
        Skill magicMissile = new SingleTargetSkill("Magic Missile", 25, physical);
        System.out.println("Original: " + magicMissile.getName());

        magicMissile.setEffect(fire);
        System.out.println("Changed to: " + magicMissile.getName());

        magicMissile.setEffect(ice);
        System.out.println("Changed to: " + magicMissile.getName());

        magicMissile.setEffect(shadow);
        System.out.println("Changed to: " + magicMissile.getName());
        System.out.println();


        System.out.println("--- Independent Variation ---");
        System.out.println("Same skill (Strike) with different effects:");

        Skill strike1 = new SingleTargetSkill("Strike", 30, physical);
        Skill strike2 = new SingleTargetSkill("Strike", 30, fire);
        Skill strike3 = new SingleTargetSkill("Strike", 30, ice);

        System.out.println("  " + strike1.getName());
        System.out.println("  " + strike2.getName());
        System.out.println("  " + strike3.getName());
    }
}