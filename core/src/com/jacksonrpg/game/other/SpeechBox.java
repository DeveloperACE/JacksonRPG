package com.jacksonrpg.game.other;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jacksonrpg.JacksonRPG;

import java.util.ArrayList;

public class SpeechBox extends Table {

    private JacksonRPG jacksonrpg;
    private Table textboxTable = new Table();
    private Texture textBox;

    protected ArrayList<String> textBuffer = new ArrayList<String>();


    public SpeechBox(JacksonRPG jacksonrpg) {
        this.jacksonrpg = jacksonrpg;

    }

    /** Queues the assets needed to construct the necessary variables for this screen
     *
     */
    public void queueAssets() {

        jacksonrpg.getAssets().queueTexture(jacksonrpg.getAssets().GAME_TEXT_BANNER);

        //pass the queueAssets() call to parts of map

    }

    public void setupTextbox() {
        textBox = jacksonrpg.getAssets().getTexture(jacksonrpg.getAssets().GAME_TEXT_BANNER);
        textboxTable.setBackground(new Image(textBox).getDrawable());
        textboxTable.setX(0);
        textboxTable.setY(0);
        textboxTable.setWidth(Gdx.graphics.getWidth());
        textboxTable.setHeight(100);
        textboxTable.setVisible(false);
    }
    public boolean isVisible() {return textboxTable.isVisible();}

    public Actor getTextboxTable() { return textboxTable;}

    @Override
    public void setVisible(boolean visible) { textboxTable.setVisible(visible);}
    public void setText(String text) {textboxTable.add(text, "Times New Roman", Color.BLACK );}
    public void addButton(String text) {textboxTable.add(text, "Times New Roman", Color.BLACK );}
    public void reset() {

        while (textboxTable.getCells().size >= 1) {

            int size = textboxTable.getCells().size;
            //Remove the last row
            textboxTable.getCells().removeIndex(size-1);

        }
    }
    public final void addTextToBuffer(String speech) {this.textBuffer.add(speech); }

    public final String getSpeechLine(int index) {
        String line = this.textBuffer.get(index);
        this.textBuffer.remove(index);
        return line;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

}
