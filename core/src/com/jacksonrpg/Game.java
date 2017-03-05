package com.jacksonrpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jacksonrpg.players.LesserJackson;

/**
 * Created by Adrian on 2/26/17.
 */
public class Game implements Screen {

    public enum GameState {
        RUNNING, PAUSED, INACTIVE, LOADING
    }

    final JacksonRPG jacksonrpg;
    private GameState gameState = GameState.LOADING;


    public static Texture loadingTexture;

    public Stage gameStage;

    public AssetManager assets = new AssetManager();



    private LesserJackson lesserJackson;

    public Game(final JacksonRPG game) {
        this.game = game;

        loadingTexture = new Texture(Gdx.files.internal("items/pillbottle.png"));
        //backgroundTexture = new Texture(Gdx.files.internal("backgrounds/Bus-Background.png"));
        //backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        lesserJackson = new LesserJackson(this);
        assets.load("backgrounds/Bus-Background.png", Texture.class);
       // assets.load("backgrounds/Bus-Background.png", Texture.class);

        gameStage = new Stage(/*new ScreenViewport()*/new FitViewport(400, 400));
        Gdx.input.setInputProcessor(gameStage);







        gameStage.addActor(lesserJackson);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f,.74f,1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        switch(gameState){
            case LOADING:
                if(assets.update()) {
                    //assets loaded
                    gameState = GameState.RUNNING;
                    lesserJackson.loaded();
                } else {
                    // display loading information
                    float progress = assets.getProgress();
                    gameStage.getBatch().begin();
                    gameStage.getBatch().draw(loadingTexture, gameStage.getWidth()*progress, 150, 100, 100);
                    gameStage.getBatch().end();
                }
                break;
            case RUNNING:
                //add background
                gameStage.getBatch().begin();

                if(assets.isLoaded("backgrounds/Bus-Background.png")) {
                    // texture is available, let's fetch it and do something interesting
                    Texture background = assets.get("backgrounds/Bus-Background.png", Texture.class);

                    gameStage.getBatch().draw(background, 0, 0, gameStage.getWidth(), gameStage.getHeight());
                }

                gameStage.getBatch().end();

                //update stage and actors
                gameStage.draw();
                gameStage.act(delta);

                break;
            case PAUSED:
                //show menu
                break;
            case INACTIVE:
                //show overlay w/ pause icon
                break;
        }

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
        //System.out.println("PAUSE");
    }

    @Override
    public void resume() {
       // System.out.println("RESUME");
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }



}
