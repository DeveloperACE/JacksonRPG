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
import com.jacksonrpg.game.Assets;

/** Manages States, stages and screens for the game and provides save/load functionality
 */
public class JacksonRPG extends ApplicationAdapter {
    
    public enum GameScreen {
        MENU, GAME
    }

	private SpriteBatch batch;
    public GameScreen state = GameScreen.MENU;
    //TODO: Move asset manager to its own class
    public Assets assets = new Assets();

	private MainMenu gameMenu;
	public Game game;

    private float elapsedAnimationTime;


	@Override
	public void create () {
		batch = new SpriteBatch();


        assets.queueGlobalAssets();
        assets.manager.finishLoading();
        assets.globalAssetsDone();

        makeMenu();
	}

	public void makeMenu() {
        gameMenu = new MainMenu(this);
        //blocks until all assets are loaded, replace with loading screen

//        loadUntilDone();
        state = GameScreen.MENU;
    }

    public void makeGame() {
        game = new Game(this);
        //blocks until all assets are loaded

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
