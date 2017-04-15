//big text made with: http://www.network-science.de/ascii/ using font "banner3"
package com.jacksonrpg.game;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.jacksonrpg.JacksonRPG;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * This is the class responsible for back-end loading of assets
 */

public class Assets {


    private AssetManager manager = new AssetManager();
    



    //GLOBAL PATHS
    public final String LESSER_JACKSON_WALKING_ATLAS = "images/entities/lesserjackson/walking.atlas";
    public final String SPEECHBUBBLE_ATLAS_PATH = "images/other/speechbubble/speechbubble.atlas";

    
    //Main Menu Paths
    public final String MENU_BACKGROUND_TEXTURE = "images/backgrounds/TitleScreen-BusBack.png";
    public final String BANNER_PATH_TEXTURE = "images/bannerlogo.png";
    public final String GAME_LOGO = "images/game_logo.png";
    public final String GAME_TEXT_BANNER = "images/text_banner.png";
    public final String GREATER_JACKSON_SLEEPING_TEXTURE = "images/entities/greaterjackson/gj_sleeping.png";
    public final String LESSER_JACKSON_SLEEPING_TEXTURE = "images/entities/lesserjackson/lj_sleeping.png";



    //Tutorial World Paths
    public final String TUTORIAL_MAP_PATH = "maps/tutorialworld/tutorialworld.tmx";


    //Main World Paths



//    //MISC Paths
    public final String DEFAULT_ENTITY_TEXTURE_PATH = "images/entities/defaulttexture.png";




    public Boolean update() {return manager.update();}
    public AssetManager getManager() {return manager;}
    public float getProgress() {return manager.getProgress();}





    public void queueTexture(String path) {
        if (!manager.isLoaded(path)){
            manager.load(path, Texture.class);
        }
    }
    public Texture getTexture(String path) {
            return manager.get(path, Texture.class);
    }



    public void queueTextureAtlas(String path) {
        if (!manager.isLoaded(path)){
            manager.load(path, TextureAtlas.class);
        }
    }
    public TextureAtlas getTextureAtlas(String path) {
        return manager.get(path, TextureAtlas.class);
    }



    public void queueMap(String path) {
        if (!manager.isLoaded(path)){
            manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
            manager.load(path, TiledMap.class);
        }
    }
    public TiledMap getMap(String path) {
        return manager.get(path, TiledMap.class);
    }





    public void dispose() {
        manager.dispose();
    }    

}