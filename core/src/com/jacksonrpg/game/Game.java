package com.jacksonrpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jacksonrpg.JacksonRPG;
import com.jacksonrpg.characters.players.LesserJackson;

/**
 * Created by Adrian on 2/26/17.
 */
public class Game implements Screen {

    public enum GameState {
        RUNNING, PAUSED, INACTIVE, LOADING
    }

    final JacksonRPG jacksonrpg;

    public AssetManager assets = new AssetManager();

    public Stage gameStage;
    public GameState state = GameState.LOADING;

    public static Texture loadingTexture;

    private BitmapFont font;
    private LesserJackson character;


    public Game(final JacksonRPG jacksonrpg) {
        this.jacksonrpg = jacksonrpg;

        //setvars
        font = new BitmapFont();
        loadingTexture = new Texture(Gdx.files.internal("images/items/pillbottle.png"));
        character = new LesserJackson(this);
        gameStage = new Stage(/*new ScreenViewport()*/new FitViewport(400, 400));

        //load assets
        loadAssets();



        Gdx.input.setInputProcessor(gameStage);

        gameStage.addActor(character);

    }




    public void loadAssets() {
        //set any custom loaders
        assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));


        //load assets
        //TODO: load map as background
        assets.load("maps/tutorialworld/tutorialworld.tmx", TiledMap.class);

    }

    public void renderAsset(String fileName, Class<Texture> fileType, Batch batch, float x, float y, float width, float height) {

        if(assets.isLoaded(fileName)) {
            batch.draw(assets.get(fileName, fileType), x, y, width, height);
        }
    }

    public void renderAsset(String fileName, String regionName, Class<TextureAtlas> fileType, Batch batch, float x, float y, float width, float height) {

        if(assets.isLoaded(fileName)) {
                batch.draw(assets.get(fileName, fileType).findRegion(regionName), x, y, width, height);
        }
    }

    public void renderAsset(String fileName, Integer regionIndex, Class<TextureAtlas> fileType, Batch batch, float x, float y, float width, float height) {

        if(assets.isLoaded(fileName)) {
            batch.draw(assets.get(fileName, fileType).getRegions().get(regionIndex), x, y, width, height);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f,.74f,1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        switch(state){
            case LOADING:
                if(assets.update()) {
                    //assets loaded
                  //  state = GameState.RUNNING;
                    character.loaded();
                } else {
                    // display loading information
                    float progress = assets.getProgress();
                    gameStage.getBatch().begin();
                    gameStage.getBatch().draw(loadingTexture, gameStage.getWidth()*progress, 150, 100, 100);
                    font.getData().setScale(2);
                    font.draw(gameStage.getBatch(), "Loading", 150, gameStage.getHeight()/3);
                    gameStage.getBatch().end();
                }
                break;
            case RUNNING:
                //add background
                gameStage.getBatch().begin();

                renderAsset(
                        "images/backgrounds/Bus-Background.png",
                        Texture.class,
                        gameStage.getBatch(),
                        0,
                        0,
                        gameStage.getWidth(),
                        gameStage.getHeight()
                );
                
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
