package com.jacksonrpg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jacksonrpg.game.MainGameMenu;

/**
 * This class is basically a screen/window-manager for the entire application
 */
public class JacksonRPG extends Game {

    public SpriteBatch gameBatch;
   // public BitmapFont font;

    public void create() {

        //Use LibGDX's default Arial font.
       // font = new BitmapFont();

        //the main menu sets the screen of JacksonRPG to the game when play is clicked
        this.setScreen(new MainGameMenu(this));
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
       // gameBatch.dispose();
        //font.dispose();
    }
}
