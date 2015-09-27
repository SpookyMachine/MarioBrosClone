package com.mariogame.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import com.mariogame.game.MarioBros;
import com.mariogame.game.scenes.HudScene;
import org.omg.CORBA.MARSHAL;

/**
 * Created by mike on 9/27/15.
 */
public class PlayScreen implements Screen {

    //Default Stuff
    private MarioBros game;

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

    public PlayScreen(MarioBros marioBros) {
        this.game = marioBros;
        camera = new OrthographicCamera();
        cameraViewPort = new FitViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, camera);
        hud = new HudScene(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        camera.position.set(cameraViewPort.getWorldWidth() / 2f, cameraViewPort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();

        for (MapObject object : map.getLayers().get("Ground").getObjects()) {
            if (object instanceof RectangleMapObject) {
                Shape shape;
                shape = getRectangle((RectangleMapObject) object);

                bdef.type = BodyDef.BodyType.StaticBody;

                fdef.friction = 0;
                fdef.isSensor = false;

                world.createBody(bdef).createFixture(shape, 1);
            }
        }
    }

    private PolygonShape getRectangle(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f),
                (rectangle.y + rectangle.height * 0.5f));
        polygon.setAsBox(rectangle.width * 0.5f , rectangle.height * 0.5f , size, 0.0f);
        return polygon;
    }

    @Override
    public void show() {
        
    }

    public void update(float dt){
        handleInput(dt);

        camera.update();
        renderer.setView(camera);
    }

    private void handleInput(float dt) {
        if(Gdx.input.isTouched())
            camera.position.x += 100 * dt;
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        b2dr.render(world, camera.combined);

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
