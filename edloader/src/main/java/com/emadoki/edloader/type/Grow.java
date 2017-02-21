package com.emadoki.edloader.type;

import android.animation.ValueAnimator;
import android.graphics.Canvas;

public class Grow extends BaseType
{
    private ValueAnimator animator;
    private int DELAY = 60;

    @Override
    public void setup()
    {
        int degree = 360 + (amount * DELAY);
        animator = ValueAnimator.ofInt(0, degree);
        animator.setDuration(1400 / 360 * degree);
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
        // if 0.5f and above the circle will scale and overlap the other circles
        // because the margin right is only radius * 0.5f
        // so we keep it below 0.5f
        float MAX_RADIUS = radius * 0.3f;

        for (int i = 0; i < circles.length; i++)
        {
            // start delay
            float delay = i * DELAY;
            float dr = circles[i].radius;
            // if progress pass the start delay time then process
            if (progress >= delay)
            {
                // get the actual progress eg: angle 5° = progress 45° - delay 40°
                float angle = progress - delay;
                // actual progress hit 360 then stop animating
                // by setting the animation at its end value
                if (angle >= 360)
                    angle = 360;

                // get radians for cos/sin calculation
                double rad = Math.toRadians(angle);
                circles[i].radius = (float) (radius + (MAX_RADIUS * Math.sin(rad)));
            }
            // draw it
            circles[i].render(canvas);
        }
    }
}
