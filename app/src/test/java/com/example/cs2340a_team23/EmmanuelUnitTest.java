package com.example.cs2340a_team23;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;



import com.example.cs2340a_team23.model.GameState;
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
        GameState gameState = GameState.getGameState();
        Player player = Player.getPlayer();
        player.setHealth(100);
        gameState.setDifficulty("Easy");
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
        LeaderboardEntry entry = new LeaderboardEntry("Player1", LocalDate.of(2023,
                10, 1), LocalTime.of(10, 0), 100);

        leaderboard.addEntry(entry);

        List<LeaderboardEntry> entries = leaderboard.getEntries();

        assertEquals(1, entries.size());

        leaderboard.getEntries().clear();

        entries = leaderboard.getEntries();

        assertEquals(0, entries.size());
    }
    @Test
    public void testGetName() {
        LeaderboardEntry entry = new LeaderboardEntry("Player1", LocalDate.of(2023,
                10, 1), LocalTime.of(10, 0), 100);
        assertEquals("Player1", entry.getName());
    }

    @Test
    public void testCollisionDamageOnEasyDifficulty() {
        simulateCollision();
        assertEquals(90, Player.getPlayer().getHealth());
    }

    @Test
    public void testCollisionDamageOnMediumDifficulty() {
        GameState.getGameState().setDifficulty("Medium");
        simulateCollision();
        assertEquals(80, Player.getPlayer().getHealth());
    }
    /**
     * Simulates a collision between the player and an obstacle or enemy.
     * Adjusts the player's health based on the current game difficulty level.
     * The damage inflicted on the player varies according to the difficulty:
     * - Easy: 10 damage
     * - Medium: 20 damage
     * - Hard: 30 damage
     * If the difficulty is unknown, no damage is applied to the player.
     */
    private void simulateCollision() {
        int damage;
        String difficulty = GameState.getGameState().getDifficulty();
        switch (difficulty) {
        case "Easy":
            damage = 10;
            break;
        case "Medium":
            damage = 20;
            break;
        case "Hard":
            damage = 30;
            break;
        default:
            damage = 0;
            break;
        }
        Player.getPlayer().setHealth(Player.getPlayer().getHealth() - damage);
    }

}