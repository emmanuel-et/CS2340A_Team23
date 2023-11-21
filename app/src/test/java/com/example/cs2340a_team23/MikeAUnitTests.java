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
import com.example.cs2340a_team23.model.Walk;
import com.example.cs2340a_team23.model.ZephyrClaw;

public class MikeAUnitTests {
    private Run run;
    private Walk walk;
    private Player player;

    @Before
    public void setUp() {
        run = new Run();
        player = Player.getPlayer();
        walk = new Walk();
    }
    @Test
    public void setNewHealthIsCorrect() {
        player.setHealth(300);
        assertEquals(300, player.getHealth());
    }

    @Test
    public void setPlayerNameWithSymbolsIsCorrect() {
        player.setPlayerName("???");
        assertEquals("???", player.getPlayerName());
    }
    @Test
    public void testMoveUp() {
        float[] initialPosition = {100.0f, 100.0f};
        int screenWidth = 800;
        int screenHeight = 600;
        int spriteWidth = 50;
        int spriteHeight = 50;
        float[] newPosition = walk.move(initialPosition[0], initialPosition[1], "up",
                screenWidth, screenHeight, spriteWidth, spriteHeight);
        float[] expectedPosition = {100.0f, 50.0f};
        assertArrayEquals(expectedPosition, newPosition, 0.01F);
    }
    @Test
    public void testMoveLeft() {
        float[] initialPosition = {100.0f, 100.0f};
        int screenWidth = 800;
        int screenHeight = 600;
        int spriteWidth = 50;
        int spriteHeight = 50;
        float[] newPosition = run.move(initialPosition[0], initialPosition[1], "left",
                screenWidth, screenHeight, spriteWidth, spriteHeight);
        float[] expectedPosition = {0.0f, 100.0f};
        assertArrayEquals(expectedPosition, newPosition, 0.01F);
    }

    @Test
    public void zephyrMoltenSpeed() {
        ZephyrClaw zephyrClaw = new ZephyrClaw(0, 0);
        MoltenWasp moltenWasp = new MoltenWasp(0, 0);
        assertNotEquals(zephyrClaw.getSpeed(), moltenWasp.getSpeed());
    }

    @Test
    public void testMolGorCollisionSpeed() {
        MoltenWasp moltenWasp = new MoltenWasp(0, 0);
        GordonWarden gordonWarden = new GordonWarden(0, 0);
        simulateCollision(moltenWasp, gordonWarden);
        assertEquals(20.0f, moltenWasp.getSpeed(), 0.01f);
        assertEquals(35.0f, gordonWarden.getSpeed(), 0.01f);
    }
    /**
     *
     * @param moltenWasp enemy
     * @param gordonWarden enemy
     */
    private void simulateCollision(MoltenWasp moltenWasp, GordonWarden gordonWarden) {
        moltenWasp.handleCollision("Medium");
        gordonWarden.handleCollision("Medium");
    }

}
