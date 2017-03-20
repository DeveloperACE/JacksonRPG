package com.jacksonrpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jacksonrpg.JacksonRPG;
import com.jacksonrpg.entities.Entity;

/** Manages the main menu of the game by displaying clickable objects for the user to select from
 * Created by edwar12421 (Adrian Edwards) on 3/15/2017.
 */
public class MainMenu implements Screen {
    
    private JacksonRPG jacksonrpg;

    private Stage menuStage = new Stage();

    private BitmapFont font = new BitmapFont();
    //used for checking font widths
    private GlyphLayout layout = new GlyphLayout();

    private static final String BACKGROUND_PATH = "core/assets/images/backgrounds/TitleScreen-BusBack.png";
    private static final String BANNER_PATH = "core/assets/images/bannerlogo.png";
    private static final String GJ_SLEEP_PATH = "core/assets/images/characters/greaterjackson/gj_sleeping.png";
    private static final String LJ_SLEEP_PATH = "core/assets/images/characters/lesserjackson/lj_sleeping.png";


    private Entity lesserJackson;
    private Entity greaterJackson;


    private ClickListener clickListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            System.out.println("Click");
            jacksonrpg.makeGame();
            jacksonrpg.state = JacksonRPG.GameScreen.GAME;
            dispose();
        }
    };

    private ClickListener gjClickListener = new ClickListener() {

        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                greaterJackson.setX(greaterJackson.getX() - 10);
        }
        @Override
        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                greaterJackson.setX(greaterJackson.getX() + 10);
        }
        @Override
        public void clicked(InputEvent event, float x, float y) {
            jacksonrpg.makeGame();
            jacksonrpg.state = JacksonRPG.GameScreen.GAME;
            dispose();
        }
    };

    private ClickListener ljClickListener = new ClickListener() {

        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            lesserJackson.setX(lesserJackson.getX() + 10);
        }
        @Override
        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            lesserJackson.setX(lesserJackson.getX() - 10);
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            jacksonrpg.makeGame();
            jacksonrpg.state = JacksonRPG.GameScreen.GAME;
            dispose();
        }
    };

    public MainMenu(JacksonRPG jrpgInstance) {
        this.jacksonrpg = jrpgInstance;

        font.getData().setScale(2);


        Gdx.input.setInputProcessor(menuStage);


        //load assets if not already loaded
        if (!jacksonrpg.assets.isLoaded(BACKGROUND_PATH)) {
            jacksonrpg.assets.load(BACKGROUND_PATH, Texture.class);
            jacksonrpg.assets.finishLoadingAsset(BACKGROUND_PATH);
        }

        if (!jacksonrpg.assets.isLoaded(BANNER_PATH)) {
            jacksonrpg.assets.load(BANNER_PATH, Texture.class);
            jacksonrpg.assets.finishLoadingAsset(BANNER_PATH);
        }

        if (!jacksonrpg.assets.isLoaded(GJ_SLEEP_PATH)) {
            jacksonrpg.assets.load(GJ_SLEEP_PATH, Texture.class);
            jacksonrpg.assets.finishLoadingAsset(GJ_SLEEP_PATH);
        }

        if (!jacksonrpg.assets.isLoaded(LJ_SLEEP_PATH)) {
            jacksonrpg.assets.load(LJ_SLEEP_PATH, Texture.class);
            jacksonrpg.assets.finishLoadingAsset(LJ_SLEEP_PATH);
        }


        lesserJackson = new Entity(
                jacksonrpg.assets.get(LJ_SLEEP_PATH, Texture.class),
                -80,
                210,
                100,
                200,
                -90
        );
        lesserJackson.addListener(ljClickListener);
        menuStage.addActor(lesserJackson);

        greaterJackson = new Entity(
                jacksonrpg.assets.get(GJ_SLEEP_PATH, Texture.class),
                280,
                210,
                100,
                200,
                -90,
                false,
                true
        );
        greaterJackson.addListener(gjClickListener);
        menuStage.addActor(greaterJackson);


    }

    /** Creates and returns some text as a clickable button to give the user more options
     *
     * @param text The text that the button will display
     * @param x The X coordinate of the button
     * @param y The Y coordinate of the button
     * @param width The width of the button
     * @param height The height of the button
     * @return A TextButton object to add to the stage as an actor
     */
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

    /** Draws text at the specified Y coordinate and automatically centers it horizontally on the stage
     *
     * @param text The string of text to show on screen
     * @param y The Y coordinate (height) at which to display the text
     */
    private void drawHorizontallyCenteredText(String text, float y) {
        float width = getTextWidth(text);
        addText(text, (menuStage.getWidth()-width)/2, y);
    }

    /** Draws text on the menu starting at the specified x and y coordinates
     *
     * @param text The string of text to show on screen
     * @param x The X coordinate at which to render the text
     * @param y The Y coordinate at which to render the text
     */
    private void addText(String text, float x, float y) {
        font.draw(menuStage.getBatch(), text, x, y);
    }

    /** Calculates the width of the specified text string using the font defined for the menu
     *
     * @param text The string of text to return the final rendered width for
     * @return Returns a float value of the number of pixels of width that the input string will need to display on screen
     */
    private float getTextWidth(String text) {
        layout.setText(font, text);
        return layout.width;
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        

        menuStage.getBatch().begin();
        if (jacksonrpg.assets.isLoaded(BACKGROUND_PATH)) {
            menuStage.getBatch().draw(
                    jacksonrpg.assets.get(
                            BACKGROUND_PATH,
                            Texture.class
                    ),
                    0,
                    0,
                    menuStage.getWidth(),
                    menuStage.getHeight()
            );
        }

        if (jacksonrpg.assets.isLoaded(BANNER_PATH)) {
            menuStage.getBatch().draw(
                    jacksonrpg.assets.get(
                            BANNER_PATH,
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
        //unload assets used
        jacksonrpg.assets.unload(BACKGROUND_PATH);
        jacksonrpg.assets.unload(BANNER_PATH);
        jacksonrpg.assets.unload(GJ_SLEEP_PATH);
        jacksonrpg.assets.unload(LJ_SLEEP_PATH);

        font.dispose();
        menuStage.dispose();


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