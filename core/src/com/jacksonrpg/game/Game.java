package com.jacksonrpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.jacksonrpg.JacksonRPG;
import com.jacksonrpg.entities.Player;
import com.jacksonrpg.maps.World;

/**
 * Created by Adrian on 3/14/17.
 */
public class Game implements Screen {

    private JacksonRPG jacksonrpg;
    private World world;
    public Player player;

    public Game(JacksonRPG jrpginstance, World.Level level) {
        this.jacksonrpg = jrpginstance;
        player = new Player(jacksonrpg, Player.PlayerName.LESSERJACKSON);
        world = new World(jacksonrpg, level);

        player.setX(100);
        player.setY(100);

        world.addCharacter(player, 100, 200);
        System.out.println("Done");

    }

    public Game(JacksonRPG jrpginstance) {
        this(jrpginstance, World.Level.TUTORIAL);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.render(delta);

//        Vector3 touchPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
//        world.camera.unproject(touchPoint);
//        System.out.println(touchPoint);

    }

    @Override
    public void dispose() {

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
