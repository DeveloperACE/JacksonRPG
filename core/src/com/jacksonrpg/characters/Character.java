package com.jacksonrpg.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jacksonrpg.JacksonRPG;

/**
 * Created by Adrian on 3/14/17.
 */
public class Character extends Actor {

    JacksonRPG jacksonrpg;
    Texture characterTexture;

    Integer sectionX;
    Integer sectionY;
    Integer sectionWidth;
    Integer sectionHeight;

    Boolean flipX;
    Boolean flipY;

    public Character(Texture texture, float x, float y, float width, float height, float rotation, int sectionX, int sectionY, int sectionWidth, int sectionHeight, boolean flipX, boolean flipY) {

        characterTexture = texture;

        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.rotateBy(rotation);
        this.sectionX = sectionX;
        this.sectionY = sectionY;
        this.sectionWidth = sectionWidth;
        this.sectionHeight = sectionHeight;
        this.flipX = flipX;
        this.flipY = flipY;

    }

    public Character(Texture texture, float x, float y, float width, float height, float rotation, boolean flipX, boolean flipY) {
        this(texture, x, y, width, height, rotation, 0, 0, texture.getWidth(), texture.getHeight(), flipX, flipY);
    }

    public Character(Texture texture, float x, float y, float width, float height, float rotation, int sectionX, int sectionY, int sectionWidth, int sectionHeight) {
        this(texture, x, y, width, height, rotation, sectionX, sectionY, sectionWidth, sectionHeight, false, false);
    }

    public Character(Texture texture, float x, float y, float width, float height, float rotation) {
        this(texture, x, y, width, height, rotation, 0, 0, texture.getWidth(), texture.getHeight());
    }


    public Character(Texture texture, float x, float y, float width, float height) {
        this(texture, x, y, width, height, 0);
    }

    public Character(Texture texture, float x, float y) {
        this(texture, x, y, 50, 100);
    }

    public Character(Texture texture) {
        this(texture, 0, 0);
    }

    public Actor getActor() {return this;}


    @Override
    public void draw(Batch batch, float parentAlpha) {
//        batch.draw(characterTexture, this.getX(), this.getY(), this.getWidth(), this.getHeight());
//        batch.draw(characterTexture, , );
        batch.draw(
                characterTexture,
                this.getX(),
                this.getY(),
                this.getOriginX(),
                this.getOriginY(),
                this.getWidth(),
                this.getHeight(),
                this.getScaleX(),
                this.getScaleY(),
                this.getRotation(),
                this.sectionX,
                this.sectionY,
                this.sectionWidth,
                this.sectionHeight,
                this.flipX,
                this.flipY
        );
    }
}
