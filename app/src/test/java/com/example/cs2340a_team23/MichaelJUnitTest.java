package com.example.cs2340a_team23;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340a_team23.model.GameState;

public class MichaelJUnitTest {

    private GameState gameState;
    @Before
    public void setUp() {
        gameState = GameState.getGameState();
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




}
