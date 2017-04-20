package com.jacksonrpg.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jacksonrpg.JacksonRPG;
import com.jacksonrpg.entities.Player;
import com.jacksonrpg.maps.TutorialWorld;

/**
 * Manages the creation and assembly of everything required to make the game, including maps, players, Entities .etc.
 * Created by Adrian on 3/14/17.
 */
public class Game implements Screen {

    private JacksonRPG jacksonrpg;
    private TutorialWorld world;
    private Player player;
    private Stage stage;
    private Stage hudStage;
    private Table hudTable;

    private boolean textboxVisible = false;
    private Texture textBox;

    public Game(JacksonRPG jacksonrpg) {
        this.jacksonrpg = jacksonrpg;
        player = new Player(this.jacksonrpg, Player.PlayerName.LESSERJACKSON);

        world = new TutorialWorld(this.jacksonrpg);
        hudTable = new Table();

       if (player.checkSpawnPoint(100)){
            player.setX(100);
            player.setY(100);
        }
        player.setWidth(50);
        player.setHeight(100);

        queueAssets();
        jacksonrpg.makeLoadingScreen(true);
       // this.jacksonrpg.checkLoad(true);



    }

    /** Queues the assets needed to construct the necessary variables for this screen
     *
     */
    public void queueAssets() {
        //pass the queueAssets() call to parts of world
        player.queueAssets();
        world.queueAssets();

        jacksonrpg.getAssets().queueTexture(jacksonrpg.getAssets().HEALTH_BAR_TEXTURE);
        jacksonrpg.getAssets().queueTexture(jacksonrpg.getAssets().GAME_TEXT_BANNER);
    }

    /** Called when the assets requested in queueAssets() have been loaded successfully
     *
     */
    public void assetsLoaded() {
        //pass the assetsLoaded() call to parts of world
        player.assetsLoaded();
        world.assetsLoaded();


        textBox = jacksonrpg.getAssets().getTexture(jacksonrpg.getAssets().GAME_TEXT_BANNER);

        //create a skin to hold the styles for the button
        Image image = new Image(jacksonrpg.getAssets().getTexture(jacksonrpg.getAssets().HEALTH_BAR_TEXTURE));

        Skin skin =  new Skin();
        Label.LabelStyle lstyle = new Label.LabelStyle();
        lstyle.font = jacksonrpg.getFont();
        skin.add("default", lstyle);

        Label nameLabel = new Label(player.getHealthGrade(), skin);
        nameLabel.setColor(player.getHealthGradeColor());

        hudTable.setBackground(image.getDrawable());
        hudTable.add(nameLabel).pad(10);
        hudTable.left();
        //hud.add(image);
        hudTable.setWidth(200);
        hudTable.setHeight(50);
        hudTable.setX(5);
        hudTable.setY(350);
       // hud.setDebug(true);



       // stage.assetsLoaded();
        stage = new Stage(new ScreenViewport(world.getCamera()));
        stage.addActor(player);

        hudStage = new Stage(new ScreenViewport());
        hudStage.addActor(hudTable);

        player.worldReady();
    }

    /** Returns the world for the current Game() instance
     *
     * @return the world for the current Game() instance
     */
    public TutorialWorld getWorld() {return world;}

    /** Returns the player for the current Game() instance
     *
     * @return the player for the current Game() instance
     */
    public Player getPlayer() {return player;}

    /** Returns the stage for the current Game() instance
     *
     * @return the stage for the current Game() instance
     */
    public Stage getStage() {return stage;}


    public void setTextBoxVisible(boolean visible) {this.textboxVisible = visible;}


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.render(delta);
        stage.act();
        stage.draw();

        hudStage.act();
        hudStage.draw();

        if (textboxVisible) {

        }


    }

    @Override
    public void dispose() {
        stage.dispose();
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
