package com.mariogame.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mariogame.game.MarioBros;
import com.mariogame.game.scenes.HudScene;
import org.omg.CORBA.MARSHAL;

/**
 * Created by mike on 9/27/15.
 */
public class PlayScreen implements Screen {

    private MarioBros game;
    private OrthographicCamera camera;
    private Viewport cameraViewPort;
    private HudScene hud;

    public PlayScreen(MarioBros marioBros) {
        this.game = marioBros;
        camera = new OrthographicCamera();
        cameraViewPort = new FitViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, camera);
        hud = new HudScene(game.batch);
    }

    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        cameraViewPort.update(width,height);
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
