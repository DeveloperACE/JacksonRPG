package com.jacksonrpg.maps;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.jacksonrpg.JacksonRPG;
import com.jacksonrpg.entities.Player;
import com.jacksonrpg.maps.other.OrthoSpriteMapRenderer;

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
        mapRenderer = new OrthoSpriteMapRenderer(map, unitScale);
        backgroundLayer = (TiledMapTileLayer)map.getLayers().get(0);
        objectLayer = map.getLayers().get("Sprite Layer");


        camera = new OrthographicCamera();
        camera.setToOrtho(false, backgroundLayer.getHeight(),backgroundLayer.getHeight());
        camera.update();

    }

    /** adds a player to the world
     *
     * @param player The player to add to the world
     */
    public void addCharacter(Player player, int width, int height) {
        TextureMapObject tmo = new TextureMapObject(new TextureRegion(
                player.getTexture(),
                width,
                height
        ));

//        tmo.setX(player.getX());
//        tmo.setY(player.getY());
        tmo.setX(100);
        tmo.setY(100);
        objectLayer.getObjects().add(tmo);

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
