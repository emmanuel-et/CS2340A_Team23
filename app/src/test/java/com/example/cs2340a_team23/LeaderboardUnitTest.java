package com.example.cs2340a_team23;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;



import com.example.cs2340a_team23.model.Leaderboard;
import com.example.cs2340a_team23.model.LeaderboardEntry;

public class LeaderboardUnitTest {
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


    // Verify the details of the added entry
    @Test
    public void checkEntryData() {
        assertEquals(playerName, firstEntry.getName());
        assertEquals(date, firstEntry.getDate());
        assertEquals(endTime.getHour(), firstEntry.getEndTime().getHour());
        assertEquals(endTime.getMinute(), firstEntry.getEndTime().getMinute());
        assertEquals(playerScore, firstEntry.getScore());
    }

}
