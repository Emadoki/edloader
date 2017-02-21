package com.emadoki.edloader.type;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.emadoki.edloader.model.Circle;
import com.emadoki.edloader.model.Pac;

public class Pacman extends BaseType
{
    private ValueAnimator animator;
    private Pac pac;

    @Override
    public void setup()
    {
        pac = new Pac(circles[0].position.x - radius, height / 2, radius * 2);
        pac.setColor(color);

        animator = ValueAnimator.ofInt(0, 180);
        animator.setDuration(2200);
        animator.setInterpolator(null);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                progress = (Integer) animation.getAnimatedValue();
                if (listener != null)
                    listener.onInvalidate();
            }
        });
        animator.start();
    }

    @Override
    public void render(Canvas canvas)
    {
        pac.moveX((circles_width + (pac.rect.width() * 2)) * (progress / 180));

        // create a fake progress to animate the mouth biting faster
        float dp = progress;
        while (dp >= 45)
        {
            dp -= 45;
        }
        // x4 speed of actual speed
        dp = dp * 4;
        pac.render(canvas, dp);

        for (int i = 0; i < circles.length; i++)
        {
            // if touched pacman then dont draw
            if (pac.position.x > circles[i].position.x)
            {
                continue;
            }
            // else we draw the circle
            circles[i].render(canvas);
        }
    }
}
