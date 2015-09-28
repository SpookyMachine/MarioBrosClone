package com.mariogame.game.Entities;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mariogame.game.utils.Box2DCreationUtils;

import java.awt.*;

/**
 * Created by mike on 9/28/15.
 */
public class Coin extends TileObject {

    public Coin(World world, TiledMap map, Rectangle rec) {
        super(world, map, rec);
    }
}
