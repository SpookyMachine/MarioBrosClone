package com.mariogame.game.Entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mariogame.game.MarioBros;

/**
 * Created by mike on 9/28/15.
 */
public class Coin extends TileObject {

    public Coin(Body body) {
        super(body);
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.COIN_BIT);
    }


    @Override
    public void onHit() {
        Gdx.app.log("Coin", "Coing got hit");
    }


}
