package com.jacksonrpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;

/**
 * Created by edwar12421 on 3/15/2017.
 */
public class MainMenu implements Screen {
    private Stage menuStage = new Stage();
    private static Texture backgroundTexture  = new Texture(Gdx.files.internal("core/assets/images/backgrounds/TitleScreen-BusBack.png"));
    private static Texture bannerTexture = new Texture(Gdx.files.internal("core/assets/images/bannerlogo.png"));
    private Skin menuButtonSkin;

    public MainMenu() {
        menuButtonSkin = getButtonStyling();
        Gdx.input.setInputProcessor(menuStage);

    }

    private TextButton makeButton(String text, float x, float y, float width, float height) {

        TextButton button = new TextButton(text, menuButtonSkin.get("default", TextButton.TextButtonStyle.class);
        button.setWidth(width);//200f
        button.setHeight(height);//20f
        button.setPosition(x, y);

       /* button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                jacksonrpg.setScreen(new Game(jacksonrpg));
                dispose();
            }
        });*/

       return button;
    }

    private Skin getButtonStyling() {
        Skin buttonSkin = new Skin();

        //Add font to skin
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2);
        buttonSkin.add("default", font);

        //add pixmap (idk wat this is)
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        buttonSkin.add("background", new Texture(pixmap));
        pixmap.dispose();

        //create and add button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = buttonSkin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = buttonSkin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = buttonSkin.newDrawable("background", Color.GRAY);
        textButtonStyle.font = buttonSkin.getFont("default");
        buttonSkin.add("default", textButtonStyle);

        return buttonSkin;
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void dispose() {

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
    public void resize(int width, int height) {

    }
}
