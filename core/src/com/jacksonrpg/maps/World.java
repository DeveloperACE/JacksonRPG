package com.jacksonrpg.maps;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jacksonrpg.JacksonRPG;
import com.jacksonrpg.entities.Entity;
import com.jacksonrpg.entities.Player;

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

    /** Creates the World
     *
     * @param jacksonrpg the jacksonRPG instance from which to ue for asset loading .etc
     * @param level the name of the level to render. this determines the assets to load
     */
    public World(JacksonRPG jacksonrpg, Level level) {
        this.jacksonrpg = jacksonrpg;
        this.level = level;

        map = new Map(this.jacksonrpg);

        setupPlayer();

    }

    public void queueAssets() {
        map.queueAssets();
        player.queueAssets();
    }

    public void assetsLoaded() {
        player.assetsLoaded();
        map.assetsLoaded();

        // stage.assetsLoaded();
        stage = new Stage(new ScreenViewport(map.getCamera()));
        stage.addActor(player);
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
