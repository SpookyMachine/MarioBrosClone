package com.mariogame.game.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mariogame.game.MarioBros;
import com.mariogame.game.enums.TiledMapLayer;

/**
 * Created by mike on 9/28/15.
 */
public class Box2DCreationUtils {
    private static Box2DCreationUtils ourInstance = new Box2DCreationUtils();

    //Box2D Stuff
    private BodyDef bdef = new BodyDef();
    private FixtureDef fdef = new FixtureDef();

    public static Box2DCreationUtils getInstance() {
        return ourInstance;
    }

    private Box2DCreationUtils() {
    }

    /**
     * Adds box2d objects to world from @layerName and adds userData to all objects from layer
     * @param world
     * @param map
     * @param layerName
     * @param userData
     */
    public void generateLayer(World world, TiledMap map, TiledMapLayer layerName, String userData){
        for (MapObject object : map.getLayers().get(layerName.toString()).getObjects()) {
            if (object instanceof RectangleMapObject) {
                Shape shape;
                shape = getRectangle((RectangleMapObject) object);

                bdef.type = BodyDef.BodyType.StaticBody;

                fdef.friction = 0;
                fdef.isSensor = false;


                world.createBody(bdef).createFixture(shape, 1).setUserData(userData);
            }
        }
    }

    /**
     * Adds box2d objects to world from @layerName
     *
     * @param world
     * @param map
     * @param layerName
     *
     */
    public void generateLayer(World world, TiledMap map, TiledMapLayer layerName){
        generateLayer(world, map, layerName, null);
    }

    //Retrieving polygon shape from map object
    private PolygonShape getRectangle(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / MarioBros.PPM,
                (rectangle.y + rectangle.height * 0.5f) / MarioBros.PPM);
        polygon.setAsBox(rectangle.width * 0.5f / MarioBros.PPM , rectangle.height * 0.5f / MarioBros.PPM, size, 0.0f);
        return polygon;
    }
}
