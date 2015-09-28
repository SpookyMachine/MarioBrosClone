package com.mariogame.game.Entities;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mariogame.game.MarioBros;
import com.mariogame.game.screens.PlayScreen;

/**
 * Created by mike on 9/28/15.
 */
public abstract class TileObject {

    protected World world;
    protected TiledMap map;
    protected Rectangle bounds;
    protected Body body;
    protected PlayScreen screen;
    protected MapObject object;

    protected Fixture fixture;

    public TileObject(World world, TiledMap map){

    }

    public TileObject() {
    }

    public TileObject(Body body) {
        fixture = body.getFixtureList().get(0);
    }

    public abstract void onHit();

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell((int)(body.getPosition().x * MarioBros.PPM / 16),
                (int)(body.getPosition().y * MarioBros.PPM / 16));
    }


}
