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

public class EmmanuelUnitTest {
    private Leaderboard leaderboard;


    private Player player;

    @Before
    public void setUp() {
        player = Player.getPlayer();
        leaderboard = Leaderboard.getLeaderboard();
        leaderboard.getEntries().clear();
    }
    @Test
    public void playerNameIsCorrect() {
        player.setPlayerName("Emmanuel");
        assertEquals("Emmanuel", player.getPlayerName());
    }

    @Test
    public void playerSpriteIsCorrect() {
        player.setSprite("mario");
        assertEquals("mario", player.getSprite());
    }
    @Test
    public void testAddAndRemoveEntry() {
        LeaderboardEntry entry = new LeaderboardEntry("Player1", LocalDate.of(2023, 10, 1), LocalTime.of(10, 0), 100);

        leaderboard.addEntry(entry);

        List<LeaderboardEntry> entries = leaderboard.getEntries();

        assertEquals(1, entries.size());

        leaderboard.getEntries().clear();

        entries = leaderboard.getEntries();

        assertEquals(0, entries.size());
    }
    @Test
    public void testGetName() {
        LeaderboardEntry entry = new LeaderboardEntry("Player1", LocalDate.of(2023, 10, 1), LocalTime.of(10, 0), 100);
        assertEquals("Player1", entry.getName());
    }

}