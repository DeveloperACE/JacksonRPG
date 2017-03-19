package com.jacksonrpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.jacksonrpg.JacksonRPG;
import com.jacksonrpg.characters.Character;

/**
 * Created by edwar12421 on 3/15/2017.
 */
public class MainMenu implements Screen {
    
    private JacksonRPG jacksonrpg;

    private Stage menuStage = new Stage();
    

    BitmapFont font = new BitmapFont();
    //used for checking font widths
    GlyphLayout layout = new GlyphLayout();

    Character lesserJackson;
    Character greaterJackson;


    private ClickListener clickListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            System.out.println("Click");
            //jacksonrpg.state = JacksonRPG.GameState.RUNNING;
            //dispose();
        }
    };



    public MainMenu(JacksonRPG jrpgInstance) {
        this.jacksonrpg = jrpgInstance;

        font.getData().setScale(2);


        Gdx.input.setInputProcessor(menuStage);
        
        jacksonrpg.assets.load("core/assets/images/titlescreen/TitleScreen-BusBack.png", Texture.class);
        jacksonrpg.assets.load("core/assets/images/titlescreen/bannerlogo.png", Texture.class);
        jacksonrpg.assets.load("core/assets/images/titlescreen/gj_sleeping.png", Texture.class);
        jacksonrpg.assets.load("core/assets/images/titlescreen/lj_sleeping.png", Texture.class);

    }
    
    public void assetsLoaded() {

        lesserJackson = new Character(
                jacksonrpg.assets.get("core/assets/images/titlescreen/lj_sleeping.png", Texture.class),
                -80,
                210,
                100,
                200,
                -90
        );
        lesserJackson.addListener(clickListener);
        menuStage.addActor(lesserJackson);

        greaterJackson = new Character(
                jacksonrpg.assets.get("core/assets/images/titlescreen/gj_sleeping.png", Texture.class),
                280,
                210,
                100,
                200,
                -90,
                false,
                true
        );
        greaterJackson.addListener(clickListener);
        menuStage.addActor(greaterJackson);


    }
    //use this for "load game save" feature
    private TextButton addTextButton(String text, float x, float y, float width, float height) {
        //create a skin to hold the styles for the button
        Skin baseStyle =  new Skin();
        //Set font
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2);
        baseStyle.add("defaultfont", font);

        //add pixmap (for background)
        Pixmap pixmap = new Pixmap(1,1, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        baseStyle.add("background", new Texture(pixmap));
        pixmap.dispose();

        //create and add button style backgrounds for different states
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = baseStyle.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = baseStyle.newDrawable("background", Color.GRAY);
        // textButtonStyle.up = baseStyle.newDrawable("background", Color.WHITE);
        textButtonStyle.font = baseStyle.getFont("defaultfont");
        baseStyle.add("textbutton", textButtonStyle);

        //create button using style/skin
        TextButton button = new TextButton(text, baseStyle.get("textbutton", TextButton.TextButtonStyle.class));
        button.setWidth(width);//200f
        button.setHeight(height);//20f
        button.setPosition(x, y);

        button.addListener(clickListener);

       return button;
    }
    

    private void drawHorizontallyCenteredText(String text, float y) {
        float width = getTextWidth(text);
        addText(text, (menuStage.getWidth()-width)/2, y);
    }
    private void addText(String text, float x, float y) {
        font.draw(menuStage.getBatch(), text, x, y);
    }
    private float getTextWidth(String text) {
        layout.setText(font, text);
        return layout.width;
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        

        menuStage.getBatch().begin();
        if (jacksonrpg.assets.isLoaded("core/assets/images/titlescreen/TitleScreen-BusBack.png")) {
            menuStage.getBatch().draw(
                    jacksonrpg.assets.get(
                            "core/assets/images/titlescreen/TitleScreen-BusBack.png",
                            Texture.class
                    ),
                    0,
                    0,
                    menuStage.getWidth(),
                    menuStage.getHeight()
            );
        }

        if (jacksonrpg.assets.isLoaded("core/assets/images/titlescreen/bannerlogo.png")) {
            menuStage.getBatch().draw(
                    jacksonrpg.assets.get(
                            "core/assets/images/titlescreen/bannerlogo.png",
                            Texture.class
                    ),
                    0,
                    menuStage.getHeight()-100,
                    400,//1536,
                    100 // 384*/
            );
        }
        drawHorizontallyCenteredText("Pick a Character To Start", 50);

        menuStage.getBatch().end();

        menuStage.act();
        menuStage.draw();


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