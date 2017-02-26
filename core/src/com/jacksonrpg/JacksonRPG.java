package com.jacksonrpg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jacksonrpg.players.LesserJackson;

public class JacksonRPG extends ApplicationAdapter {
	SpriteBatch batch;
	LesserJackson lesserJackson;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		lesserJackson = new LesserJackson();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.5f,.74f,1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);// This cryptic line clears the screen.

		batch.begin();
		batch.draw(lesserJackson.sprite, 0, 0, 200, 200);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
