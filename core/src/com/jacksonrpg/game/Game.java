package com.jacksonrpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jacksonrpg.JacksonRPG;
import com.jacksonrpg.entities.Player;
import com.jacksonrpg.maps.World;

/**
 * Manages the creation and assembly of everything required to make the game, including maps, players, Entities .etc.
 * Created by Adrian on 3/14/17.
 */
public class Game implements Screen {

    private JacksonRPG jacksonrpg;
    private World world;
    private Player player;
    private Stage stage;

    public Game(JacksonRPG jrpginstance, World.Level level) {
        this.jacksonrpg = jrpginstance;
        player = new Player(jacksonrpg, Player.PlayerName.LESSERJACKSON);
        world = new World(jacksonrpg, level);
        stage = new Stage(new ScreenViewport(world.camera));

        player.setX(100);
        player.setY(100);
        player.setWidth(200);
        player.setHeight(200);

        stage.addActor(player);

    }

    public Game(JacksonRPG jrpginstance) {
        this(jrpginstance, World.Level.TUTORIAL);
    }

    public Player getPlayer() {return player;}
    public Stage getStage() {return stage;}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.render(delta);
        stage.act();
        stage.draw();

//        Vector3 touchPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
//        world.camera.unproject(touchPoint);
//        System.out.println(touchPoint);

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
