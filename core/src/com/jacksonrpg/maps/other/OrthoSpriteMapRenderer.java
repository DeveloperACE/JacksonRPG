package com.jacksonrpg.maps.other;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


/**
 * Created by Adrian on 3/24/17.
 */
public class OrthoSpriteMapRenderer extends OrthogonalTiledMapRenderer {

    public OrthoSpriteMapRenderer(TiledMap map, float unitScale) {
        super(map, unitScale);
    }

    @Override
    public void renderObject(MapObject object) {
        if(object instanceof TextureMapObject) {
            TextureMapObject textureObj = (TextureMapObject) object;
            batch.draw(textureObj.getTextureRegion(), textureObj.getX(), textureObj.getY());

        }
    }
}
