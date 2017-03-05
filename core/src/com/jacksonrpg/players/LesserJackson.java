package com.jacksonrpg.players;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

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

    private static Texture pngTexture;
    private static TextureAtlas atlasFile;
    private String textureSheetPath = "characters/lesserjackson/walking";

    private TextureRegion region;

    public Animation<TextureRegion> walkAnimation;

    private float elapsedTime = 0;

    private Integer movementSpeed = 4;



    public LesserJackson() {
        this.setX(0);
        this.setY(0);
        this.setWidth(100);
        this.setHeight(100);

        pngTexture = new Texture(Gdx.files.internal(textureSheetPath + ".png"));
        atlasFile = new TextureAtlas(Gdx.files.internal(textureSheetPath + ".atlas"));


        walkAnimation = new Animation<TextureRegion>(1/19f, atlasFile.getRegions());


        region = new TextureRegion(pngTexture, 0,0,100,100);

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





        //elapsedTime += Gdx.graphics.getDeltaTime();
//        batch.begin();
//        batch.draw(runningAnimation.getKeyFrame(delta, true), 0, 0);
//        batch.end();


        super.act(delta);
    }

    private void move() {
        if (getX() >= 0 || getX() <= this.getStage().getWidth()) {

            if (this.movementState == MovementState.WALKING && this.graphicalState == GraphicalState.FACINGRIGHT) {
                setX(getX() + movementSpeed);
            } else if (this.movementState == MovementState.WALKING && this.graphicalState == GraphicalState.FACINGLEFT) {
                setX(getX() - movementSpeed);
            }
        }
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

        move();

    }
}
