package com.jacksonrpg.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jacksonrpg.JacksonRPG;

import java.util.ArrayList;

/** A simple non-movable entity that can be interacted with.
 *
 * Created by Adrian on 3/14/17.
 */
public class Entity extends Actor {


    private JacksonRPG jacksonrpg;

    private Texture entityTexture;

    private Integer sectionX;
    private Integer sectionY;
    private Integer sectionWidth;
    private Integer sectionHeight;

    private Boolean flipEntityX;
    private Boolean flipEntityY;

    private boolean interactable = false;
    private boolean interacted = false;
    private int closenessBuffer = 75;

    private Integer interactionAnimationWidth = 25;
    private Animation<TextureRegion> interactionAnimation;
    private TextureRegion checkBubble;
    protected float elapsedTime = 0;//private access to only this and any class that extends it

    protected ArrayList<String> speech;


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

    /** Creates a new entity with the x, y, width, height and rotation properties.
     * Section properties default to the x, y, width and height values of the entire texture
     * flipEntityX and flipEntityY properties default to false
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
    public Entity(JacksonRPG jacksonrpg, Texture texture, float x, float y, float width, float height, float rotation) {
        this(jacksonrpg, texture, x, y, width, height, rotation, 0, 0, texture.getWidth(), texture.getHeight(), false,false);
    }

    /** Creates a new entity with the x, y, width and height properties.
     * Section properties default to the x, y, width and height values of the entire texture
     * flipEntityX and flipEntityY properties default to false
     * rotation defaults to 0
     *
     * @param texture The texture for the entity
     * @param x The X coordinate of the entity
     * @param y The Y coordinate of the entity
     * @param width The width of the entity. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     * @param height The height of the entity. Texture will only be scaled to these dimensions if the aspect ratio
     *              matches that of the texture
     */
    public Entity(JacksonRPG jacksonrpg, Texture texture, float x, float y, float width, float height) {
        this(jacksonrpg, texture, x, y, width, height, 0);
    }


    /** Queues the assets needed to construct the necessary variables for this screen
     *
     */
    public void queueAssets() {
        jacksonrpg.getAssets().queueTextureAtlas(jacksonrpg.getAssets().SPEECHBUBBLE_ATLAS_PATH);

    }

    /** Called when the assets requested in queueAssets() have been loaded successfully
     *
     */
    public void assetsLoaded() {
        TextureAtlas atlas = jacksonrpg.getAssets().getTextureAtlas(jacksonrpg.getAssets().SPEECHBUBBLE_ATLAS_PATH);
        interactionAnimation = new Animation<TextureRegion>(1f/3f, atlas.findRegions("ellipsis"));
        checkBubble = atlas.findRegion("ok");

        System.out.println(atlas);
    }


    /** Gets the players horizontal midpoint/center along the X axis. Together with getPositionX(), this function
     *  returns the bottom middle point of the player
     *
     * @return the characters horizontal midpoint
     */
    public float getPositionX() {
        float midpoint = getX() + (getWidth()/2);
        return midpoint;
    }

    /** gets the characters current Y position. Together with getPositionX(), this function
     *  returns the bottom middle point of the player
     *
     * @return the y coordinate of the bottom of the character
     */
    public float getPositionY() {
        return getY();
    }

    /** Updates the Entity texture
     *
     * @param newEntityTexture The new texture to update to
     */
    public void changeTexture(Texture newEntityTexture) {
        entityTexture = newEntityTexture;
        setSectionBoundaries(0, 0, newEntityTexture.getWidth(), newEntityTexture.getHeight());
    }

    public void setSectionBoundaries(int sectionX, int sectionY, int sectionWidth, int sectionHeight) {
        this.sectionX = sectionX;
        this.sectionY = sectionY;
        this.sectionWidth = sectionWidth;
        this.sectionHeight = sectionHeight;
    }

    /**
     *
     * @return the closeness buffer trigger
     */
    public int getClosenessBuffer() {
        return closenessBuffer;
    }

    public boolean isCloseTo(float xCoordinate, int closenessBuffer) {
        //if given X coordinate is within the closenessBuffer of the current entity
        if (xCoordinate < getPositionX() + closenessBuffer && xCoordinate > getPositionX() - closenessBuffer){
            return true;
        } else {return false;}
    }

    public boolean isCloseTo(float xCoordinate) {
        return isCloseTo(xCoordinate, this.closenessBuffer);
    }


        /**
         * @returns the entity's texture
         */
    public Texture getTexture() {
        return entityTexture;
    }

    /** Sets if the entity should just be a static entity or should be interactable
     *
     * @param interactable Boolean representing if the entity should be able to be interacted with or not
     */
    public final void setInteractable(boolean interactable) {this.interactable = interactable;}

    public final void interacted(){

    }
    public final void say(String whatToSay){
        jacksonrpg.getGame().setTextBoxVisible(true);
        jacksonrpg.getGame().setTextBoxText(whatToSay);
        //TODO: add action to run setTextBoxVisible(false); jacksonrpg.getGame().resetTextBox(); when button clicked
    }
    public final void addSpeech(String speech) {
        this.speech.add(speech);
    }

    public final String saySpeechLine(int index) {
        String line = this.speech.get(index);
        this.speech.remove(index);
        return line;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        elapsedTime += Gdx.graphics.getDeltaTime();

        if (entityTexture != null) {
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

        //checks if characer is interactable, has an interaction animation, hasnt been interacted with and the player is close to the entity
        if (interactable && (interactionAnimation != null) && !interacted && isCloseTo(jacksonrpg.getGame().getWorld().getPlayer().getPositionX())) {

            batch.draw(interactionAnimation.getKeyFrame(elapsedTime, true), this.getX()+this.getWidth(), this.getY()+this.getHeight(), interactionAnimationWidth, interactionAnimationWidth);
           //TODO: maybe replace with "E" icon to suggest how to interact without having to center text
            jacksonrpg.getFont().draw(batch, "Press E to interact", getX()-50, 75);



        } else if (interactable && (checkBubble != null) && interacted) {

            batch.draw(checkBubble, this.getX()+this.getWidth(), this.getY()+this.getHeight(), interactionAnimationWidth, interactionAnimationWidth);

        }
    }
}
