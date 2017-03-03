package com.jacksonrpg.players;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jacksonrpg.players.Players.*;

/**
 * Created by Adrian on 2/25/17.
 */
public class LesserJackson extends Actor {


//    public Actor lesserJackson;

    private String textureSheetPath = "characters/lesserjackson/walking";
    private static Texture pngTexture;
    private static TextureAtlas atlasFile;
    TextureRegion region;

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

    //controls drawing/graphical state of character?
    @Override
    public void draw (Batch batch, float parentAlpha) {

        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw(walkAnimation.getKeyFrame(elapsedTime, true), this.getX(), this.getY(), this.getWidth(), this.getHeight());


    }

    //controls movement/errthang else?
    @Override
    public void act(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.setX(this.getX() + movementSpeed);
            System.out.println("D");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.setX(this.getX() - movementSpeed);
            System.out.println("A");
        }

        //System.out.println(delta);
        //elapsedTime += Gdx.graphics.getDeltaTime();
//        batch.begin();
//        batch.draw(runningAnimation.getKeyFrame(delta, true), 0, 0);
//        batch.end();


        super.act(delta);
    }
}
