package com.jacksonrpg.players;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jacksonrpg.Game;

/**
 * Created by Adrian on 2/25/17.
 */
public class LesserJackson extends Actor {

    enum GraphicalState {
        FACINGRIGHT, FACINGLEFT
    }
    enum MovementState {
        STANDING, WALKING
    }

    private GraphicalState graphicalState = GraphicalState.FACINGRIGHT;
    private MovementState movementState = MovementState.STANDING;
    


    public Animation<TextureRegion> walkAnimation;

    private float elapsedTime = 0;

    private Integer movementSpeed = 4;

    private com.jacksonrpg.Game game;


//setup basic vars and load assets for character into game's asset manager
    public LesserJackson(com.jacksonrpg.Game game) {

        this.game = game;

        this.setX(0);
        this.setY(0);
        this.setWidth(100);
        this.setHeight(100);

        game.assets.load("characters/lesserjackson/walking.png", Texture.class);
        game.assets.load("characters/lesserjackson/walking.atlas", TextureAtlas.class);


    }
    //extention of constructor, called when assets are loaded. for post processing
    public void loaded() {

        if(game.assets.isLoaded("characters/lesserjackson/walking.atlas")) {
            // texture is available, let's fetch it and do something interesting
            TextureAtlas atlasFile = game.assets.get("characters/lesserjackson/walking.atlas", TextureAtlas.class);

            walkAnimation = new Animation<TextureRegion>(1/19f, atlasFile.getRegions());

        }


    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        elapsedTime += Gdx.graphics.getDeltaTime();

        if (this.movementState == MovementState.WALKING && this.graphicalState == GraphicalState.FACINGRIGHT) {

            batch.draw(walkAnimation.getKeyFrame(elapsedTime, true), this.getX(), this.getY(), this.getWidth(), this.getHeight());

        } else if (this.movementState == MovementState.WALKING && this.graphicalState == GraphicalState.FACINGLEFT) {

            batch.draw(walkAnimation.getKeyFrame(elapsedTime, true), this.getX()+this.getWidth(), this.getY(), -this.getWidth(), this.getHeight());

        } else if (this.movementState == MovementState.STANDING) {

            batch.draw(walkAnimation.getKeyFrame(0), this.getX(), this.getY(), this.getWidth(), this.getHeight());

        }
    }

    @Override
    public void act(float delta) {
        //reset movement to standing in case no buttons are pressed
        this.movementState = MovementState.STANDING;

        checkKeyPresses();



        //elapsedTime += Gdx.graphics.getDeltaTime();
//        batch.begin();
//        batch.draw(runningAnimation.getKeyFrame(delta, true), 0, 0);
//        batch.end();


        super.act(delta);
    }



    private void checkKeyPresses() {

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.movementState = MovementState.WALKING;
            this.graphicalState = GraphicalState.FACINGRIGHT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.movementState = MovementState.WALKING;
            this.graphicalState = GraphicalState.FACINGLEFT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            game.state = Game.GameState.PAUSED;

        }


        move();

    }

    private void move() {
        //if player is right of leftmost world border
        if (getX() > 0) {
            //allow movement left
            if (this.movementState == MovementState.WALKING && this.graphicalState == GraphicalState.FACINGLEFT) {
                setX(getX() - movementSpeed);
            }
        }

        //if player is left of rightmost world border
        if (getX() < this.getStage().getWidth()-getWidth()) {
            //allow movement right
            if (this.movementState == MovementState.WALKING && this.graphicalState == GraphicalState.FACINGRIGHT) {
                setX(getX() + movementSpeed);
            }
        }
    }
}
