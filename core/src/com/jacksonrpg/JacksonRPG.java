package com.jacksonrpg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jacksonrpg.game.Game;
import com.jacksonrpg.game.MainMenu;

/** Manages States, stages and screens for the game and provides save/load functionality
 */
public class JacksonRPG extends ApplicationAdapter {
    
    public enum GameScreen {
        MENU, GAME
    }

	private SpriteBatch batch;
    public GameScreen state = GameScreen.MENU;
    public AssetManager assets = new AssetManager();

	private MainMenu gameMenu;
	public Game game;

	private static final String LOADING_GRAPHIC = "core/assets/images/characters/lesserjackson/walking.atlas";
    private float elapsedAnimationTime;

	@Override
	public void create () {
		batch = new SpriteBatch();


		if (!assets.isLoaded(LOADING_GRAPHIC, TextureAtlas.class)) {
		    assets.load(LOADING_GRAPHIC, TextureAtlas.class);
		    assets.finishLoadingAsset(LOADING_GRAPHIC);
        }

        makeMenu();
	}

	public void makeMenu() {
        gameMenu = new MainMenu(this);
        //blocks until all assets are loaded, replace with loading screen
        assets.finishLoading();
//        loadUntilDone();
        state = GameScreen.MENU;
    }

    public void makeGame() {
        game = new Game(this);
        //blocks until all assets are loaded
        //TODO: replace with loading screen
        assets.finishLoading();
        state = GameScreen.GAME;
    }


    @Override
	public void render () {
		Gdx.gl.glClearColor(0.5f,.74f,1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);// This cryptic line clears the screen.



        switch(state){
            case MENU:

                batch.begin();
                gameMenu.render(Gdx.graphics.getDeltaTime());
                batch.end();

                break;

            case GAME:
                //add background
               // gameStage.getBatch().begin();

                /*renderAsset(
                        "images/backgrounds/Bus-Background.png",
                        Texture.class,
                        gameStage.getBatch(),
                        0,
                        0,
                        gameStage.getWidth(),
                        gameStage.getHeight()
                );*/
               // font.draw(gameStage.getBatch(), "Done Loading", 100, gameStage.getHeight()/3);
                //gameStage.getBatch().end();

                //update stage and actors
                //gameStage.draw();
                //gameStage.act(delta);

                break;
        }


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		gameMenu.dispose();
		assets.dispose();

	}
}
