package com.jacksonrpg.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/** A simple non-movable entity that can be interacted with.
 *
 * Created by Adrian on 3/14/17.
 */
public class Entity extends Actor {

    private Texture entityTexture;

    private Integer sectionX;
    private Integer sectionY;
    private Integer sectionWidth;
    private Integer sectionHeight;

    private Boolean flipEntityX;
    private Boolean flipEntityY;

    protected float elapsedTime = 0;//private access to only this and any clas that extends it


    /** Creates a new entity with the x, y, width, height, rotation, section, flipEntityX and flipEntityY properties
     *
     * @param texture The texture for the entity
     * @param x The X coordinate of the entity
     * @param y The Y coordinate of the entity
     * @param width The width of the entity. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     * @param height The height of the entity. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     * @param rotation The number of degrees to rotate the entity
     * @param sectionX The X coordinate of the start of a section of the texture to render (if the texture contains
     *                 multiple sprites)
     * @param sectionY The Y coordinate of the start of a section of the texture to render (if the texture contains
     *                 multiple sprites)
     * @param sectionWidth The width of the section of the texture to render (if the texture contains multiple sprites)
     * @param sectionHeight The height of the section of the texture to render (if the texture contains multiple sprites)
     * @param flipEntityX Should the entity be flipped horizontally?
     * @param flipEntityY Should the entity be flipped vertically?
     */
    public Entity(JacksonRPG jacksonrpg, Texture texture, float x, float y, float width, float height, float rotation, int sectionX, int sectionY, int sectionWidth, int sectionHeight, boolean flipEntityX, boolean flipEntityY) {

        this.jacksonrpg = jacksonrpg;
        entityTexture = texture;

        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.rotateBy(rotation);
        this.sectionX = sectionX;
        this.sectionY = sectionY;
        this.sectionWidth = sectionWidth;
        this.sectionHeight = sectionHeight;
        this.flipEntityX = flipEntityX;
        this.flipEntityY = flipEntityY;

    }

    public void queueAssets() {
        jacksonrpg.getAssets().queueTextureAtlas(jacksonrpg.getAssets().SPEECHBUBBLE_ATLAS_PATH);

    }

    public void assetsLoaded() {
        TextureAtlas atlas = jacksonrpg.getAssets().getTextureAtlas(jacksonrpg.getAssets().SPEECHBUBBLE_ATLAS_PATH);
        interactionAnimation = new Animation<TextureRegion>(1f/3f, atlas.findRegions("ellipsis"));
    }

    /** Creates a new entity with the x, y, width, height, rotation, flipEntityX and flipEntityY properties.
     * Section properties default to the x, y, width and height values of the entire texture
     *
     * @param texture The texture for the entity
     * @param x The X coordinate of the entity
     * @param y The Y coordinate of the entity
     * @param width The width of the entity. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     * @param height The height of the entity. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     * @param rotation The number of degrees to rotate the entity
     * @param flipEntityX Should the entity be flipped horizontally?
     * @param flipEntityY Should the entity be flipped vertically?
     */
    public Entity(JacksonRPG jacksonrpg, Texture texture, float x, float y, float width, float height, float rotation, boolean flipEntityX, boolean flipEntityY) {
        this(jacksonrpg, texture, x, y, width, height, rotation, 0, 0, texture.getWidth(), texture.getHeight(), flipEntityX, flipEntityY);
    }
    }


    /** Updates the Entity texture
     *
     * @param newTexture The new texture to update to
     */
    public void changeTexture(Texture newTexture) {
        entityTexture = newTexture;
    }

    /**
     * @returns the entity's texture
     */
    public Texture getTexture() {
        return entityTexture;
    }


    public void showSpeechBubble() {

    }

    public void hideSpeechBubble() {

    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        elapsedTime += Gdx.graphics.getDeltaTime();

        batch.draw(
                entityTexture,
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
                this.flipEntityX,
                this.flipEntityY
        );
    }
}
