package com.jacksonrpg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.jacksonrpg.game.Game;
import com.jacksonrpg.game.MainMenu;

public class JacksonRPG extends ApplicationAdapter {

    public enum GameState {
        MENU, RUNNING, PAUSED, LOADING, INACTIVE
    }

	SpriteBatch batch;
	MainMenu gameMenu;
	public Game game = new Game();
	public GameState state = GameState.LOADING;
	public AssetManager assets = new AssetManager();

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameMenu = new MainMenu(this);

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
            case LOADING:
                if(assets.update()) {
                    //assets loaded
                    state = GameState.MENU;
                    gameMenu.assetsLoaded();
                }// else {
//                    // display loading information
//                    float progress = assets.getProgress();
//                    gameStage.getBatch().begin();
//                    gameStage.getBatch().draw(loadingTexture, gameStage.getWidth()*progress, 150, 100, 100);
//                    font.getData().setScale(2);
//                    font.draw(gameStage.getBatch(), "Loading", 150, gameStage.getHeight()/3);
//                    gameStage.getBatch().end();
//                }
//                break;
            case RUNNING:
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
            case PAUSED:
                //show in-game menu
               // jacksonrpg.setScreen(new MainGameMenu(jacksonrpg));
                //dispose();
                break;
            case INACTIVE:
                //show overlay w/ pause icon
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
