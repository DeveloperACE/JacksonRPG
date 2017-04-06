package com.jacksonrpg.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jacksonrpg.JacksonRPG;
import com.jacksonrpg.game.Game;

import java.util.AbstractMap;

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

    private GraphicalState graphicalState = GraphicalState.FACINGRIGHT;
    private MovementState movementState = MovementState.STANDING;


    private Integer movementSpeed = 4;
    private Integer leftBorder = 0;
    private Integer rightBorder = 2148;

    private Integer healthLost = 0;//0-14, 0 = full, 14 = dead
    private Integer maxHealth = 14;

    private double monetaryBalance = 0;

    private TextureAtlas walkingAtlas;
    private Animation<TextureRegion> walkingAnimation;
    private float animationFramerate = 1/19;//default framerate 19fps
    private float elapsedTime = 0;



    public Player(JacksonRPG jrpginstance, PlayerName player) {

        super(/*jrpginstance,*/ new Texture(jrpginstance.assets.DEFAULT_ENTITY_TEXTURE_PATH), 0, 0, 1, 2, 0, false, false);
        jacksonrpg = jrpginstance;

        switch (player){
            case LESSERJACKSON:
                walkingAtlas = jacksonrpg.assets.lesserjacksonWalking;
                break;
            case GREATERJACKSON:
                //TODO: add greater jackson walking stuffs
                break;
        }

        walkingAnimation = new Animation<TextureRegion>(animationFramerate, walkingAtlas.getRegions(), Animation.PlayMode.LOOP);

    }


    /** Sets the animation frame rate
     *
     * @param rate The number of frames per second to set this characters anmations to
     */
    public void setFramerate(int rate) { this.animationFramerate = 1/rate;}


    @Override
    public void act(float delta) {
        //reset movement to standing in case no buttons are pressed
        this.movementState = MovementState.STANDING;

        checkKeyPresses();
        move();



        //elapsedTime += Gdx.graphics.getDeltaTime();
//        batch.begin();
//        batch.draw(runningAnimation.getKeyFrame(delta, true), 0, 0);
//        batch.end();


        super.act(delta);
    }

    @Override
    public Texture getTexture() {
        return walkingAnimation.getKeyFrame(0).getTexture();
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        elapsedTime += Gdx.graphics.getDeltaTime();

        if (this.movementState == MovementState.WALKING && this.graphicalState == GraphicalState.FACINGRIGHT) {

            batch.draw(getAnimationFrame(), this.getX(), this.getY(), this.getWidth(), this.getHeight());

        } else if (this.movementState == MovementState.WALKING && this.graphicalState == GraphicalState.FACINGLEFT) {

            batch.draw(getAnimationFrame(), this.getX()+this.getWidth(), this.getY(), -this.getWidth(), this.getHeight());

        } else if (this.movementState == MovementState.STANDING) {

            batch.draw(getAnimationFrame(0), this.getX(), this.getY(), this.getWidth(), this.getHeight());

        } else {
            elapsedTime += Gdx.graphics.getDeltaTime();
        }


//        switch (movementState) {
//            case WALKING:
//                switch (graphicalState) {
//                    case FACINGLEFT:
//                        break;
//                    case FACINGRIGHT:
//                        break;
//                }
//                break;
//            case STANDING:
//                switch (graphicalState) {
//                    case FACINGLEFT:
//                        break;
//                    case FACINGRIGHT:
//                        break;
//                }
//                break;
//        }
    }


    /** Gets the players current animation frame
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

    /** Sets the players movement boundaries on either side of the map
     *
     * @param left The leftmost x coordinate the player can go to
     * @param right The rightmost x coordinate the player can go to (auto-adjusted for player width)
     */
    public void setMovementBorders(Integer left, Integer right) {
        leftBorder = left;
        rightBorder = right;
    }

    /*
    public void adjustMovementBorders(Integer left, Integer right) {
        leftBorder = leftBorder + left;
        rightBorder = rightBorder + right;
    }*/

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


    /** Gives health to the player
     *
     */
    public void addHealth() {
        if (healthLost > 0) {
            //remove a lost health, basically getting one back
            healthLost = healthLost - 1;
        }
    }

    /** Removes health from the player
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
    public float getHealthPointsRemaining() {
        return maxHealth-healthLost;
    }

    /** Returns players current health as a float value from 0 to 1 (percentage)
     *
     * @return Float value from 0 to 1 (percentage) representing the players health
     */
    public float getHealth() {
        return maxHealth-healthLost/maxHealth;
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
     * @return The players current balance
     */
    public String getBalance() {return "" + monetaryBalance;}




}
