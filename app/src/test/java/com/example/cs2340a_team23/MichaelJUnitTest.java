package com.example.cs2340a_team23;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340a_team23.model.GameState;
import com.example.cs2340a_team23.model.Player;


public class MichaelJUnitTest {

    private GameState gameState;

    public void setUp() {
        gameState = GameState.getGameState();
    }
    private Player player;

    public void setUp2() {
        player = Player.getPlayer();
    }


    @Test
    public void difficultyTest() {
        assertEquals("hard", gameState.getDifficulty());
    }

    public void scoreTest() {
        gameState.setScore(1000);
        assertEquals(1000, gameState.getScore());

    }
    public void playerTest() {
        player.setPlayerName("Mikeyj");
        assertEquals("Mikeyj", player.getPlayerName());

    }



}
