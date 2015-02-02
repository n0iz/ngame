package com.nGame.utils.ng;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.nGame.utils.ng.input.InputEventManager;
import com.nGame.utils.ng.input.InputResponceEvent;
import com.nGame.utils.ng.input.XboxController;

/**
 * Created by oli on 17.04.2014.
 */
public abstract class ScreenNG implements Screen {
    public final InputEventManager ieManager;
    protected String TAG = ScreenNG.class.getSimpleName();
    public Stage stage;



    protected ScreenNG() {
        //stage=new Stage();
        stage = new Stage(new ExtendViewport(Conf.Video.WIDTH, Conf.Video.HEIGHT));

        ieManager = new InputEventManager();


        ieManager.addInputResponseEvent(new InputResponceEvent("back") {
            @Override
            public void onAction(float value) {
                GameNG.I.switchToPreviousScreen();
            }
        });

        try {
            ieManager.bindGamePadButton(0,XboxController.BACK,"back");
            ieManager.bindKeyDown(Input.Keys.ESCAPE, "back");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected abstract void update(float delta);


    @Override
    public void render(float delta) {


        // Set the viewport to the whole screen.
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Draw anywhere on the screen.


        update(delta);
        stage.act(delta);
        stage.draw();
        //Table.drawDebug(stage);
        // Restore the stage's viewport.
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        if(!GameNG.I.ongoingTransition) {
            Log.d("show activate input");
            stage.addListener(ieManager);
            Gdx.input.setInputProcessor(stage);
            ieManager.activateGamePad();
        } else {
            Log.d("input not activated");
        }
    }

    @Override
    public void hide() {
        Log.d("hide");
        ieManager.deactivateGamePad();
        stage.removeListener(ieManager);
        Gdx.input.setInputProcessor(null);

    }

    @Override
    public void pause() {
        Gdx.app.debug(TAG, "pause");
    }

    @Override
    public void resume() {
        Gdx.app.debug(TAG, "resume");
    }

    @Override
    public void dispose() {
        Gdx.app.debug(TAG, "close game");
        stage.dispose();
    }

    protected void resetScreen(){

        stage.addAction(Actions.parallel(Actions.fadeIn(0), Actions.moveTo(0, 0), Actions.color(Color.WHITE)));
    }


}
