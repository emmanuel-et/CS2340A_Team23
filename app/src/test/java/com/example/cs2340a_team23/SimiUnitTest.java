package com.example.cs2340a_team23;


import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


import com.example.cs2340a_team23.model.Leaderboard;
import com.example.cs2340a_team23.model.LeaderboardEntry;
import com.example.cs2340a_team23.model.Player;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class SimiUnitTest {
    private Leaderboard leaderboard;
    private LeaderboardEntry entry1;
    private LeaderboardEntry entry2;

    private Player player;

    @Before
    public void setUp() {
        player = Player.getPlayer();
        leaderboard = Leaderboard.getLeaderboard();
        entry1 = new LeaderboardEntry("Player1", LocalDate.of(2023, 10,
                1), LocalTime.of(10, 0), 100);
        entry2 = new LeaderboardEntry("Player2", LocalDate.of(2023, 10,
                2), LocalTime.of(12, 0), 200);
    }
    @Test
    public void healthIsRight() {
        assertEquals(100, player.getHealth());
    }

    @Test
    public void testSetSprite() {
        player.setSprite("NewSprite");
        assertEquals("NewSprite", player.getSprite());
    }
    @Test
    public void testAddAndSortEntries() {
        leaderboard.addEntry(entry1);
        leaderboard.addEntry(entry2);
        leaderboard.sortEntriesByScoreDescending();
        List<LeaderboardEntry> sortedEntries = leaderboard.getEntries();
        assertEquals("Player2", sortedEntries.get(0).getName());
        assertEquals("Player1", sortedEntries.get(1).getName());
    }
    @Test
    public void testGetEndTime() {
        LocalTime endTime = LocalTime.of(10, 0);
        LeaderboardEntry entry = new LeaderboardEntry("Player1", LocalDate.of(2023, 10, 1), endTime, 100);
        assertEquals(endTime, entry.getEndTime());
    }
}
