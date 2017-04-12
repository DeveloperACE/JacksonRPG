package com.jacksonrpg.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.jacksonrpg.JacksonRPG;

/**
 * Created by edwar12421 on 3/21/2017.
 */
public class TutorialWorld /*extends World*/ implements Screen {

    private JacksonRPG jacksonrpg;
    private TiledMap map;
    private  OrthographicCamera camera;

    private float unitScale = 1.05f;
    private OrthogonalTiledMapRenderer mapRenderer;

    private Integer mapMovementTriggerBuffer = 50;//if user gets this close to the edge of SCREEN, scroll the map.
    //used for getting map width in px. https://gist.github.com/spilth/5457184
    private Integer mapTileSize;
    private TiledMapTileLayer mainLayer;


    public TutorialWorld(JacksonRPG jacksonrpg) {
        this.jacksonrpg = jacksonrpg;
    }


    public void queueAssets() {
        jacksonrpg.getAssets().queueTexture(jacksonrpg.getAssets().LESSER_JACKSON_SLEEPING_TEXTURE);
        jacksonrpg.getAssets().queueMap(jacksonrpg.getAssets().TUTORIAL_MAP_PATH);

    }

    public void assetsLoaded() {
        map = jacksonrpg.getAssets().getMap(jacksonrpg.getAssets().TUTORIAL_MAP_PATH);
        mainLayer = (TiledMapTileLayer) map.getLayers().get(0);
        mapTileSize = (int) mainLayer.getTileWidth();
        mapRenderer = new OrthogonalTiledMapRenderer(map, unitScale);

        camera = new OrthographicCamera(400, 400);
        // camera.setToOrtho(false, backgroundLayer.getHeight(),backgroundLayer.getHeight());
        // camera.position.set();
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

    }

    public Camera getCamera() {return camera;}
    public Integer getMapWidthInPixels() {return mainLayer.getWidth() * mapTileSize; }
    public Integer getMapHeightInPixels() {return mainLayer.getHeight() * mapTileSize; }


    @Override
    public void render(float delta) {

        if (camera != null) {

            Vector3 playerScreenCoords = camera.unproject(new Vector3(jacksonrpg.getGame().getPlayer().getX(), 0, 0));
            // let the camera follow the player, x-axis only
            //TODO: Fix player movement for midsections of map
            ///*playerScreenCoords.x < m ||*/ playerScreenCoords.x > Gdx.graphics.getWidth()-mapMovementTriggerBuffer
            if (jacksonrpg.getGame().getPlayer().getX() > 200 && playerScreenCoords.x < mapMovementTriggerBuffer) {

                camera.position.x = jacksonrpg.getGame().getPlayer().getX();
            }

            if (jacksonrpg.getGame().getPlayer().getX() < getMapWidthInPixels() - 200 && playerScreenCoords.x > getMapWidthInPixels() - mapMovementTriggerBuffer ) {
                camera.position.x = jacksonrpg.getGame().getPlayer().getX();

            }
            System.out.println(jacksonrpg.getGame().getPlayer().getX());
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
