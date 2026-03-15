package com.narxoz.rpg.battle;

import java.util.ArrayList;
import java.util.List;

public class RaidResult {
    private String winner;
    private int rounds;
    private List<String> battleLog = new ArrayList<>();

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void addLog(String line) {
        battleLog.add(line);
    }

    public String getWinner() {
        return winner;
    }

    public int getRounds() {
        return rounds;
    }

    public List<String> getBattleLog() {
        return battleLog;
    }

    public void printSummary() {
        System.out.println("\n=== RAID BATTLE RESULTS ===");
        System.out.println("Winner: " + winner);
        System.out.println("Rounds: " + rounds);
        System.out.println("\nBattle Log:");
        for (String line : battleLog) {
            System.out.println(line);
        }
    }
}