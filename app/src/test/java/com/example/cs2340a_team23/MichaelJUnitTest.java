package com.example.cs2340a_team23;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;


import com.example.cs2340a_team23.model.GordonWarden;
import com.example.cs2340a_team23.model.MoltenWasp;
import com.example.cs2340a_team23.model.Player;
import com.example.cs2340a_team23.model.Run;
import com.example.cs2340a_team23.model.GameState;
import com.example.cs2340a_team23.model.ShadowRevenant;
import com.example.cs2340a_team23.model.ZephyrClaw;

public class MichaelJUnitTest {

    private Run run;

    private GameState gameState;
    @Before
    public void setUp() {
        gameState = GameState.getGameState();
        run = new Run();
        GameState gameState = GameState.getGameState();
        Player player = Player.getPlayer();
        player.setHealth(100);
        gameState.setDifficulty("Easy");
        GameState.resetGameState();
        Player.resetPlayer();
    }


    @Test
    public void difficultyIsCorrect() {
        gameState.setDifficulty("hard");
        assertEquals("hard", gameState.getDifficulty());
    }
    @Test
    public void scoreIsCorrect() {
        gameState.setScore(1000);
        assertEquals(1000, gameState.getScore());

    }
    @Test
    public void testMoveUp() {
        float[] initialPosition = {100.0f, 100.0f};
        int screenWidth = 800;
        int screenHeight = 600;
        int spriteWidth = 50;
        int spriteHeight = 50;
        float[] newPosition = run.move(initialPosition[0], initialPosition[1], "up",
                screenWidth, screenHeight, spriteWidth, spriteHeight);
        float[] expectedPosition = {100.0f, 0.0f};
        assertArrayEquals(expectedPosition, newPosition, 0.01F);
    }
    @Test
    public void testMoveDown() {
        float[] initialPosition = {100.0f, 100.0f};
        int screenWidth = 800;
        int screenHeight = 600;
        int spriteWidth = 50;
        int spriteHeight = 50;
        float[] newPosition = run.move(initialPosition[0], initialPosition[1], "down",
                screenWidth, screenHeight, spriteWidth, spriteHeight);
        float[] expectedPosition = {100.0f, 200.0f};
        assertArrayEquals(expectedPosition, newPosition, 0.01F);
    }

    @Test
    public void testCollisionDamageOnHardDifficulty() {
        GameState.getGameState().setDifficulty("Hard");
        simulateCollision();
        assertEquals(70, Player.getPlayer().getHealth());
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


    @Test
    public void shadowGorden() {
        ShadowRevenant shadowRevenant = new ShadowRevenant(0, 0);
        GordonWarden gordonWarden = new GordonWarden(0, 0);
        assertNotEquals(gordonWarden.getSpeed(), shadowRevenant.getSpeed());
    }

    @Test
    public void zephyrSetSpeed() {
        ZephyrClaw zephyrClaw = new ZephyrClaw(0, 0);
        zephyrClaw.setSpeed(200);
        assertEquals(zephyrClaw.getSpeed(), 200);
    }

    @Test
    public void moltenSetSpeed() {
        MoltenWasp moltenWasp = new MoltenWasp(0, 0);
        moltenWasp.setSpeed(300);
        assertEquals(moltenWasp.getSpeed(), 300);
    }
}
