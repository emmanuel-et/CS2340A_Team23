package com.example.cs2340a_team23;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340a_team23.model.Player;
import com.example.cs2340a_team23.model.Run;
import com.example.cs2340a_team23.model.GameState;

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
