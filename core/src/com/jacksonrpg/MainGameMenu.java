package com.jacksonrpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Adrian on 2/26/17.
 */

public class MainGameMenu implements Screen {

    final JacksonRPG game;

    private Stage menuStage = new Stage();
    private Skin skin = new Skin();
    private SpriteBatch menuBatch = new SpriteBatch();


    public MainGameMenu(final JacksonRPG gam) {
        game = gam;

        createSkin();
    }

    public void createSkin() {
        //add font
        skin.add("default", new BitmapFont());

        //Create a texture
        //TODO make button sizes fixed
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        skin.add("background", new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);


        final TextButton button = new TextButton("TEST", skin);

        button.setWidth(200f);
        button.setHeight(20f);
        button.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 10f);

        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new Game(game));
                dispose();
            }
        });

        menuStage.addActor(button);

        Gdx.input.setInputProcessor(menuStage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f,.74f,1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

       // camera.update();
       //game.batch.setProjectionMatrix(camera.combined);

       // game.batch.begin();
       // game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
       // game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        menuBatch.begin();
        menuStage.act();
        menuStage.draw();
        menuBatch.end();

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        menuStage.dispose();
        menuBatch.dispose();
    }

}