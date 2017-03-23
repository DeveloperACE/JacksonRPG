package com.jacksonrpg.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/** A simple non-movable character that can be interacted with.
 *
 * Created by Adrian on 3/14/17.
 */
public class Entity extends Actor {

    private Texture entityTexture;
    private Animation animation;
    private TextureAtlas animationAtlas;

    private Integer sectionX;
    private Integer sectionY;
    private Integer sectionWidth;
    private Integer sectionHeight;

    private Boolean flipEntityX;
    private Boolean flipEntityY;

    float stateTime;

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

    /** Updates the Entity texture
     *
     * @param newTexture The new texture to update to
     */
    public void updateTexture(Texture newTexture) {
        entityTexture = newTexture;
    }


    public void addTextureAnimation(TextureAtlas atlas, float frameRate) {
        animationAtlas = atlas;
        animation = new Animation<TextureRegion>(1/frameRate, atlas.findRegions("running"), Animation.PlayMode.LOOP);
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
    public void moveBy(float x, float y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        stateTime += Gdx.graphics.getDeltaTime();

        if (animation != null) {
            Integer currentFrameIndex = animation.getKeyFrameIndex(stateTime);
            entityTexture = animationAtlas.getRegions().get(currentFrameIndex).getTexture();

        }

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
