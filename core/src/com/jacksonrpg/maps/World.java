package com.jacksonrpg.maps;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jacksonrpg.JacksonRPG;
import com.jacksonrpg.entities.Entity;
import com.jacksonrpg.entities.Player;

import java.util.ArrayList;

/** the world is what manages all the subobjects fo the World/level.
 * Created by edwar12421 on 5/8/2017.
 */
public class World implements Screen {

    public enum Level {
        TUTORIAL, MAIN
    }

    private JacksonRPG jacksonrpg;
    private Level level;

    private Map map;
    private Player player;
    private Stage stage;

   private Entity freddie;

    /** Creates the World
     *
     * @param jacksonrpg the jacksonRPG instance from which to ue for asset loading .etc
     * @param level the name of the level to render. this determines the assets to load
     */
    public World(JacksonRPG jacksonrpg, Level level) {
        this.jacksonrpg = jacksonrpg;
        this.level = level;

        freddie = new Entity(
                jacksonrpg,
                null,
                600,
                100,
                100,
                100,
                0,
                0,
                0,
                0,
                0,
                false,
                false
        );
        map = new Map(this.jacksonrpg, level);
        freddie.setInteractable(true);
        setupPlayer();

    }

    /** Creates the World
     * Level Defaults to TUORIAL
     *
     * @param jacksonrpg the jacksonRPG instance from which to ue for asset loading .etc
     */
    public World(JacksonRPG jacksonrpg) {
        this(jacksonrpg, Level.TUTORIAL);
    }

    public void queueAssets() {
        map.queueAssets();
        player.queueAssets();

        switch (level) {
            case TUTORIAL:
                jacksonrpg.getAssets().queueTexture(jacksonrpg.getAssets().FREDDIE_MAC_TEXTURE);
                break;
            case MAIN:
                break;
        }
    }

    public void assetsLoaded() {
        player.assetsLoaded();
        map.assetsLoaded();

        // stage.assetsLoaded();
        stage = new Stage(new ScreenViewport(map.getCamera()));
        stage.addActor(player);

        switch (level) {
            case TUTORIAL:



                //jacksonrpg.getAssets().getTexture(jacksonrpg.getAssets().FREDDIE_MAC_TEXTURE),
               // freddie.setInteractable(true);
                //entities.add(freddie);


               // Entity freddie = entities.get(0);
                freddie.changeTexture(jacksonrpg.getAssets().getTexture(jacksonrpg.getAssets().FREDDIE_MAC_TEXTURE));

                stage.addActor(freddie);
                player.setMovementBorders(player.getLeftBoundary(), 600);

                break;
            case MAIN:
                break;
        }
    }


    private void setupPlayer() {
        player = new Player(this.jacksonrpg, Player.PlayerName.LESSERJACKSON);

        if (player.checkSpawnPoint(100)){
            player.setX(100);
            player.setY(100);
        }//TODO: Error handling

        player.setWidth(50);
        player.setHeight(100);
    }

    /** Returns the map for the current Game() instance
     *
     * @return the map for the current Game() instance
     */
    public Map getMap() {return map;}

    /** Returns the player for the current Game() instance
     *
     * @return the player for the current Game() instance
     */
    public Player getPlayer() {return player;}

    /** Returns the stage for the current Game() instance
     *
     * @return the stage for the current Game() instance
     */
    public Stage getStage() {return stage;}

    public void addEntity(Entity entity) {stage.addActor(entity);}



    @Override
    public void render(float delta) {


        map.render(delta);
        stage.act();
        stage.draw();
    }





    @Override
    public void resize(int width, int height) {

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
    public void dispose() {
         stage.dispose();
    }
}
