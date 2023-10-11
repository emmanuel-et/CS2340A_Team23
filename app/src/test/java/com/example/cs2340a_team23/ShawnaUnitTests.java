package com.example.cs2340a_team23;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340a_team23.model.Player;

public class ShawnaUnitTests {

    private Player player;

    @Before
    public void setUp() {
        player = Player.getPlayer();
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
}
