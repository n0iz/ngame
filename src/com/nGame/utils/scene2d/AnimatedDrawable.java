package com.nGame.utils.scene2d;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author oli
 *         Date: 23.11.13 - 16:23
 */
public class AnimatedDrawable extends BaseDrawable {
    private Animation animation;
    private float stateTime = 0;

    public AnimatedDrawable(Animation ani) {
        this.animation = ani;
        setMinWidth(ani.getKeyFrame(0).getRegionWidth());
        setMinHeight(ani.getKeyFrame(0).getRegionHeight());
    }


    public void update(float delta) {
        stateTime += delta;
    }

    public void reset() {
        stateTime = 0;
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        batch.draw(animation.getKeyFrame(stateTime), x, y, width, height);
    }
}
