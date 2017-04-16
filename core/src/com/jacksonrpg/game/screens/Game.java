package com.jacksonrpg.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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

    //pass the queueAssets() call to parts of world
    public void queueAssets() {
        player.queueAssets();
        world.queueAssets();

        jacksonrpg.getAssets().queueTexture(jacksonrpg.getAssets().HEALTH_BAR_TEXTURE);
    }

    //pass the assetsLoaded() call to parts of world
    public void assetsLoaded() {
        player.assetsLoaded();
        world.assetsLoaded();


        //hud.add()

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

    public TutorialWorld getWorld() {return world;}
    public Player getPlayer() {return player;}
    public Stage getStage() {return stage;}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.render(delta);
        stage.act();
        stage.draw();

        hudStage.act();
        hudStage.draw();


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
