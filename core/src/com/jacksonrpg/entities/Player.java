package com.jacksonrpg.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jacksonrpg.JacksonRPG;

/**
 * Created by edwar12421 on 3/23/2017.
 */
public class Player extends Entity {

    private JacksonRPG jacksonrpg;
//jumping and stuf: https://github.com/libgdx/libgdx/blob/master/tests/gdx-tests/src/com/badlogic/gdx/tests/superkoalio/SuperKoalio.java
    private enum GraphicalState {
        FACINGRIGHT, FACINGLEFT
    }
    private enum MovementState {
        STANDING, WALKING
    }

    public enum PlayerName {
        LESSERJACKSON, GREATERJACKSON
    }
    private PlayerName player;

    private GraphicalState graphicalState = GraphicalState.FACINGRIGHT;
    private MovementState movementState = MovementState.STANDING;


    private Integer movementSpeed = 3;
    private Integer leftBorder = 0;
    private Integer rightBorder = 2148;

    private Integer healthLost = 0;//0-14, 0 = full, 14 = dead
    private Integer maxHealth = 14;

    private double monetaryBalance = 0;

    private TextureAtlas walkingAtlas;
    private Animation<TextureRegion> walkingAnimation;
    private float animationFramerate = 1f/18f;


    /** Creates a new player
     *
     * @param jacksonrpg the jacksonRPG instance from which to ue for asset loading .etc
     * @param player the {@link PlayerName} of the player to create. This determines which assets to use.
     */
    public Player(JacksonRPG jacksonrpg, PlayerName player) {

        super(jacksonrpg, new Texture(jacksonrpg.getAssets().DEFAULT_ENTITY_TEXTURE_PATH), 0, 0, 1, 2, 0, false, false);
        this.jacksonrpg = jacksonrpg;
        this.player = player;


    }

    @Override
    public void queueAssets() {
        switch (player){
            case LESSERJACKSON:
                jacksonrpg.getAssets().queueTextureAtlas(jacksonrpg.getAssets().LESSER_JACKSON_WALKING_ATLAS);
                break;
            case GREATERJACKSON:
                //TODO: add greater jackson assets
                break;
        }
    }

    @Override
    public void assetsLoaded() {

        switch (player){
            case LESSERJACKSON:
                walkingAtlas = jacksonrpg.getAssets().getTextureAtlas(jacksonrpg.getAssets().LESSER_JACKSON_WALKING_ATLAS);
                break;
            case GREATERJACKSON:
                //TODO: add greater jackson assets
                break;
         }

        walkingAnimation = new Animation<TextureRegion>(animationFramerate, walkingAtlas.getRegions(), Animation.PlayMode.LOOP);

    }

    /** This is called when the current world (loaded in {@link com.jacksonrpg.game.screens.Game}) has loaded so the
     * player can get the world dimensions and set its own movement boundaries
     *
     */
    public void worldReady() {
        rightBorder = jacksonrpg.getGame().getWorld().getMapWidthInPixels();
    }


    /** Sets the animation frame rate
     *
     * @param animationFramerate The number of frames per second to set this characters animations to
     */
    public void setFramerate(int animationFramerate) { this.animationFramerate = 1/animationFramerate;}


    @Override
    public void act(float delta) {
        //reset movement to standing in case no buttons are pressed
        if (movementState != MovementState.STANDING) {
            this.movementState = MovementState.STANDING;
        }

        checkKeyPresses();
        move();

        super.act(delta);
    }

//    @Override
//    public Texture getTexture() {
//        return walkingAnimation.getKeyFrame(0).getTexture();
//    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);//updates deltatime and updates the position of the invisible texture given in players constructor

        switch (movementState) {
            case WALKING:

                TextureRegion currentFrame = walkingAnimation.getKeyFrame(elapsedTime, true);

                switch (graphicalState) {
                    case FACINGLEFT:
                        batch.draw(currentFrame, this.getX()+this.getWidth(), this.getY(), -this.getWidth(), this.getHeight());
                        break;
                    case FACINGRIGHT:
                        batch.draw(currentFrame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
                        break;
                }
                break;
            case STANDING:
                switch (graphicalState) {
                    case FACINGLEFT:
                        batch.draw(getAnimationFrame(0), this.getX()+this.getWidth(), this.getY(), -this.getWidth(), this.getHeight());
                        break;
                    case FACINGRIGHT:
                        batch.draw(getAnimationFrame(0), this.getX(), this.getY(), this.getWidth(), this.getHeight());
                        break;
                }
                break;
        }


    }


    /** Gets the players animation frame for the given elapsed time
     *
     */
    public TextureRegion getAnimationFrame(float elapsedTime) {
       return walkingAnimation.getKeyFrame(elapsedTime, true);
    }

    /** Gets the players current animation frame
     *
     */
    public TextureRegion getAnimationFrame() {
        elapsedTime += Gdx.graphics.getDeltaTime();
        return getAnimationFrame(this.elapsedTime);
    }

