package com.jacksonrpg.maps;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.jacksonrpg.JacksonRPG;
import com.jacksonrpg.entities.Entity;
import com.jacksonrpg.maps.World.Level;


/**
 * Created by edwar12421 on 3/21/2017.
 */
public class Map/*extends World*/ implements Screen {

    private JacksonRPG jacksonrpg;

    private Level level;


    private TiledMap map;
    private OrthographicCamera camera;

    private float unitScale = 1.05f;
    private OrthogonalTiledMapRenderer mapRenderer;

    private Integer mapMovementTriggerBuffer = 50;//if user gets this close to the edge of SCREEN, scroll the map.
    //used for getting map width in px. https://gist.github.com/spilth/5457184
    private Integer mapTileSize;
    private TiledMapTileLayer mainLayer;//used only for getmapWidth() and getMapHeight()




    /** Creates the Map
     *
     * @param jacksonrpg the jacksonRPG instance from which to ue for asset loading .etc
     * @param level the name of the level to render. this determines the assets to load
     */
    public Map(JacksonRPG jacksonrpg, Level level) {
        this.jacksonrpg = jacksonrpg;
        this.level = level;

        setupCamera();
    }

    /** Creates the Map
     * level defaults to TUTORIAL
     *
     * @param jacksonrpg the jacksonRPG instance from which to ue for asset loading .etc
     */
    public Map(JacksonRPG jacksonrpg) {this(jacksonrpg, Level.TUTORIAL);}

    /** Queues the assets needed to construct the necessary variables for this screen
     *
     */
    public void queueAssets() {
       // freddiemac.queueAssets();
       // jacksonrpg.getAssets().queueTexture(jacksonrpg.getAssets().FREDDIE_MAC_TEXTURE);

        //queue world map
        switch (level){
            case TUTORIAL:
                jacksonrpg.getAssets().queueMap(jacksonrpg.getAssets().TUTORIAL_MAP_PATH);
                break;
            case MAIN:
                //jacksonrpg.getAssets().queueMap(jacksonrpg.getAssets().TUTORIAL_MAP_PATH);
                break;
        }

    }

    /** Called when the assets requested in queueAssets() have been loaded successfully
     *
     */
    public void assetsLoaded() {

        setupMap();

        //freddiemac.assetsLoaded();
       // freddiemac.changeTexture(jacksonrpg.getAssets().getTexture(jacksonrpg.getAssets().FREDDIE_MAC_TEXTURE));
    }

//    private Entity makeFreddie() {
//        Entity freddie = new Entity(
//                jacksonrpg,
//                jacksonrpg.getAssets().getTexture(jacksonrpg.getAssets().DEFAULT_ENTITY_TEXTURE_PATH),
//                600,
//                100,
//                100,
//                100
//        );
//        freddie.setInteractable(true);
//
//        return freddie;
//    }

    private void setupMap() {
        switch (level){
            case TUTORIAL:
                map = jacksonrpg.getAssets().getMap(jacksonrpg.getAssets().TUTORIAL_MAP_PATH);
                break;
            case MAIN:
                //map = jacksonrpg.getAssets().getMap(jacksonrpg.getAssets().TUTORIAL_MAP_PATH);
                break;
        }

        mainLayer = (TiledMapTileLayer) map.getLayers().get(0);
        mapTileSize = (int) mainLayer.getTileWidth();
        mapRenderer = new OrthogonalTiledMapRenderer(map, unitScale);
    }

    private void setupCamera() {
        camera = new OrthographicCamera(400, 400);
        // camera.setToOrtho(false, backgroundLayer.getHeight(),backgroundLayer.getHeight());
        // camera.position.set();
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
    }

  //  public Entity getFreddie() {return freddiemac;}
    /** Returns the {@link OrthographicCamera} used in this world
     *
     * @return An {@link OrthographicCamera} object
     */
    public Camera getCamera() {return camera;}

    /** calculates the width of the current world's map using the methods provided by the {@link TiledMap} class
     *
     * @return the number of pixels the map takes up along its X axis as an integer
     */
    public int getMapWidthInPixels() {return mainLayer.getWidth() * mapTileSize; }

    /** calculates the height of the current world's map using the methods provided by the {@link TiledMap} class
     *
     * @return the number of pixels the map takes up along its Y axis as an integer
     */
    public int getMapHeightInPixels() {return mainLayer.getHeight() * mapTileSize; }


    @Override
    public void render(float delta) {

        if (camera != null) {

            Vector3 playerScreenCoords = camera.unproject(new Vector3(jacksonrpg.getGame().getWorld().getPlayer().getX(), 0, 0));
            // let the camera follow the player, x-axis only
            //TODO: Fix player movement for midsections of map
            ///*playerScreenCoords.x < m ||*/ playerScreenCoords.x > Gdx.graphics.getWidth()-mapMovementTriggerBuffer
            if (jacksonrpg.getGame().getWorld().getPlayer().getX() > 200 && jacksonrpg.getGame().getWorld().getPlayer().getX() < getMapWidthInPixels() - 200) {

                camera.position.x = jacksonrpg.getGame().getWorld().getPlayer().getX();
            }

            if (jacksonrpg.getGame().getWorld().getPlayer().getX() < getMapWidthInPixels() - 200 && playerScreenCoords.x > getMapWidthInPixels() - mapMovementTriggerBuffer ) {
                camera.position.x = jacksonrpg.getGame().getWorld().getPlayer().getX();

            }
            camera.update();

            if (mapRenderer != null) {
                // set the TiledMapRenderer view based on what the
                // camera sees, and render the map
                mapRenderer.setView(camera);
                mapRenderer.render();
            }
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void resize(int width, int height) {

    }
}
