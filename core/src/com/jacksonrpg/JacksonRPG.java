package com.jacksonrpg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jacksonrpg.game.Game;
import com.jacksonrpg.game.MainMenu;

public class JacksonRPG extends ApplicationAdapter {
	SpriteBatch batch;
	MainMenu gameMenu;
	Game game;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameMenu = new MainMenu();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.5f,.74f,1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);// This cryptic line clears the screen.

		batch.begin();
        //display menu
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		gameMenu.dispose();

	}
}
