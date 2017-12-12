package me.blip.pokeymans;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Pokeymans extends ApplicationAdapter
{
    SpriteBatch batch;
    Texture img;

    private Stage stage;

    @Override
    public void create()
    {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");

        stage = new Stage(new FitViewport(800,450));
        Gdx.input.setInputProcessor(stage);

        new Battle(stage);
    }

    @Override
    public void resize(int width,int height)
    {
        stage.getViewport().update(width,height,true);
    }
    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void dispose()
    {
        batch.dispose();
        img.dispose();
    }
}
