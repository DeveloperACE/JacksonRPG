package com.jacksonrpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jacksonrpg.players.LesserJackson;

/**
 * Created by Adrian on 2/26/17.
 */
public class Game implements Screen {

    public enum GameState {
        RUNNING, PAUSED, INACTIVE, LOADING
    }

    final JacksonRPG jacksonrpg;
    public Stage gameStage;

    public GameState state = GameState.LOADING;


    public AssetManager assets = new AssetManager();


    public static Texture loadingTexture;
    private BitmapFont font;


    private LesserJackson lesserJackson;




    public Game(final JacksonRPG jacksonrpg) {
        this.jacksonrpg = jacksonrpg;

        //setvars
        font = new BitmapFont();
        loadingTexture = new Texture(Gdx.files.internal("images/items/pillbottle.png"));
        lesserJackson = new LesserJackson(this);
        gameStage = new Stage(/*new ScreenViewport()*/new FitViewport(400, 400));

        //load assets
        loadAssets();



        Gdx.input.setInputProcessor(gameStage);

        gameStage.addActor(lesserJackson);

    }




    public void loadAssets() {
        //set any custom loaders
        assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));


        //load assets
        assets.load("maps/tutorialworld/tutorialworld.tmx", TiledMap.class);
        assets.load("images/HUD/health.atlas", TextureAtlas.class);
        assets.load("images/items/currency.atlas", TextureAtlas.class);
        assets.load("images/backgrounds/Bus-Background.png", Texture.class);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f,.74f,1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        switch(state){
            case LOADING:
                if(assets.update()) {
                    //assets loaded
                    state = GameState.RUNNING;
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

                if(assets.isLoaded("images/backgrounds/Bus-Background.png")) {
                    // texture is available, let's fetch it and do something interesting
                    Texture background = assets.get("images/backgrounds/Bus-Background.png", Texture.class);

                    gameStage.getBatch().draw(background, 0, 0, gameStage.getWidth(), gameStage.getHeight());
                }

                if(assets.isLoaded("images/HUD/health.atlas")) {
                    // texture is available, let's fetch it and do something interesting
                    TextureAtlas healthStates = assets.get("images/HUD/health.atlas", TextureAtlas.class);

                    gameStage.getBatch().draw(healthStates.getRegions().get(lesserJackson.getHealthLost()), 5, gameStage.getHeight()-69, 64, 64);
                }

                if(assets.isLoaded("images/items/currency.atlas")) {
                    // texture is available, let's fetch it and do something interesting
                    TextureAtlas currencyIcon = assets.get("images/items/currency.atlas", TextureAtlas.class);

                    font.draw(gameStage.getBatch(), lesserJackson.getBalance(), 108, gameStage.getHeight()-7);
                    gameStage.getBatch().draw(currencyIcon.findRegion("papercurrency"), 74, gameStage.getHeight()-21, 32, 16);
                }

                gameStage.getBatch().end();

                //update stage and actors
                gameStage.draw();
                gameStage.act(delta);

                break;
            case PAUSED:
                //show in-game menu
                jacksonrpg.setScreen(new MainGameMenu(jacksonrpg));
                //dispose();
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
