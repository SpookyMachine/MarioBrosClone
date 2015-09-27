package com.mariogame.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mariogame.game.MarioBros;

/**
 * Created by mike on 9/27/15.
 */
public class HudScene {

    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label countDownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label marioLabel;

    public HudScene(SpriteBatch spriteBatch){
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countDownLabel = new Label(String.format("%03d", worldTimer),new Label.LabelStyle(new BitmapFont(), Color.WHITE) );
        scoreLabel = new Label(String.format("%06d", score),new Label.LabelStyle(new BitmapFont(), Color.WHITE) );
        timeLabel = new Label("Time",new Label.LabelStyle(new BitmapFont(), Color.WHITE) );
        levelLabel = new Label("1-1",new Label.LabelStyle(new BitmapFont(), Color.WHITE) );
        worldLabel = new Label("WORLD",new Label.LabelStyle(new BitmapFont(), Color.WHITE) );
        marioLabel = new Label("MARIO",new Label.LabelStyle(new BitmapFont(), Color.WHITE) );

        table.add(marioLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        //----------
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countDownLabel).expandX();

        stage.addActor(table);

    }
}
