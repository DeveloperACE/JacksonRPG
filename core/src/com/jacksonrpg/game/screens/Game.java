package com.jacksonrpg.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jacksonrpg.JacksonRPG;
import com.jacksonrpg.game.other.SpeechBox;
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
    private SpeechBox speechBox;


    public Game(JacksonRPG jacksonrpg) {
        this.jacksonrpg = jacksonrpg;

        world = new World(jacksonrpg, World.Level.TUTORIAL);
        speechBox = new SpeechBox(jacksonrpg);


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

        //pass the queueAssets() call to parts of map
        speechBox.queueAssets();
        world.queueAssets();
    }

    /** Called when the assets requested in queueAssets() have been loaded successfully
     *
     */
    public void assetsLoaded() {
        //pass the assetsLoaded() call to parts of map
       world.assetsLoaded();


        setupHUD();
        speechBox.setupTextbox();



        hudStage = new Stage(new ScreenViewport());
        hudStage.addActor(statsTable);
        hudStage.addActor(speechBox.getTextboxTable());

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



    public World getWorld() {return world;}
    public SpeechBox getSpeechBox() {return speechBox;}



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
