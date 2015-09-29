package com.mariogame.game.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.mariogame.game.Entities.Brick;
import com.mariogame.game.Entities.Mario;
import com.mariogame.game.Entities.TileObject;

/**
 * Created by mike on 9/28/15.
 */
public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() == "head" || fixB.getUserData() == "head"){
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if(object.getUserData() instanceof TileObject){
                ((TileObject) object.getUserData()).onHit();
            }
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
