package com.jacksonrpg;

import com.jacksonrpg.game.screens.Game;
import com.jacksonrpg.game.screens.MainMenu;
import org.junit.jupiter.api.Test;

import org.junit.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by edwar12421 on 5/10/2017.
 */
public class UnitTests {

    JacksonRPG jacksonRPG = new JacksonRPG();

    @Test
    public void testJacksonRPGMenu()
    {
        jacksonRPG.makeMenu();

        assertEquals(new MainMenu(jacksonRPG), jacksonRPG.getMenu());

    }

    @Test
    public void testJacksonRPGGame()
    {
        jacksonRPG.makeGame();

        assertEquals(new Game(jacksonRPG), jacksonRPG.getGame());

    }
    @Test
    public void ()
    {
        jacksonRPG.makeMenu();

        assertEquals(new MainMenu(jacksonRPG), jacksonRPG.getMenu());

    }
}
