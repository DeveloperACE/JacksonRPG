package com.jacksonrpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Matrix4;
import com.jacksonrpg.JacksonRPG;
import com.jacksonrpg.entities.Entity;
import com.jacksonrpg.entities.Player;
import com.jacksonrpg.maps.World;

/**
 * Created by Adrian on 3/14/17.
 */
public class Game implements Screen {

    JacksonRPG jacksonrpg;
    World world;
   public Player character;

    public Game(JacksonRPG jrpginstance, World.Level level) {
        this.jacksonrpg = jrpginstance;
        world = new World(jacksonrpg, level);
        character = new Player(jacksonrpg.assets.lesserjacksonWalking);
    }

    public Game(JacksonRPG jrpginstance) {
        this(jrpginstance, World.Level.TUTORIAL);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.render(delta);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void resize(int width, int height) {

    }
}
