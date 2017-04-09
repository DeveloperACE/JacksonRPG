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


    //JacksonRPG jacksonrpg;

    private AssetManager manager = new AssetManager();
    



    //GLOBAL PATHS
    public final String LESSER_JACKSON_WALKING_ATLAS = "core/assets/images/entities/lesserjackson/walking.atlas";
    public final String SPEECHBUBBLE_ATLAS_PATH = "core/assets/images/other/speechbubble/speechbubble.atlas";

    
    //Main Menu Paths
    public final String MENU_BACKGROUND_TEXTURE = "core/assets/images/backgrounds/TitleScreen-BusBack.png";
    public final String BANNER_PATH_TEXTURE = "core/assets/images/bannerlogo.png";
    public final String GAME_LOGO = "core/assets/images/game_logo.png";
    public final String GAME_TEXT_BANNER = "core/assets/images/text_banner.png";
    public final String GREATER_JACKSON_SLEEPING_TEXTURE = "core/assets/images/entities/greaterjackson/gj_sleeping.png";
    public final String LESSER_JACKSON_SLEEPING_TEXTURE = "core/assets/images/entities/lesserjackson/lj_sleeping.png";



    //Tutorial World Paths
    public final String TUTORIAL_MAP_PATH = "core/assets/maps/tutorialworld/tutorialworld.tmx";


    //Main World Paths



//    //MISC Paths
    public final String DEFAULT_ENTITY_TEXTURE_PATH = "core/assets/images/entities/defaulttexture.png";


    public void queueGlobalAssets() {

        manager.load(LESSER_JACKSON_WALKING, TextureAtlas.class);
        manager.load(SPEECHBUBBLE_ATLAS_PATH, TextureAtlas.class);

    }

    public void queueMenuAssets() {
        manager.load(MENU_BACKGROUND_PATH, Texture.class);
        manager.load(BANNER_PATH, Texture.class);
        manager.load(GJ_SLEEP_PATH, Texture.class);
        manager.load(LJ_SLEEP_PATH, Texture.class);
    }
    
    public void queueTutorialAssets() {
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        manager.load(TUTORIAL_MAP_PATH, TiledMap.class);

//        manager.load("myothergraphics.pack", TextureAtlas.class);
    }

    public void queueMainGameAssets() {
//        manager.load("mygraphics.pack", TextureAtlas.class);
//        manager.load("myothergraphics.pack", TextureAtlas.class);
    }


    public void globalAssetsDone() {

        lesserjacksonWalking = manager.get(LESSER_JACKSON_WALKING, TextureAtlas.class);
    }

    public void menuAssetsDone() {
        menuBackground = manager.get(MENU_BACKGROUND_PATH, Texture.class);
        gameBanner = manager.get(BANNER_PATH, Texture.class);
        greaterJacksonSleepingTexture = manager.get(GJ_SLEEP_PATH, Texture.class);
        lesserjacksonSleepingTexture = manager.get(LJ_SLEEP_PATH, Texture.class);
    }

    public void tutorialAssetsDone() {
        tutorialMap = manager.get(TUTORIAL_MAP_PATH, TiledMap.class);
        speechBubbleAtlas = manager.get(SPEECHBUBBLE_ATLAS_PATH, TextureAtlas.class);
        //myOtherGraphics = manager.get("myothergraphics.pack", TextureAtlas.class);
    }

    public void mainGameAssetsDone() {
        tutorialMap = manager.get(TUTORIAL_MAP_PATH, TiledMap.class);
        //        myOtherGraphics = manager.get("myothergraphics.pack", TextureAtlas.class);
    }



    public void dispose() {
        manager.dispose();
    }    

}