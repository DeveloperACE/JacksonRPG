package com.jacksonrpg.maps;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.jacksonrpg.JacksonRPG;

/**
 * Created by edwar12421 on 3/21/2017.
 */
public class World implements Screen {

    JacksonRPG jacksonrpg;
    TiledMap map;
    TiledMapTileLayer backgroundLayer;
    MapLayer objectLayer;
    OrthographicCamera camera;

    float unitScale = 1 / 32f;
    OrthogonalTiledMapRenderer mapRenderer;

    public enum Level {
        TUTORIAL, MAIN
    }



    public World(JacksonRPG jrpginstance, Level level) {
        this.jacksonrpg = jrpginstance;

        switch (level){
            case TUTORIAL:
                jacksonrpg.assets.queueTutorialAssets();
                //TODO: replace with loading screen
                jacksonrpg.assets.manager.finishLoading();
                jacksonrpg.assets.tutorialAssetsDone();

                map = jacksonrpg.assets.tutorialMap;
                break;
            case MAIN:
                jacksonrpg.assets.queueMainGameAssets();
                //TODO: replace with loading screen
                jacksonrpg.assets.manager.finishLoading();
                jacksonrpg.assets.mainGameAssetsDone();
                break;
        }
        mapRenderer = new OrthogonalTiledMapRenderer(map, unitScale);
        backgroundLayer = (TiledMapTileLayer)map.getLayers().get(0);
        objectLayer = map.getLayers().get("Object Layer");



        objectLayer.getObjects().add(jacksonrpg.game.character);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, backgroundLayer.getHeight(),backgroundLayer.getHeight());
        camera.update();

    }

    @Override
    public void render(float delta) {

// let the camera follow the koala, x-axis only
      //  camera.position.x = koala.position.x;
        camera.update();

        // set the TiledMapRenderer view based on what the
        // camera sees, and render the map
        mapRenderer.setView(camera);
        mapRenderer.render();
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
