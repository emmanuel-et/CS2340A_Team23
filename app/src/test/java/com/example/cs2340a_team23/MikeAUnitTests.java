package com.example.cs2340a_team23;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340a_team23.model.Player;
import com.example.cs2340a_team23.model.Run;
import com.example.cs2340a_team23.model.Walk;

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
        float[] newPosition = walk.move(initialPosition[0], initialPosition[1], "up", screenWidth, screenHeight, spriteWidth, spriteHeight);
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
        float[] newPosition = run.move(initialPosition[0], initialPosition[1], "left", screenWidth, screenHeight, spriteWidth, spriteHeight);
        float[] expectedPosition = {0.0f, 100.0f};
        assertArrayEquals(expectedPosition, newPosition, 0.01F);
    }
}
