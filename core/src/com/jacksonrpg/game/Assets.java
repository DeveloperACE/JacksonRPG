package com.jacksonrpg.game;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.jacksonrpg.JacksonRPG;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class Assets {
    
    public AssetManager manager = new AssetManager();
    
    //GLOBAL/FREQUENTLY USED ASSETS
    public OrthographicCamera camera;
    public TextureAtlas lesserjacksonWalking;

    //GLOBAL PATHS
    private static final String LESSER_JACKSON_WALKING = "core/assets/images/entities/lesserjackson/walking.atlas";



    //Main Menu assets
    public Texture menuBackground;
    public Texture gameBanner;
    public Texture lesserjacksonSleepingTexture;
    public Texture greaterJacksonSleepingTexture;
    
    //Main Menu Paths
    private static final String MENU_BACKGROUND_PATH = "core/assets/images/backgrounds/TitleScreen-BusBack.png";
    private static final String BANNER_PATH = "core/assets/images/bannerlogo.png";
    private static final String GJ_SLEEP_PATH = "core/assets/images/entities/greaterjackson/gj_sleeping.png";
    private static final String LJ_SLEEP_PATH = "core/assets/images/entities/lesserjackson/lj_sleeping.png";



    //Tutorial World Assets
    public TiledMap tutorialMap;

    //Tutorial World Paths
    private static final String TUTORIAL_MAP_PATH = "core/assets/maps/tutorialworld/tutorialworld.tmx";




    //Main World Assets
    public TiledMap mainMap;
    
    
    //Main World Paths


    public void queueGlobalAssets() {

        manager.load(LESSER_JACKSON_WALKING, TextureAtlas.class);
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