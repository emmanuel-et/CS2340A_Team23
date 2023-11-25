package com.example.cs2340a_team23;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


import com.example.cs2340a_team23.model.GordonWarden;
import com.example.cs2340a_team23.model.Leaderboard;
import com.example.cs2340a_team23.model.MoltenWasp;
import com.example.cs2340a_team23.model.Player;
import com.example.cs2340a_team23.model.ShadowRevenant;
import com.example.cs2340a_team23.model.Walk;
import com.example.cs2340a_team23.model.ZephyrClaw;

public class ShawnaUnitTest {
    private Leaderboard leaderboard;
    private Walk walk;
    private Player player;

    @Before
    public void setUp() {
        player = Player.getPlayer();
        walk = new Walk();
    }
    @Test
    public void checkInitializePlayer() {
        player.initializePlayer("Rocko", 200, "mario");
        assertEquals("Rocko", player.getPlayerName());
        assertEquals(200, player.getHealth());
        assertEquals("mario", player.getSprite());
    }

    @Test
    public void checkPlayerSprite() {
        player.setSprite("megaman");
        assertEquals("megaman", player.getSprite());
    }
    @Test
    public void testMoveDown() {
        float[] initialPosition = {100.0f, 100.0f};
        int screenWidth = 800;
        int screenHeight = 600;
        int spriteWidth = 50;
        int spriteHeight = 50;
        float[] newPosition = walk.move(initialPosition[0], initialPosition[1], "down",
                screenWidth, screenHeight, spriteWidth, spriteHeight);
        float[] expectedPosition = {100.0f, 150.0f};
        assertArrayEquals(expectedPosition, newPosition, 0.01F);
    }
    @Test
    public void testMoveLeft() {
        float[] initialPosition = {100.0f, 100.0f};
        int screenWidth = 800;
        int screenHeight = 600;
        int spriteWidth = 50;
        int spriteHeight = 50;
        float[] newPosition = walk.move(initialPosition[0], initialPosition[1], "left",
                screenWidth, screenHeight, spriteWidth, spriteHeight);
        float[] expectedPosition = {50.0f, 100.0f};
        assertArrayEquals(expectedPosition, newPosition, 0.01F);
    }

    @Test
    public void setPlayerNameWithNumbersIsCorrect() {
        player.setPlayerName("123");
        assertEquals("123", player.getPlayerName());
    }

    @Test
    public void testMolZyCollisionSpeed() {
        MoltenWasp moltenWasp = new MoltenWasp(0, 0);
        ZephyrClaw zephyrClaw = new ZephyrClaw(0, 0);
        simulateCollision(moltenWasp, zephyrClaw);
        assertEquals(20.0f, moltenWasp.getSpeed(), 0.01f);
        assertEquals(40.0f, zephyrClaw.getSpeed(), 0.01f);
    }
    /**
     *
     * @param zephyrClaw enemy
     * @param moltenWasp enemy
     */
    private void simulateCollision(MoltenWasp moltenWasp, ZephyrClaw zephyrClaw) {
        moltenWasp.handleCollision("Medium");
        zephyrClaw.handleCollision("Medium");
    }

    @Test
    public void gordonSetNegativeSpeed() {
        GordonWarden gordon = new GordonWarden(0, 0);
        gordon.setSpeed(-20);
        assertEquals(gordon.getSpeed(), -20);
    }

    @Test
    public void shadowSetNegativeSpeed() {
        ShadowRevenant shadow = new ShadowRevenant(0, 0);
        shadow.setSpeed(-42);
        assertEquals(shadow.getSpeed(), -42);
    }
}
