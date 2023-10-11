package com.example.cs2340a_team23;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340a_team23.model.Player;

public class EmmanuelUnitTest {

    private Player player;

    @Before
    public void setUp() {
        player = Player.getPlayer();
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
}