    /** Checks for keys being pressed and updates the player state flags accordingly
     *
     */
    private void checkKeyPresses() {

        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
            this.movementState = MovementState.WALKING;
            this.graphicalState = GraphicalState.FACINGRIGHT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
            this.movementState = MovementState.WALKING;
            this.graphicalState = GraphicalState.FACINGLEFT;
        }
    }

    /** Updates player position based on state flags. If the player hits the border, they will keep animating, just without moving
     *
     */
    private void move() {
        //if player is more than one "movement" right of leftmost world border
        if (getX() > leftBorder + movementSpeed) {
            //allow movement left
            if (this.movementState == MovementState.WALKING && this.graphicalState == GraphicalState.FACINGLEFT) {
                setX(getX() - movementSpeed);
            }
        }

        //if player is more than one "movement" left of rightmost world border
        if (getX() < (rightBorder - movementSpeed)-getWidth()) {
            //allow movement right
            if (this.movementState == MovementState.WALKING && this.graphicalState == GraphicalState.FACINGRIGHT) {
                setX(getX() + movementSpeed);
            }
        }
    }

    /**
     *
     * @return The left boundary of player movement
     */
    public Integer getLeftBoundary() {return leftBorder;}

    /**
     *
     * @return The right boundary of player movement
     */
    public Integer getRightBoundary() {return rightBorder;}
    //public Integer getTopBoundary() {return topBorder;}
    //public Integer getBottomBoundary() {return bottomBorder;}


    /** Sets the players movement boundaries on either side of the map
     *
     * @param left The leftmost x coordinate the player can go to
     * @param right The rightmost x coordinate the player can go to (auto-adjusted for player width)
     */
    public void setMovementBorders(int left, int right) {
        leftBorder = left;
        rightBorder = right;
    }

    /** increases or decreases the players movement boundaries
     *
     * @param left the amount to increase or decrease the left boundary by
     * @param right the amount to increase or decrease the right boundary by
     */
    public void adjustMovementBorders(int left, int right) {
        leftBorder = leftBorder + left;
        rightBorder = rightBorder + right;
    }

    /** checks if the given x coordinate is within the player's movement boundaries to prevent glitches
     *
     * @param x The x coordinate to check
     * @return A boolean depicting whether the coordinate is within the boundaries or not
     */
    public Boolean checkSpawnPoint(float x/*, float y*/) {
        if(x > leftBorder && x < rightBorder) {
            return true;
        } else {
            return false;
        }

    }


    /** Gives a single health point back to the player
     *
     */
    public void addHealth() {
        if (healthLost > 0) {
            //remove a lost health, basically getting one back
            healthLost = healthLost - 1;
        }
    }

    /** Removes a single health point from the player
     *
     */
    public void subtractHealth() {
        if (healthLost < maxHealth) {
            //add one health lost, making the player "less healthy"
            healthLost = healthLost + 1;
        }
    }

    /** Returns the amount of health the player has left
     *
     * @return Float value representing the players health remaining
     */
    public int getHealthPointsRemaining() {
        return maxHealth-healthLost;
    }

    /** Returns players current health as a float value from 0 to 1 (percentage)
     *
     * @return Float value from 0 to 1 (percentage) representing the players health
     */
    public float getHealthPercentage() {
        return (maxHealth-healthLost)/maxHealth;
    }


    /** Returns players current health as a "Grade" A-F
     *
     * @return String value of the grade that the player has
     */
    public String getHealthGrade() {

       float percentage = getHealthPercentage();
       System.out.println(percentage);

       /*grading scale
        A+ 90-100%
        A 80-90%
        A- 70-80%

        B+ 60-70%
        B 50-60%
        B- 40-50%

        C+ 30-40%
        C 20-30%
        C- 10-20%

        D 1%-10%

        F 0%


        */
            if (percentage >= .9f && percentage <= 1f ) {return "A+";}
        else if (percentage >= .8f && percentage < .9f ) {return "A";}
        else if (percentage >= .7f && percentage < .8f ) {return "A-";}
        else if (percentage >= .6f && percentage < .7f ) {return "B+";}
        else if (percentage >= .5f && percentage < .6f ) {return "B";}
        else if (percentage >= .4f && percentage < .5f ) {return "B-";}
        else if (percentage >= .3f && percentage < .4f ) {return "C+";}
        else if (percentage >= .2f && percentage < .3f ) {return "C";}
        else if (percentage >= .1f && percentage < .2f ) {return "C-";}

        else if (percentage > 0f && percentage < .1f ) {return "D";}

        else if (percentage == 0f ) {return "F";}

        else {return " ";}

    }


    public Color getHealthGradeColor() {

        if (getHealthGrade().contains("A")) {
            return Color.GREEN;
        } else if (getHealthGrade().contains("B")) {
            return Color.BLUE;
        } else if (getHealthGrade().contains("C")) {
            return Color.GOLDENROD;
        } else if (getHealthGrade().contains("D")) {
            return Color.BROWN;
        } else if (getHealthGrade().contains("F")) {
            return Color.RED;
        } else {
            return Color.PINK;
        }
    }

    /** Returns the amount of health the player has lost
     *
     * @return The amount of health the player has lost
     */
    public int getHealthLost() {
        return healthLost;
    }






    /** Adds money to the players balance
     *
     * @param amount The amount to add
     */
    public void addMoney(double amount) {
        monetaryBalance = monetaryBalance + amount;
    }

    /** Checks to see if the player has enough money to afford something
     *
     * @param amount The amount of the purchase that is being checked
     * @return boolean value depicting if the player has enough balance for the purchace
     */
    public Boolean canSpend(double amount) {
        if (monetaryBalance - amount > 0) {
            return true;
        } else {
            return false;
        }
    }

    /** Removes money from the players balance
     *
     * @param amount The amount of money to remove
     */
    public void subtractMoney(double amount) {
        if (canSpend(amount)) {
            monetaryBalance = monetaryBalance - amount;
        } else {
            //TODO: HANDLE NOT ENOUGH FUNDS
            System.out.println("ERROR: NOT ENOUGH FUNDS TO SPEND");
        }
    }

    /**
     *
     * @return The players current balance as a string
     */
    public String getBalance() {return "" + monetaryBalance;}




}
