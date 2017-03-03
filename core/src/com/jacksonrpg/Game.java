package com.jacksonrpg;

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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jacksonrpg.players.LesserJackson;

/**
 * Created by Adrian on 2/26/17.
 */
public class Game implements Screen {
    final JacksonRPG game;

    public Stage gameStage;

    private LesserJackson lesserJackson;

    public Game(final JacksonRPG gam) {
        this.game = gam;

        lesserJackson = new LesserJackson();

        gameStage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(gameStage);

        gameStage.addActor(lesserJackson);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f,.74f,1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.act(Gdx.graphics.getDeltaTime());
        gameStage.draw();


    }

    @Override
    public void dispose() {
        gameStage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height, true);

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



}
