package com.example.cs2340a_team23;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340a_team23.model.Player;

public class MikeAUnitTests {

    private Player player;

    @Before
    public void setUp() {
        player = Player.getPlayer();
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
}
