package com.mariogame.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mariogame.game.MarioBros;

import java.awt.*;

/**
 * Created by mike on 9/28/15.
 */
public class Brick extends TileObject {
    public Brick(Body body) {
        super(body);
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.BRICK_BIT);
    }

    @Override
    public void onHit() {
        Gdx.app.log("Brick", "Brick got hit");
        setCategoryFilter(MarioBros.DESTROYED_BIT);
    }


}
