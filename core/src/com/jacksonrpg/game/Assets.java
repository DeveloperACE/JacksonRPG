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
 * This is the class responsible for back-end loading of assets and provides all the file paths for assets required in the game
 */

public class Assets {


    private AssetManager manager = new AssetManager();
    



    //GLOBAL PATHS
    public final String LESSER_JACKSON_WALKING_ATLAS = "core/assets/images/entities/lesserjackson/walking.atlas";
    public final String SPEECHBUBBLE_ATLAS_PATH = "core/assets/images/other/speechbubble/speechbubble.atlas";
    public final String HEALTH_BAR_TEXTURE = "core/assets/images/HUD/healthbar.png";

    
    //Main Menu Paths
    public final String MENU_BACKGROUND_TEXTURE = "core/assets/images/backgrounds/TitleScreen-BusBack.png";
    public final String BANNER_PATH_TEXTURE = "core/assets/images/bannerlogo.png";
    public final String GAME_LOGO = "core/assets/images/game_logo.png";
    public final String GAME_TEXT_BANNER = "core/assets/images/text_banner.png";
    public final String GREATER_JACKSON_SLEEPING_TEXTURE = "core/assets/images/entities/greaterjackson/gj_sleeping.png";
    public final String LESSER_JACKSON_SLEEPING_TEXTURE = "core/assets/images/entities/lesserjackson/lj_sleeping.png";



    //Tutorial World Paths
    public final String TUTORIAL_MAP_PATH = "core/assets/maps/tutorialworld/tutorialworld.tmx";
    public final String FREDDIE_MAC_TEXTURE = "core/assets/images/entities/freddiemac.png";


    //Main World Paths



//    //MISC Paths
    public final String DEFAULT_ENTITY_TEXTURE_PATH = "core/assets/images/entities/defaulttexture.png";


    /**
     *
     * @return The result of manager.update()
     */
    public Boolean update() {return manager.update();}

    /**
     *
     * @return The asset manager instance used to store the attets for the game
     * @see AssetManager
     */
    public AssetManager getManager() {return manager;}

    /**
     *
     * @return the progress of asset loading
     */
    public float getProgress() {return manager.getProgress();}


    /** Queues an asset of type {@link Texture} with the given path if it hasnt already been queued
     *
     * @param path the path of the asset to add to the queue
     */
    public void queueTexture(String path) {
        if (!manager.isLoaded(path)){
            manager.load(path, Texture.class);
        }
    }

    /** Gets the asset of type {@link Texture} loaded at the given path
     *
     * @param path the path of the loaded asset
     * @return the {@link Texture} stored at the given path
     */
    public Texture getTexture(String path) {
            return manager.get(path, Texture.class);
    }


    /** Queues an asset of type {@link TextureAtlas} with the given path if it hasnt already been queued
     *
     * @param path the path of the asset to add to the queue
     */
    public void queueTextureAtlas(String path) {
        if (!manager.isLoaded(path)){
            manager.load(path, TextureAtlas.class);
        }
    }

    /** Gets the asset of type {@link TextureAtlas} loaded at the given path
     *
     * @param path the path of the loaded asset
     * @return the {@link TextureAtlas} stored at the given path
     */
    public TextureAtlas getTextureAtlas(String path) {
        return manager.get(path, TextureAtlas.class);
    }


    /** Queues an asset of type {@link TiledMap} with the given path if it hasnt already been queued
     *
     * @param path the path of the asset to add to the queue
     */
    public void queueMap(String path) {
        if (!manager.isLoaded(path)){
            manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
            manager.load(path, TiledMap.class);
        }
    }

    /** Gets the asset of type {@link TiledMap} loaded at the given path
     *
     * @param path the path of the loaded asset
     * @return the {@link TiledMap} stored at the given path
     */
    public TiledMap getMap(String path) {
        return manager.get(path, TiledMap.class);
    }





    public void dispose() {
        manager.dispose();
    }    

}