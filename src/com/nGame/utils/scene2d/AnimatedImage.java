package com.nGame.utils.scene2d;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created with IntelliJ IDEA.
 *
 * @author oli
 *         Date: 23.11.13 - 16:09
 */
public class AnimatedImage extends Image {
    private AnimatedDrawable drawable;

    public AnimatedImage(AnimatedDrawable drawable) {
        super(drawable);
        this.drawable = drawable;
    }

    @Override
    public void act(float delta) {
        drawable.update(delta);
        super.act(delta);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
