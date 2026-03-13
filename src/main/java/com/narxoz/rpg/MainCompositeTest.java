package com.narxoz.rpg;

import com.narxoz.rpg.composite.*;

public class MainCompositeTest {
    public static void main(String[] args) {
        System.out.println("=== COMPOSITE PATTERN TEST ===\n");

        // Создаем героев
        HeroUnit warrior = new HeroUnit("Артур", "Warrior", 120, 25);
        HeroUnit mage = new HeroUnit("Мерлин", "Mage", 80, 30);
        HeroUnit rogue = new HeroUnit("Лира", "Rogue", 90, 28);

        // Создаем врагов
        EnemyUnit goblin = new EnemyUnit("Гоблин", "Goblin", 60, 12);
        EnemyUnit orc = new EnemyUnit("Орк", "Orc", 100, 18);
        EnemyUnit troll = new EnemyUnit("Тролль", "Troll", 150, 15);

        // Создаем партии
        PartyComposite heroesParty = new PartyComposite("Герои");
        heroesParty.add(warrior);
        heroesParty.add(mage);
        heroesParty.add(rogue);

        PartyComposite enemiesParty = new PartyComposite("Враги");
        enemiesParty.add(goblin);
        enemiesParty.add(orc);

        // Создаем рейд группу (вложенная структура)
        RaidGroup mainRaid = new RaidGroup("Основной Рейд");
        mainRaid.add(heroesParty);
        mainRaid.add(enemiesParty);

        RaidGroup bossGroup = new RaidGroup("Босс Группа");
        bossGroup.add(troll);
        mainRaid.add(bossGroup);

        // Печатаем дерево
        System.out.println("Структура рейда:");
        mainRaid.printTree("");

        // Тестируем урон
        System.out.println("\n--- Тест урона ---");
        System.out.println("Общая атака рейда: " + mainRaid.getAttackPower());
        System.out.println("Общее здоровье рейда: " + mainRaid.getHealth());

        System.out.println("\nНаносим 100 урона всему рейду:");
        mainRaid.takeDamage(100);

        System.out.println("\nОбновленная структура:");
        mainRaid.printTree("");
    }
}