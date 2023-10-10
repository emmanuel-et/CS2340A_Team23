package com.example.cs2340a_team23.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
public class Leaderboard {

    private static Leaderboard leaderboard;
    private List<LeaderboardEntry> entries;

    private Leaderboard() {
        entries = new ArrayList<>();
    }

    public static Leaderboard getLeaderboard() {
        if (leaderboard == null) {
            leaderboard = new Leaderboard();
        }
        return leaderboard;
    }

    public void addEntry(LeaderboardEntry entry) {
        entries.add(entry);
    }

    public void sortEntriesByScoreDescending() {
        Collections.sort(entries, new Comparator<LeaderboardEntry>() {
            @Override
            public int compare(LeaderboardEntry entry1, LeaderboardEntry entry2) {
                // Compare in descending order by score
                return Integer.compare(entry2.getScore(), entry1.getScore());
            }
        });
    }
    public List<LeaderboardEntry> getEntries() {
        return entries;
    }
}

