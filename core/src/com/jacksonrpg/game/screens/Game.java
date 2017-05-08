package com.jacksonrpg.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jacksonrpg.JacksonRPG;
import com.jacksonrpg.entities.Entity;
import com.jacksonrpg.entities.Player;
import com.jacksonrpg.maps.*;
/**
 * Manages the interactions between game levels, saving progress and the in-game menu
 * TODO
 * Created by Adrian on 3/14/17.
 */
public class Game implements Screen {

    private JacksonRPG jacksonrpg;
    private World world;
    private Stage hudStage;
    private Table statsTable = new Table();
    private Table textboxTable = new Table();

    private Texture textBox;

    public Game(JacksonRPG jacksonrpg) {
        this.jacksonrpg = jacksonrpg;

        world = new World(jacksonrpg, World.Level.TUTORIAL);



        //queue all assets and load them
        //this is where the queueAssets() and assetsLoaded() calls originate from for the game
        queueAssets();
        jacksonrpg.makeLoadingScreen(true);
       // this.jacksonrpg.checkLoad(true);



    }

    /** Queues the assets needed to construct the necessary variables for this screen
     *
     */
    public void queueAssets() {


        jacksonrpg.getAssets().queueTexture(jacksonrpg.getAssets().HEALTH_BAR_TEXTURE);
        jacksonrpg.getAssets().queueTexture(jacksonrpg.getAssets().GAME_TEXT_BANNER);

        //pass the queueAssets() call to parts of map

       world.queueAssets();
    }

    /** Called when the assets requested in queueAssets() have been loaded successfully
     *
     */
    public void assetsLoaded() {
        //pass the assetsLoaded() call to parts of map
       world.assetsLoaded();


        setupHUD();
        setupTextbox();



        hudStage = new Stage(new ScreenViewport());
        hudStage.addActor(statsTable);
        hudStage.addActor(textboxTable);

    }



    private void setupHUD() {
        Image image = new Image(jacksonrpg.getAssets().getTexture(jacksonrpg.getAssets().HEALTH_BAR_TEXTURE));

        //create a skin to hold the styles for the button
        Skin skin =  new Skin();
        Label.LabelStyle lstyle = new Label.LabelStyle();
        lstyle.font = jacksonrpg.getFont();
        skin.add("default", lstyle);

        Label nameLabel = new Label(world.getPlayer().getHealthGrade(), skin);
        nameLabel.setColor(world.getPlayer().getHealthGradeColor());

        statsTable.setBackground(image.getDrawable());
        statsTable.add(nameLabel).pad(10);
        statsTable.left();
        //hud.add(image);
        statsTable.setWidth(200);
        statsTable.setHeight(50);
        statsTable.setX(5);
        statsTable.setY(350);
        // hud.setDebug(true);
    }

    private void setupTextbox() {
        textBox = jacksonrpg.getAssets().getTexture(jacksonrpg.getAssets().GAME_TEXT_BANNER);
        textboxTable.setBackground(new Image(textBox).getDrawable());
        textboxTable.setX(0);
        textboxTable.setY(0);
        textboxTable.setWidth(400);
        textboxTable.setHeight(100);
        textboxTable.setVisible(false);
    }


    public World getWorld() {return world;}
    public void setTextBoxVisible(boolean visible) {textboxTable.setVisible(visible);}
    public void setTextBoxText(String text) {textboxTable.add(text, "Times New Roman", Color.BLACK );}
    public void addTextBoxButton(String text) {textboxTable.add(text, "Times New Roman", Color.BLACK );}
    public void resetTextBox() {

        while (textboxTable.getCells().size >= 1) {

         int size = textboxTable.getCells().size;
         //Remove the last row
         textboxTable.getCells().removeIndex(size-1);

        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.render(delta);

        hudStage.act();
        hudStage.draw();



    }

    @Override
    public void dispose() {
        world.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void resize(int width, int height) {

    }
}
