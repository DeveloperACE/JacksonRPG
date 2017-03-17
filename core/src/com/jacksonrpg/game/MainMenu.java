package com.jacksonrpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jacksonrpg.JacksonRPG;

/**
 * Created by edwar12421 on 3/15/2017.
 */
public class MainMenu implements Screen {
    
    private JacksonRPG jacksonrpg;

    private Stage menuStage = new Stage();
    private Skin menuButtonSkin = getButtonStyling();

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
        
        Gdx.input.setInputProcessor(menuStage);
        
        jacksonrpg.assets.load("core/assets/images/titlescreen/TitleScreen-BusBack.png", Texture.class);
        jacksonrpg.assets.load("core/assets/images/titlescreen/bannerlogo.png", Texture.class);
        jacksonrpg.assets.load("core/assets/images/titlescreen/gj_sleeping.png", Texture.class);
        jacksonrpg.assets.load("core/assets/images/titlescreen/lj_sleeping.png", Texture.class);

        //TODO: use asset manager for menu background, banner and character textures
    }
    
    public void assetsLoaded() {

        //TextButton butt = addButton("DEMO", 200, 125, 100, 40);
        //butt.rotateBy(90);
        menuStage.addActor(addButton("DEMO", 0, 125, 100, 40));


        menuStage.addActor(addCharacterButton("core/assets/images/titlescreen/lj_sleeping.png"));
    }

    private TextButton addButton(String text, float x, float y, float width, float height) {

        TextButton button = new TextButton(text, menuButtonSkin.get("default", TextButton.TextButtonStyle.class));
        button.setWidth(width);//200f
        button.setHeight(height);//20f
        button.setPosition(x, y);

       button.addListener(clickListener);

       return button;
    }

    private Actor addCharacterButton(String fileName) {

        Texture tex = jacksonrpg.assets.get(fileName, Texture.class);//fetchAsset("core/assets/images/titlescreen/lj_sleeping.png", Texture.class);
        ButtonActor act = new ButtonActor(tex, 200, 200, 50, 100);
        System.out.println(act.getRotation());
        act.setRotation(90);
        System.out.println(act.getRotation());
        act.setTouchable(Touchable.enabled);
        act.addListener(clickListener);

        return act;
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
        menuStage.draw();
        menuStage.act(delta);
        menuStage.getBatch().end();



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





 class ButtonActor extends Actor {
    public Texture texture;
    Float x;
    Float y;
    Float width;
    Float height;

    public ButtonActor(Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, this.x, this.y, this.width, this.height);
    }

}
