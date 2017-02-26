package com.jacksonrpg.players;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.jacksonrpg.players.Players.*;

/**
 * Created by Adrian on 2/25/17.
 */
public class LesserJackson extends Players {

    private Integer walkFrames = 9;
    public Sprite sprite;

    private String walkingTextureSheetPath = "characters/lesserjackson/walking";
    private String jumpingTextureSheetPath;

    public LesserJackson() {
        sprite = Players.createPlayer(walkingTextureSheetPath);

    }



}
