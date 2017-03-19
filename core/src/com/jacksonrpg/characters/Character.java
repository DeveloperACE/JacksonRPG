package com.jacksonrpg.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jacksonrpg.JacksonRPG;

/**
 * Created by Adrian on 3/14/17.
 */
public class Character extends Actor {

    private Texture characterTexture;

    private Integer sectionX;
    private Integer sectionY;
    private Integer sectionWidth;
    private Integer sectionHeight;

    private Boolean flipX;
    private Boolean flipY;

    /** Creates a new character with the x, y, width, height, rotation, section, flipX and flipY properties
     *
     * @param texture The texture for the character
     * @param x The X coordinate of the character
     * @param y The Y coordinate of the character
     * @param width The width of the character. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     * @param height The height of the character. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     * @param rotation The number of degrees to rotate the character
     * @param sectionX The X coordinate of the start of a section of the texture to render (if the texture contains
     *                 multiple sprites)
     * @param sectionY The Y coordinate of the start of a section of the texture to render (if the texture contains
     *                 multiple sprites)
     * @param sectionWidth The width of the section of the texture to render (if the texture contains multiple sprites)
     * @param sectionHeight The height of the section of the texture to render (if the texture contains multiple sprites)
     * @param flipX Should the character be flipped horizontally?
     * @param flipY Should the character be flipped vertically?
     */
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

    /** Creates a new character with the x, y, width, height, rotation, flipX and flipY properties.
     * Section properties default to the x, y, width and height values of the entire texture
     *
     * @param texture The texture for the character
     * @param x The X coordinate of the character
     * @param y The Y coordinate of the character
     * @param width The width of the character. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     * @param height The height of the character. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     * @param rotation The number of degrees to rotate the character
     * @param flipX Should the character be flipped horizontally?
     * @param flipY Should the character be flipped vertically?
     */
    public Character(Texture texture, float x, float y, float width, float height, float rotation, boolean flipX, boolean flipY) {
        this(texture, x, y, width, height, rotation, 0, 0, texture.getWidth(), texture.getHeight(), flipX, flipY);
    }

    /** Creates a new character with the x, y, width, height, rotation and section properties
     * Flip properties default to false (no flip)
     *
     * @param texture The texture for the character
     * @param x The X coordinate of the character
     * @param y The Y coordinate of the character
     * @param width The width of the character. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     * @param height The height of the character. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     * @param rotation The number of degrees to rotate the character
     * @param sectionX The X coordinate of the start of a section of the texture to render (if the texture contains
     *                 multiple sprites)
     * @param sectionY The Y coordinate of the start of a section of the texture to render (if the texture contains
     *                 multiple sprites)
     * @param sectionWidth The width of the section of the texture to render (if the texture contains multiple sprites)
     * @param sectionHeight The height of the section of the texture to render (if the texture contains multiple sprites)
     */
    public Character(Texture texture, float x, float y, float width, float height, float rotation, int sectionX, int sectionY, int sectionWidth, int sectionHeight) {
        this(texture, x, y, width, height, rotation, sectionX, sectionY, sectionWidth, sectionHeight, false, false);
    }

    /** Creates a new character with the x, y, width, height and rotation properties
     * Section properties default to the x, y, width and height values of the entire texture
     * Flip properties default to false (no flip)
     *
     * @param texture The texture for the character
     * @param x The X coordinate of the character
     * @param y The Y coordinate of the character
     * @param width The width of the character. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     * @param height The height of the character. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     * @param rotation The number of degrees to rotate the character
     */
    public Character(Texture texture, float x, float y, float width, float height, float rotation) {
        this(texture, x, y, width, height, rotation, 0, 0, texture.getWidth(), texture.getHeight());
    }

    /** Creates a new character with the x, y, width and height properties
     * Rotation defaults to 0 degrees
     * Section properties default to the x, y, width and height values of the entire texture
     * Flip properties default to false (no flip)
     *
     * @param texture The texture for the character
     * @param x The X coordinate of the character
     * @param y The Y coordinate of the character
     * @param width The width of the character. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     * @param height The height of the character. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     */
    public Character(Texture texture, float x, float y, float width, float height) {
        this(texture, x, y, width, height, 0);
    }

    /** Creates a new character with the x and y properties
     * Width defaults to 50 pixels
     * Height defaults to 100 pixels
     * Rotation defaults to 0 degrees
     * Section properties default to the x, y, width and height values of the entire texture
     * Flip properties default to false (no flip)
     *
     * @param texture The texture for the character
     * @param x The X coordinate of the character
     * @param y The Y coordinate of the character
     */
    public Character(Texture texture, float x, float y) {
        this(texture, x, y, 50, 100);
    }

    /** Creates a new character with the given texture
     * X and Y default to (0, 0)
     * Width defaults to 50 pixels
     * Height defaults to 100 pixels
     * Rotation defaults to 0 degrees
     * Section properties default to the x, y, width and height values of the entire texture
     * Flip properties default to false (no flip)
     *
     * @param texture The texture for the character
     */
    public Character(Texture texture) {
        this(texture, 0, 0);
    }


    /** Returns the current character for inclusion in a Stage if needed
     *
     * @return The current Character() object
     */
    public Actor getActor() {return this;}


    @Override
    public void draw(Batch batch, float parentAlpha) {
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
