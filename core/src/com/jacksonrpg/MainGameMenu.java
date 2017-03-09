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

    final JacksonRPG jacksonrpg;

    public static Texture backgroundTexture;
    public static Texture bannerTexture;

    private Stage menuStage = new Stage();

    private Skin skin = new Skin();
   // private SpriteBatch menuBatch = new SpriteBatch();


    public MainGameMenu(final JacksonRPG jacksonrpg) {
        this.jacksonrpg = jacksonrpg;

        Gdx.input.setInputProcessor(menuStage);

        backgroundTexture = new Texture(Gdx.files.internal("images/backgrounds/TitleScreen-BusBack.png"));
        bannerTexture = new Texture(Gdx.files.internal("images/bannerlogo.png"));


        makeButton("DEMO", 0, 125, 100, 40);

    }

    //TODO: WAT
    public void createSkin() {
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2);

        skin.add("default", font);

        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        skin.add("background", new Texture(pixmap));
        pixmap.dispose();

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();

        //normal color
        //textButtonStyle.up = skin.newDrawable("background", Color.LIGHT_GRAY);

        //click+hold
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        // wat
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        //hover
        textButtonStyle.over = skin.newDrawable("background", Color.GRAY);


        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

    }

    public void makeButton(String text, float x, float y, float width, float height)  {
        createSkin();
        final TextButton button = new TextButton(text, skin);

        button.setWidth(width);//200f
        button.setHeight(height);//20f
        button.setPosition( x, y);//Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 10f

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                jacksonrpg.setScreen(new Game(jacksonrpg));
                dispose();
            }
        });

        menuStage.addActor(button);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f,.74f,1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        menuStage.getBatch().begin();
        menuStage.getBatch().draw(backgroundTexture, 0, 0, menuStage.getWidth(), menuStage.getHeight());
        menuStage.getBatch().draw(bannerTexture, 0, menuStage.getHeight()-50, menuStage.getWidth(), 50);//50 was a manually calculated height

        menuStage.getBatch().end();

        menuStage.draw();
        menuStage.act();

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
        backgroundTexture.dispose();
    }

}