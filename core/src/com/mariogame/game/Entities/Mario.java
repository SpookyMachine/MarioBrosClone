package com.mariogame.game.Entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.values.RectangleSpawnShapeValue;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mariogame.game.MarioBros;
import com.mariogame.game.enums.MarioState;

import java.util.ArrayList;

/**
 * Created by mike on 9/27/15.
 */
public class Mario extends Sprite {
    // Default Stuff
    public World world;
    public Body body;

    //States
    private MarioState currentState;
    private MarioState previousState;

    //Drawing stuff
    private boolean turnedRight;
    private TextureRegion littleMario;
    private Animation marioRun;
    private Animation marioJump;
    private float stateTimer;


    public Mario(World world, TextureAtlas atlas){
        //Init Stuff
        super(atlas.findRegion("little_mario"));
        this.world = world;

        currentState = MarioState.STANDING;
        previousState = MarioState.STANDING;
        stateTimer = 0f;
        turnedRight = true;

        //Defining texture regions and Animations
        loadLittleMarioTexture();
        loadLittleMarioRunningAnimation();
        loadLittleMarioJumpAnimation();

        //byDefaultSetMarioLitlle >.>
        setRegion(littleMario);

        //Defining Box2D object
        defineMario();






    }

    private void loadLittleMarioJumpAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 4; i < 6; i++)
            frames.add(new TextureRegion(getTexture(), i * 16, 0, 16, 16));
        marioJump = new Animation(0.1f, frames);
        frames.clear();
    }

    private void loadLittleMarioRunningAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 1; i < 4; i++)
            frames.add(new TextureRegion(getTexture(), i * 16, 0, 16, 16));
        marioRun = new Animation(0.1f, frames);
        frames.clear();
    }

    private void loadLittleMarioTexture() {
        littleMario = new TextureRegion(getTexture(), 0, 0, 16, 16);
        setBounds(0, 0, 16 / MarioBros.PPM, 16 / MarioBros.PPM);
    }

    public void update(float delta){
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(delta));
    }

    private TextureRegion getFrame(float delta) {
        currentState = getState();
        TextureRegion region;

        switch (currentState){
            case JUMPING:
                region = marioJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = marioRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = littleMario;
                break;
        }

        if((body.getLinearVelocity().x < 0 || !turnedRight) && !region.isFlipX()){
            region.flip(true, false);
            turnedRight = false;
        }else  if((body.getLinearVelocity().x > 0 || turnedRight) && region.isFlipX()){
            region.flip(true, false);
            turnedRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;
        return region;
    }

    public MarioState getState(){
        if(body.getLinearVelocity().y > 0 || (body.getLinearVelocity().y < 0 && previousState == MarioState.JUMPING))
            return MarioState.JUMPING;
        if (body.getLinearVelocity().y < 0)
            return MarioState.FALLING;
        if (body.getLinearVelocity().x != 0)
            return MarioState.RUNNING;

        return MarioState.STANDING;
    }

    private void defineMario() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / MarioBros.PPM, 32 / MarioBros.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((littleMario.getRegionWidth() / 2) / MarioBros.PPM, littleMario.getRegionHeight() / 2  / MarioBros.PPM );

        fdef.shape = shape;
        body.createFixture(fdef).setUserData("mario");

        EdgeShape edge = new EdgeShape();
        edge.set(new Vector2(-2 / MarioBros.PPM, 6/ MarioBros.PPM), new Vector2(2 / MarioBros.PPM, 6/ MarioBros.PPM));
        fdef.shape = edge;
        fdef.isSensor = true;

        body.createFixture(fdef).setUserData("head");

    }
}
