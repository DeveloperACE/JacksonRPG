package com.jacksonrpg.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jacksonrpg.JacksonRPG;

/** Displays a graphical loading screen while loading assets in the background
 *
 * Created by Adrian on 4/8/17.
 */
public class LoadingScreen implements Screen {

    private JacksonRPG jacksonrpg;
    private float elapsedTime = 0f;
    private float width = Gdx.graphics.getWidth();
    private float height = Gdx.graphics.getHeight();
    private float loadIconWidth = 25;
    private float loadIconHeight = 50;
    private float progress;


    private Animation<TextureRegion> loadingAnimation;

    private JacksonRPG.AppScreen postLoadScreen;


    /** Creates the Loading Screen with the main menu as the default screen to display after loading
     *
     * @param jacksonrpg the jacksonRPG instance from which to ue for asset loading .etc
     */
   public LoadingScreen(JacksonRPG jacksonrpg) {
        this(jacksonrpg, JacksonRPG.AppScreen.MAIN_MENU);
    }

    /** Creates the Loading Screen
     *
     * @param jacksonrpg the jacksonRPG instance from which to ue for asset loading .etc
     * @param postLoadScreen
     */
    public LoadingScreen(JacksonRPG jacksonrpg, JacksonRPG.AppScreen postLoadScreen) {
        this.jacksonrpg = jacksonrpg;
        this.postLoadScreen = postLoadScreen;

        //load the assets for loading screen in a blocking way
        jacksonrpg.getAssets().queueTextureAtlas(jacksonrpg.getAssets().LESSER_JACKSON_WALKING_ATLAS);
        jacksonrpg.getAssets().getManager().finishLoadingAsset(jacksonrpg.getAssets().LESSER_JACKSON_WALKING_ATLAS);

        //make animation
        TextureAtlas atlas = jacksonrpg.getAssets().getTextureAtlas(jacksonrpg.getAssets().LESSER_JACKSON_WALKING_ATLAS);
        loadingAnimation = new Animation<TextureRegion>(1f/19f, atlas.getRegions(), Animation.PlayMode.LOOP);
    }

    /** Continually calls the asset manager's update() function to actually load the queued assets (asynchronously)
     *  and updates the progress variable
     *
     * @return Boolean value indicating if the assetManager has completed loading or not
     * @see com.jacksonrpg.game.Assets
     */
    public Boolean update() {
        if (!jacksonrpg.getAssets().update()) {
            progress = jacksonrpg.getAssets().getProgress();
            return true;
        } else {
            return false;
        }



    }

   // public float getProgress() {return jacksonrpg.getAssets().getProgress();}

    /** Sets the screen to display after the loading screen is no longer needed
     *
     * @param postLoadScreen the screen to display after loading
     * @see JacksonRPG.AppScreen
     */
    public void setPostLoadScreen(JacksonRPG.AppScreen postLoadScreen) {this.postLoadScreen = postLoadScreen;}

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f,.74f,1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (update()) {
            elapsedTime += delta;

            TextureRegion currentFrame = loadingAnimation.getKeyFrame(elapsedTime);

            jacksonrpg.getBatch().draw(currentFrame, (width - loadIconWidth) * progress, (height / 2) - (loadIconHeight/2), loadIconWidth, loadIconHeight);
            jacksonrpg.getFont().draw(jacksonrpg.getBatch(), "Loading...", 175, 150);
        } else {
            switch (postLoadScreen) {
                case GAME:
                    jacksonrpg.getGame().assetsLoaded();
                    break;
                case MAIN_MENU:
                    jacksonrpg.getMenu().assetsLoaded();
                    break;

                default:
                    System.out.println(postLoadScreen);
                    break;
            }

            jacksonrpg.setScreenState(postLoadScreen);
            jacksonrpg.disposeLoadingScreen();

        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}