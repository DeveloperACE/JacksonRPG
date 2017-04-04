package com.jacksonrpg.maps.other;
//
//import com.badlogic.gdx.graphics.g2d.Batch;
//import com.badlogic.gdx.maps.MapLayer;
//import com.badlogic.gdx.maps.MapObject;
//import com.badlogic.gdx.maps.objects.TextureMapObject;
//import com.badlogic.gdx.maps.tiled.TiledMap;
//import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
//
//
///**
// * Created by Adrian on 3/24/17.
// */
//public class OrthoSpriteMapRenderer extends OrthogonalTiledMapRenderer {
//
//
//    public OrthoSpriteMapRenderer(TiledMap map) {
//        super(map);
//    }
//    public OrthoSpriteMapRenderer(TiledMap map, Batch batch) {
//        super(map, batch);
//    }
//    public OrthoSpriteMapRenderer(TiledMap map, float unitScale) {
//        super(map, unitScale);
//    }
//    public OrthoSpriteMapRenderer(TiledMap map, float unitScale, Batch batch) {
//        super(map, unitScale, batch);
//    }
//
//    @Override
//    public void renderObject(MapObject object) {
//        if(object instanceof TextureMapObject) {
//            TextureMapObject textureObj = (TextureMapObject) object;
//            batch.draw(
//                    textureObj.getTextureRegion(),
//                    textureObj.getX(),
//                    textureObj.getY()
//            );
//
//        }
//    }
//
//    @Override
//    public void renderObjects(MapLayer layer) {
//        for (MapObject object : layer.getObjects()) {
//            renderObject(object);
//        }
//    }
//}


        import com.badlogic.gdx.graphics.g2d.Sprite;
        import com.badlogic.gdx.maps.MapLayer;
        import com.badlogic.gdx.maps.MapObject;
        import com.badlogic.gdx.maps.tiled.TiledMap;
        import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
        import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

        import java.util.ArrayList;
        import java.util.List;

public class OrthoSpriteMapRenderer extends OrthogonalTiledMapRenderer {
    private Sprite sprite;
    private List<Sprite> sprites;
    private int drawSpritesAfterLayer = 1;


//    public OrthoSpriteMapRenderer(TiledMap map, Batch batch) {
//        super(map, batch);
//    }
    public OrthoSpriteMapRenderer(TiledMap map, float unitScale) {
        super(map, unitScale);
        sprites = new ArrayList<Sprite>();
    }
//    public OrthoSpriteMapRenderer(TiledMap map, float unitScale, Batch batch) {
//        super(map, unitScale, batch);
//    }
//
    public OrthoSpriteMapRenderer(TiledMap map) {
        super(map);
        sprites = new ArrayList<Sprite>();
    }

    public void addSprite(Sprite sprite){
        sprites.add(sprite);
    }

    @Override
    public void render() {
        beginRender();
        int currentLayer = 0;
        for (MapLayer layer : map.getLayers()) {
            if (layer.isVisible()) {
                if (layer instanceof TiledMapTileLayer) {
                    renderTileLayer((TiledMapTileLayer)layer);
                    currentLayer++;
                    if(currentLayer == drawSpritesAfterLayer){
                        for(Sprite sprite : sprites)
                            sprite.draw(this.getBatch());
                    }
                } else {
                    for (MapObject object : layer.getObjects()) {
                        renderObject(object);
                    }
                }
            }
        }
        endRender();
    }
}