package com.jacksonrpg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.*;
import com.jacksonrpg.game.screens.Game;
import com.jacksonrpg.game.screens.LoadingScreen;
import com.jacksonrpg.game.screens.MainMenu;
import com.jacksonrpg.game.Assets;

/** Manages screens for the game
 */
public class JacksonRPG extends ApplicationAdapter {

    public enum AppScreen {
        MAIN_MENU, GAME, LOADING
    }

    private SpriteBatch batch;
    private AppScreen currentScreen = AppScreen.MAIN_MENU;


    private Assets assets = new Assets();
    private BitmapFont font;

    //game screens
    private MainMenu gameMenu;
    private Game game;
    private LoadingScreen loadScreen;

	private Float elapsedTime = 0f;

	@Override
	public void create () {
		batch = new SpriteBatch();
        font = new BitmapFont();


        //temp bypass main menu for game testing
        makeMenu();
        //makeGame();
    }

    public void makeMenu() {
        gameMenu = new MainMenu(this);
    }

    public void makeGame() {
        game = new Game(this);
    }

    public void makeLoadingScreen() {
        makeLoadingScreen(false);
    }

    public void makeLoadingScreen(Boolean showGameOnCompletion) {
        if (showGameOnCompletion) {
            loadScreen = new LoadingScreen(this, AppScreen.GAME);
        } else {
            loadScreen = new LoadingScreen(this);//2nd param defaults to main menu
        }

        setScreenState(AppScreen.LOADING);
    }

    public void disposeLoadingScreen() {
        loadScreen = null;
    }

    /** Checks if there are any assets to load and pops up a loading screen if there are
     *
     */
    public void checkLoad() {checkLoad(false);}

    public void checkLoad(Boolean showGameOnCompletion) {
        if (getAssets().getManager().getQueuedAssets() > 0) {
            makeLoadingScreen(showGameOnCompletion);
        }
    }




    public MainMenu getMenu() {return gameMenu;}
    public SpriteBatch getBatch() {return batch;}
    public BitmapFont getFont() {return font;}
    public Float getElapsedTime() {return elapsedTime;}
    public void updateElapsedTime() {elapsedTime += Gdx.graphics.getDeltaTime();}
    public Game getGame() {return game;}
    public Assets getAssets() {return assets;}
    //public JacksonRPG getJacksonRPGInstance() {return this;}
    public AppScreen getScreenState() {return currentScreen;}
    public void setScreenState(AppScreen screen) {this.currentScreen = screen;}

    @Override
	public void render () {
        Gdx.gl.glClearColor(1,0,0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);// This cryptic line clears the screen.



        switch(currentScreen){
            case MAIN_MENU:

                batch.begin();
                gameMenu.render(Gdx.graphics.getDeltaTime());
                batch.end();

                break;

            case GAME:
                //add background
                batch.begin();
                game.render(Gdx.graphics.getDeltaTime());
                batch.end();

                //update stage and actors
                //gameStage.draw();
                //gameStage.act(delta);

                break;
            case LOADING:
                batch.begin();
                loadScreen.render(Gdx.graphics.getDeltaTime());
                batch.end();
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
