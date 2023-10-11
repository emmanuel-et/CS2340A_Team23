package com.example.cs2340a_team23;


import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


import com.example.cs2340a_team23.model.Player;


public class simiTest21 {


    private Player player;

    @Before
    public void setUp() {
        player = Player.getPlayer();
    }
    @Test
    public void healthIsRight() {
        assertEquals(100, player.getHealth());
    }

    @Test
    public void testSetSprite() {
        player.setSprite("NewSprite");
        assertEquals("NewSprite", player.getSprite());
    }


}
