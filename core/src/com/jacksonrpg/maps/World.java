package com.jacksonrpg.maps;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    public OrthographicCamera camera;

    float unitScale = 1.05f;
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


        camera = new OrthographicCamera(400, 400);
       // camera.setToOrtho(false, backgroundLayer.getHeight(),backgroundLayer.getHeight());
       // camera.position.set();
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
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
        tmo.setX(width);
        tmo.setY(height);
       // objectLayer.getObjects().add(tmo);
        Sprite sprite = new Sprite(jacksonrpg.game.player.getTexture());
        mapRenderer.addSprite


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

        mapRenderer.getBatch().begin();
        mapRenderer.renderObjects(objectLayer);
        //mapRenderer.renderObject(jacksonrpg.game.player);
        mapRenderer.getBatch().end();
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
