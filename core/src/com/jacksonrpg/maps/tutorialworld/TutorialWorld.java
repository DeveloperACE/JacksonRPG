package com.jacksonrpg.maps.tutorialworld;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.jacksonrpg.JacksonRPG;
import com.jacksonrpg.entities.Entity;


/**
 * Created by edwar12421 on 3/21/2017.
 */
public class TutorialWorld /*extends World*/ implements Screen {

    private JacksonRPG jacksonrpg;
    private TiledMap map;
    private OrthographicCamera camera;

    private float unitScale = 1.05f;
    private OrthogonalTiledMapRenderer mapRenderer;

    private Integer mapMovementTriggerBuffer = 50;//if user gets this close to the edge of SCREEN, scroll the map.
    //used for getting map width in px. https://gist.github.com/spilth/5457184
    private Integer mapTileSize;
    private TiledMapTileLayer mainLayer;//used only for getmapWidth() and getMapHeight()




    /** Creates the TutorialWorld
     *
     * @param jacksonrpg the jacksonRPG instance from which to ue for asset loading .etc
     */
    public TutorialWorld(JacksonRPG jacksonrpg) {
        this.jacksonrpg = jacksonrpg;

        setupCamera();
    }

    /** Queues the assets needed to construct the necessary variables for this screen
     *
     */
    public void queueAssets() {
       // freddiemac.queueAssets();
       // jacksonrpg.getAssets().queueTexture(jacksonrpg.getAssets().FREDDIE_MAC_TEXTURE);

        //get world map
        jacksonrpg.getAssets().queueMap(jacksonrpg.getAssets().TUTORIAL_MAP_PATH);

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
        map = jacksonrpg.getAssets().getMap(jacksonrpg.getAssets().TUTORIAL_MAP_PATH);
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

            Vector3 playerScreenCoords = camera.unproject(new Vector3(jacksonrpg.getGame().getPlayer().getX(), 0, 0));
            // let the camera follow the player, x-axis only
            //TODO: Fix player movement for midsections of map
            ///*playerScreenCoords.x < m ||*/ playerScreenCoords.x > Gdx.graphics.getWidth()-mapMovementTriggerBuffer
            if (jacksonrpg.getGame().getPlayer().getX() > 200 && jacksonrpg.getGame().getPlayer().getX() < getMapWidthInPixels() - 200) {

                camera.position.x = jacksonrpg.getGame().getPlayer().getX();
            }

            if (jacksonrpg.getGame().getPlayer().getX() < getMapWidthInPixels() - 200 && playerScreenCoords.x > getMapWidthInPixels() - mapMovementTriggerBuffer ) {
                camera.position.x = jacksonrpg.getGame().getPlayer().getX();

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
