package com.mariogame.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mariogame.game.Entities.Mario;
import com.mariogame.game.MarioBros;
import com.mariogame.game.enums.TiledMapLayer;
import com.mariogame.game.listeners.WorldContactListener;
import com.mariogame.game.scenes.HudScene;
import com.mariogame.game.utils.Box2DCreationUtils;
import org.omg.CORBA.MARSHAL;

/**
 * Created by mike on 9/27/15.
 */
public class PlayScreen implements Screen {

    //Default Stuff
    private MarioBros game;
    private TextureAtlas atlas;

    //Cameras Stuff
    private OrthographicCamera camera;
    private Viewport cameraViewPort;
    private HudScene hud;

    //Tiled Map Stuff
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2D Stuff
    private World world;
    private Box2DDebugRenderer b2dr;
    private Mario player;

    public PlayScreen(MarioBros marioBros) {
        this.game = marioBros;
        atlas = new TextureAtlas("Mario.pack");

        camera = new OrthographicCamera();
        cameraViewPort = new FitViewport(MarioBros.V_WIDTH / MarioBros.PPM, MarioBros.V_HEIGHT / MarioBros.PPM, camera);
        hud = new HudScene(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 /  MarioBros.PPM);

        camera.position.set(cameraViewPort.getWorldWidth() / 2f, cameraViewPort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,-10f), true);
        b2dr = new Box2DDebugRenderer();

        player = new Mario(world, atlas);

        Box2DCreationUtils.getInstance().generateLayer(world,map, TiledMapLayer.COINS, "coins");
        Box2DCreationUtils.getInstance().generateLayer(world,map, TiledMapLayer.GROUND, "ground");
        Box2DCreationUtils.getInstance().generateLayer(world,map, TiledMapLayer.GOOMBAS, "goomba");
        Box2DCreationUtils.getInstance().generateLayer(world,map, TiledMapLayer.PIPES, "pipe");
        Box2DCreationUtils.getInstance().generateLayer(world,map, TiledMapLayer.BRICKS, "brick");

        world.setContactListener(new WorldContactListener());


    }



    @Override
    public void show() {
        
    }

    public void update(float dt){
        handleInput(dt);

        world.step(1 / 60f, 6, 2);

        player.update(dt);

        camera.update();
        renderer.setView(camera);
    }

    private void handleInput(float dt) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            player.body.applyLinearImpulse(new Vector2(0, 4f), player.body.getWorldCenter(), true);

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.body.getLinearVelocity().x <= 2)
            player.body.applyLinearImpulse(new Vector2(0.1f, 0), player.body.getWorldCenter(), true);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.body.getLinearVelocity().x >= -2)
            player.body.applyLinearImpulse(new Vector2(-0.1f, 0), player.body.getWorldCenter(), true);

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        //Follow the player
        camera.position.x = player.body.getPosition().x;

        //Box2D debug
        b2dr.render(world, camera.combined);

        //Draw player
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();

        //Draw hud
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

    public TextureAtlas getAtlas() {
        return atlas;
    }
}
