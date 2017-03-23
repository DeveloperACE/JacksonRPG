package com.jacksonrpg.entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by edwar12421 on 3/23/2017.
 */
public class Player extends Entity {



    public Player(TextureAtlas atlas) {
        super(atlas.getRegions().get(0).getTexture());
       // playerEntity = new Entity(atlas.getRegions().get(0).getTexture());
       // playerEntity.addTextureAnimation(atlas, 9);

    }


}
