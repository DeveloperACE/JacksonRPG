package com.jacksonrpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jacksonrpg.players.LesserJackson;

/**
 * Created by Adrian on 2/26/17.
 */
public class Game implements Screen {
    final JacksonRPG game;

    public Batch gameBatch;
    private Stage gameStage;

    private LesserJackson lesserJackson;

    public Game(final JacksonRPG gam) {
        this.game = gam;

        lesserJackson = new LesserJackson();
        System.out.println("Done");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f,.74f,1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameBatch.begin();
        gameBatch.draw(lesserJackson.sprite, 0, 0, 100, 100);

        gameBatch.end();
    }

    @Override
    public void dispose() {
    }

    @Override
    public void resize(int width, int height) {
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
