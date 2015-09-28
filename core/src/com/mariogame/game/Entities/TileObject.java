package com.mariogame.game.Entities;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import java.awt.*;

/**
 * Created by mike on 9/28/15.
 */
public abstract class TileObject {

    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle rec;
    protected Body body;

    public TileObject(World world, TiledMap map, Rectangle rec) {
        this.world = world;
        this.map = map;
        this.rec = rec;



    }
}
