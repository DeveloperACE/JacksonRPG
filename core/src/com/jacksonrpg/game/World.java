package com.jacksonrpg.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.jacksonrpg.JacksonRPG;

/**
 * Created by edwar12421 on 3/21/2017.
 */
public class World implements Screen {

    JacksonRPG jacksonrpg;
    TiledMap map;
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

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 10,10);
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
