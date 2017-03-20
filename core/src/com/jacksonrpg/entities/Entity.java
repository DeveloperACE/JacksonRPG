package com.jacksonrpg.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Adrian on 3/14/17.
 */
public class Entity extends Actor {

    private Texture entityTexture;

    private Integer sectionX;
    private Integer sectionY;
    private Integer sectionWidth;
    private Integer sectionHeight;

    private Boolean flipX;
    private Boolean flipY;

    /** Creates a new entity with the x, y, width, height, rotation, section, flipX and flipY properties
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
     * @param flipX Should the entity be flipped horizontally?
     * @param flipY Should the entity be flipped vertically?
     */
    public Entity(Texture texture, float x, float y, float width, float height, float rotation, int sectionX, int sectionY, int sectionWidth, int sectionHeight, boolean flipX, boolean flipY) {

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
        this.flipX = flipX;
        this.flipY = flipY;

    }

    /** Creates a new entity with the x, y, width, height, rotation, flipX and flipY properties.
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
     * @param flipX Should the entity be flipped horizontally?
     * @param flipY Should the entity be flipped vertically?
     */
    public Entity(Texture texture, float x, float y, float width, float height, float rotation, boolean flipX, boolean flipY) {
        this(texture, x, y, width, height, rotation, 0, 0, texture.getWidth(), texture.getHeight(), flipX, flipY);
    }

    /** Creates a new entity with the x, y, width, height, rotation and section properties
     * Flip properties default to false (no flip)
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
     */
    public Entity(Texture texture, float x, float y, float width, float height, float rotation, int sectionX, int sectionY, int sectionWidth, int sectionHeight) {
        this(texture, x, y, width, height, rotation, sectionX, sectionY, sectionWidth, sectionHeight, false, false);
    }

    /** Creates a new entity with the x, y, width, height and rotation properties
     * Section properties default to the x, y, width and height values of the entire texture
     * Flip properties default to false (no flip)
     *
     * @param texture The texture for the entity
     * @param x The X coordinate of the entity
     * @param y The Y coordinate of the entity
     * @param width The width of the entity. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     * @param height The height of the entity. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     * @param rotation The number of degrees to rotate the entity
     */
    public Entity(Texture texture, float x, float y, float width, float height, float rotation) {
        this(texture, x, y, width, height, rotation, 0, 0, texture.getWidth(), texture.getHeight());
    }

    /** Creates a new entity with the x, y, width and height properties
     * Rotation defaults to 0 degrees
     * Section properties default to the x, y, width and height values of the entire texture
     * Flip properties default to false (no flip)
     *
     * @param texture The texture for the entity
     * @param x The X coordinate of the entity
     * @param y The Y coordinate of the entity
     * @param width The width of the entity. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     * @param height The height of the entity. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     */
    public Entity(Texture texture, float x, float y, float width, float height) {
        this(texture, x, y, width, height, 0);
    }

    /** Creates a new entity with the x and y properties
     * Width defaults to 50 pixels
     * Height defaults to 100 pixels
     * Rotation defaults to 0 degrees
     * Section properties default to the x, y, width and height values of the entire texture
     * Flip properties default to false (no flip)
     *
     * @param texture The texture for the entity
     * @param x The X coordinate of the entity
     * @param y The Y coordinate of the entity
     */
    public Entity(Texture texture, float x, float y) {
        this(texture, x, y, 50, 100);
    }

    /** Creates a new entity with the given texture
     * X and Y default to (0, 0)
     * Width defaults to 50 pixels
     * Height defaults to 100 pixels
     * Rotation defaults to 0 degrees
     * Section properties default to the x, y, width and height values of the entire texture
     * Flip properties default to false (no flip)
     *
     * @param texture The texture for the entity
     */
    public Entity(Texture texture) {
        this(texture, 0, 0);
    }


    /** Returns the current entity for inclusion in a Stage if needed
     *
     * @return The current Entity() object
     */
    public Actor getActor() {return this;}

    /** Move the Entity by
     *
     * @param x
     * @param y
     */
    public Entity(float x, float y) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
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
                this.flipX,
                this.flipY
        );
    }
}
