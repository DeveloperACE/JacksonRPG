package com.jacksonrpg.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/** A simple non-movable character that can be interacted with.
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
    public Entity(Texture texture, float x, float y, float width, float height, float rotation, int sectionX, int sectionY, int sectionWidth, int sectionHeight, boolean flipEntityX, boolean flipEntityY) {

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
    public Entity(Texture texture, float x, float y, float width, float height, float rotation, boolean flipEntityX, boolean flipEntityY) {
        this(texture, x, y, width, height, rotation, 0, 0, texture.getWidth(), texture.getHeight(), flipEntityX, flipEntityY);
    }


    /** Updates the Entity texture
     *
     * @param newTexture The new texture to update to
     */
    public void updateTexture(Texture newTexture) {
        entityTexture = newTexture;
    }

    /** Returns the entity texture
     *
     * @returns the entity's texture
     */
    public Texture getTexture() {
        return entityTexture;
    }



    /** Updates the Entity texture
     *
     * @param newTexture The new texture to update to
     */
    public void updateTexture(Texture newTexture) {
        entityTexture = newTexture;
    }


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
                this.flipEntityX,
                this.flipEntityY
        );
    }
}
