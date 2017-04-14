package com.jacksonrpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jacksonrpg.JacksonRPG;
import com.jacksonrpg.entities.Player;
import com.jacksonrpg.maps.TutorialWorld;

/**
 * Manages the creation and assembly of everything required to make the game, including maps, players, Entities .etc.
 * Created by Adrian on 3/14/17.
 */
public class Game implements Screen {

    private JacksonRPG jacksonrpg;
    private TutorialWorld world;
    private Player player;
    private Stage stage;

    public Game(JacksonRPG jacksonrpg) {
        this.jacksonrpg = jacksonrpg;
        player = new Player(this.jacksonrpg, Player.PlayerName.LESSERJACKSON);

        world = new TutorialWorld(this.jacksonrpg);

       if (player.checkSpawnPoint(100)){
            player.setX(100);
            player.setY(100);
        }
        player.setWidth(50);
        player.setHeight(100);

        queueAssets();
        jacksonrpg.makeLoadingScreen(true);
       // this.jacksonrpg.checkLoad(true);



    }

    //pass the queueAssets() call to parts of world
    public void queueAssets() {
        player.queueAssets();
        world.queueAssets();
       // stage.queueAssets();
    }

    //pass the assetsLoaded() call to parts of world
    public void assetsLoaded() {
        player.assetsLoaded();
        world.assetsLoaded();
       // stage.assetsLoaded();
        stage = new Stage(new ScreenViewport(world.getCamera()));
        stage.addActor(player);

        player.worldReady();
    }

    public TutorialWorld getWorld() {return world;}
    public Player getPlayer() {return player;}
    public Stage getStage() {return stage;}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.render(delta);
        stage.act();
        stage.draw();


    }

    @Override
    public void dispose() {
        stage.dispose();
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
