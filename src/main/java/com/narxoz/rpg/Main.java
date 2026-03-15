package com.narxoz.rpg;

import com.narxoz.rpg.composite.*;
import com.narxoz.rpg.bridge.*;
import com.narxoz.rpg.battle.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== HOMEWORK 4: RPG RAID SYSTEM ===\n");

        // PART 1: COMPOSITE PATTERN - Create raid structure
        System.out.println("--- PART 1: COMPOSITE PATTERN ---");
        System.out.println("Creating raid hierarchy...\n");

        // Create heroes
        HeroUnit warrior = new HeroUnit("Артур", "Warrior", 120, 25);
        HeroUnit mage = new HeroUnit("Мерлин", "Mage", 80, 30);
        HeroUnit rogue = new HeroUnit("Лира", "Rogue", 90, 28);
        HeroUnit priest = new HeroUnit("Элейн", "Priest", 70, 15);

        // Create enemies
        EnemyUnit goblin = new EnemyUnit("Гоблин", "Goblin", 60, 12);
        EnemyUnit orc = new EnemyUnit("Орк", "Orc", 100, 18);
        EnemyUnit troll = new EnemyUnit("Тролль", "Troll", 150, 15);
        EnemyUnit dragon = new EnemyUnit("Дракон", "Dragon", 200, 35);

        // Create parties
        PartyComposite heroesParty1 = new PartyComposite("Герои Отряд 1");
        heroesParty1.add(warrior);
        heroesParty1.add(mage);

        PartyComposite heroesParty2 = new PartyComposite("Герои Отряд 2");
        heroesParty2.add(rogue);
        heroesParty2.add(priest);

        // Create raid group (nested structure)
        RaidGroup heroesRaid = new RaidGroup("Герои Рейд");
        heroesRaid.add(heroesParty1);
        heroesRaid.add(heroesParty2);

        // Enemy hierarchy
        PartyComposite enemyParty1 = new PartyComposite("Враги Отряд 1");
        enemyParty1.add(goblin);
        enemyParty1.add(orc);

        PartyComposite enemyParty2 = new PartyComposite("Враги Отряд 2");
        enemyParty2.add(troll);

        RaidGroup enemiesRaid = new RaidGroup("Враги Рейд");
        enemiesRaid.add(enemyParty1);
        enemiesRaid.add(enemyParty2);
        enemiesRaid.add(dragon);

        // Print structure
        System.out.println("Heroes Raid Structure:");
        heroesRaid.printTree("");
        System.out.println("\nEnemies Raid Structure:");
        enemiesRaid.printTree("");

        // PART 2: BRIDGE PATTERN - Create skills with effects
        System.out.println("\n--- PART 2: BRIDGE PATTERN ---");
        System.out.println("Creating skill-effect combinations...\n");

        // Create effects
        EffectImplementor physical = new PhysicalEffect();
        EffectImplementor fire = new FireEffect();
        EffectImplementor ice = new IceEffect();
        EffectImplementor shadow = new ShadowEffect();

        // Create skills with different effects
        Skill fireball = new SingleTargetSkill("Fireball", 30, fire);
        Skill iceShard = new SingleTargetSkill("Ice Shard", 25, ice);
        Skill shadowStrike = new SingleTargetSkill("Shadow Strike", 35, shadow);
        Skill whirlwind = new AreaSkill("Whirlwind", 20, physical, 3);
        Skill meteor = new AreaSkill("Meteor", 40, fire, 5);

        System.out.println("Available skills:");
        System.out.println("  " + fireball.getName());
        System.out.println("  " + iceShard.getName());
        System.out.println("  " + shadowStrike.getName());
        System.out.println("  " + whirlwind.getName());
        System.out.println("  " + meteor.getName());

        // PART 3: INTEGRATION - Run raid battle
        System.out.println("\n--- PART 3: RAID BATTLE INTEGRATION ---");

        // Create raid engine
        RaidEngine engine = new RaidEngine();
        engine.setRandomSeed(42); // Deterministic battle

        // Add skills to engine
        engine.addSkill(fireball);
        engine.addSkill(iceShard);
        engine.addSkill(shadowStrike);
        engine.addSkill(whirlwind);
        engine.addSkill(meteor);

        // Run battle
        System.out.println("\nStarting RAID BATTLE between Heroes and Enemies...\n");
        RaidResult result = engine.runRaid(heroesRaid, enemiesRaid);

        // Print results
        result.printSummary();

        // PART 4: VERIFICATION
        System.out.println("\n--- PART 4: PATTERN VERIFICATION ---");

        // Composite verification
        System.out.println("✓ COMPOSITE: Single units and groups use same interface");
        System.out.println("  Heroes total attack: " + heroesRaid.getAttackPower());
        System.out.println("  Enemies total health: " + enemiesRaid.getHealth());

        // Bridge verification
        System.out.println("\n✓ BRIDGE: Skills and effects vary independently");
        Skill sameSkill = new SingleTargetSkill("Test Skill", 50, physical);
        System.out.println("  Physical version: " + sameSkill.getName());
        sameSkill.setEffect(fire);
        System.out.println("  Fire version: " + sameSkill.getName());
        sameSkill.setEffect(ice);
        System.out.println("  Ice version: " + sameSkill.getName());

        System.out.println("\n=== HOMEWORK 4 COMPLETE ===");
    }
}