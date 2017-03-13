package com.jacksonrpg.world;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Created by Adrian on 3/12/17.
 */
public class World {

    public static void World() {


    }

    public void loadTiledMap(AssetManager manager, String mapPath, Class fileType) {
        //set custom loader
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

        manager.load(mapPath, TiledMap.class);
    }

    public TiledMap getMap(AssetManager manager, String mapPath) {
        if(manager.isLoaded(mapPath)) {
            return manager.get(mapPath, TiledMap.class);
        } else {
            //TODO: error handling here
            System.out.println("OOPS: Tried to get map when it hasnt loaded yet");
            return null;
        }
    }
}
