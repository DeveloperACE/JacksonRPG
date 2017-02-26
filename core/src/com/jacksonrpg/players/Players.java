package com.jacksonrpg.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

/**
 * Created by Adrian on 2/25/17.
 */
public class Players {

    private static Texture pngTexture;
    private static TextureAtlas atlasFile;
    private static Sprite sprite;
    private static PlayerState state;

    enum PlayerState {
        WALKING_RIGHT, WALKING_LEFT, JUMPING_UP, FALLING_DOWN, SQUAT, STANDING
    }

    public static void main(String[] args) {


    }

    public static Sprite createPlayer(String textureSheetPath) {

        pngTexture = new Texture(Gdx.files.internal(textureSheetPath + ".png"));
        atlasFile = new TextureAtlas(Gdx.files.internal(textureSheetPath + ".atlas"));
        sprite = new Sprite(atlasFile.findRegion("Right00"));

        return sprite;
    }

    public refresh() {
//        if (state == PlayerState.WALKING_RIGHT) {
//
//        }
//
//        if (state == PlayerState.WALKING_LEFT) {
//
//        }
//
//        if (state == PlayerState.JUMPING_UP) {
//
//        }
//
//        if (state == PlayerState.FALLING_DOWN) {
//
//        }
//
//        if (state == PlayerState.SQUAT) {
//
//        }
//
//        if (state == PlayerState.STANDING) {
//
//        }
    }

    public checkState() {}
    public updatePlayerPosition(Boolean animate) {}
    public updateState() {}

}
