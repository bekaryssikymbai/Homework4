package com.narxoz.rpg.battle;

import com.narxoz.rpg.composite.CombatNode;
import com.narxoz.rpg.bridge.Skill;
import java.util.*;

public class RaidEngine {
    private Random random = new Random();
    private List<Skill> availableSkills;

    public RaidEngine() {
        this.availableSkills = new ArrayList<>();
    }

    public RaidEngine setRandomSeed(long seed) {
        random = new Random(seed);
        return this;
    }

    public void addSkill(Skill skill) {
        availableSkills.add(skill);
    }

    public RaidResult runRaid(CombatNode teamA, CombatNode teamB) {
        RaidResult result = new RaidResult();
        List<String> log = new ArrayList<>();

        log.add("⚔️ RAID BATTLE BEGINS ⚔️");
        log.add("\nTeam A:");
        teamA.printTree("  ");
        log.add("\nTeam B:");
        teamB.printTree("  ");

        int round = 1;
        String winner = "";

        while (teamA.isAlive() && teamB.isAlive()) {
            log.add("\n--- ROUND " + round + " ---");

            // Team A attacks
            performTeamAttack(teamA, teamB, log, "Team A");

            if (!teamB.isAlive()) {
                winner = "Team A";
                break;
            }

            // Team B attacks
            performTeamAttack(teamB, teamA, log, "Team B");

            if (!teamA.isAlive()) {
                winner = "Team B";
                break;
            }

            round++;
        }

        log.add("\n🏆 " + winner + " VICTORY! 🏆");

        for (String line : log) {
            result.addLog(line);
        }
        result.setWinner(winner);
        result.setRounds(round);

        return result;
    }

    private void performTeamAttack(CombatNode attackers, CombatNode defenders,
                                   List<String> log, String teamName) {
        if (availableSkills.isEmpty()) {
            log.add("  No skills available!");
            return;
        }

        // Get all alive leaf nodes from attackers
        List<CombatNode> aliveAttackers = getAliveLeaves(attackers);

        for (CombatNode attacker : aliveAttackers) {
            if (!defenders.isAlive()) break;

            // Choose random skill
            Skill skill = availableSkills.get(random.nextInt(availableSkills.size()));

            // Choose random target from defenders
            List<CombatNode> targets = getAliveLeaves(defenders);
            if (targets.isEmpty()) break;

            CombatNode target = targets.get(random.nextInt(targets.size()));

            log.add("  " + teamName + ": " + attacker.getName() + " uses " + skill.getName());
            skill.apply(attacker, target);
        }
    }

    private List<CombatNode> getAliveLeaves(CombatNode node) {
        List<CombatNode> leaves = new ArrayList<>();
        collectAliveLeaves(node, leaves);
        return leaves;
    }

    private void collectAliveLeaves(CombatNode node, List<CombatNode> leaves) {
        if (node.getChildren() == null || node.getChildren().isEmpty()) {
            if (node.isAlive()) {
                leaves.add(node);
            }
        } else {
            for (CombatNode child : node.getChildren()) {
                collectAliveLeaves(child, leaves);
            }
        }
    }

    public void reset() {
        // Reset any internal state if needed
    }
}