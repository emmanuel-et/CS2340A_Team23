package com.example.cs2340a_team23;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import com.example.cs2340a_team23.model.Enemy;
import com.example.cs2340a_team23.model.EnemyCreator;
import com.example.cs2340a_team23.model.GameState;
import com.example.cs2340a_team23.model.GordonWarden;
import com.example.cs2340a_team23.model.GorgonWardenCreator;
import com.example.cs2340a_team23.model.Leaderboard;
import com.example.cs2340a_team23.model.LeaderboardEntry;
import com.example.cs2340a_team23.model.MoltenWasp;
import com.example.cs2340a_team23.model.ShadowRevenant;
import com.example.cs2340a_team23.model.ZephyrClaw;

public class AustinUnitTest {
    // Create parameters

    private String playerName;
    private int playerScore;
    private LocalDate date;
    private LocalTime endTime;

    private Leaderboard leaderboard;
    private LeaderboardEntry currentGameEntry;
    private List<LeaderboardEntry> entries;
    private LeaderboardEntry firstEntry;

    @Before
    public void setUp() {
        date = LocalDate.now();
        endTime = LocalTime.now();
        playerName = "Player1";
        playerScore = 100;

        leaderboard = Leaderboard.getLeaderboard();
        currentGameEntry = new LeaderboardEntry(playerName, date, endTime, playerScore);
        leaderboard.addEntry(currentGameEntry);
        entries = leaderboard.getEntries();
        firstEntry = entries.get(0);
    }

    @Test
    public void checkEntriesSize() {
        assertEquals(1, entries.size());
    }



    @Test
    public void checkEntryData() {
        assertEquals(playerName, firstEntry.getName());
        assertEquals(date, firstEntry.getDate());
        assertEquals(endTime.getHour(), firstEntry.getEndTime().getHour());
        assertEquals(endTime.getMinute(), firstEntry.getEndTime().getMinute());
        assertEquals(playerScore, firstEntry.getScore());
    }
    @Test
    public void testSetName() {
        LeaderboardEntry entry = new LeaderboardEntry("Player1", LocalDate.of(2023,
                10, 1), LocalTime.of(10, 0), 100);
        entry.setName("Player2");
        assertEquals("Player2", entry.getName());
    }

    @Test
    public void testSetScore() {
        LeaderboardEntry entry = new LeaderboardEntry("Player1", LocalDate.of(2023,
                10, 1), LocalTime.of(10, 0), 100);
        entry.setScore(200);
        assertEquals(200, entry.getScore());
    }

    @Test
    public void MoltenGordenSpeed() {
        MoltenWasp moltenWasp = new MoltenWasp(0, 0);
        GordonWarden gordonWarden = new GordonWarden(0, 0);
        assertNotEquals(moltenWasp.getSpeed(), gordonWarden.getSpeed());
    }

    @Test
    public void zephyrShadowSpeed() {
        ShadowRevenant shadowRevenant = new ShadowRevenant(0, 0);
        ZephyrClaw zephyrClaw = new ZephyrClaw(0, 0);
        assertNotEquals(zephyrClaw.getSpeed(), shadowRevenant.getSpeed());
    }

}
