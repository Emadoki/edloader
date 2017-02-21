package com.emadoki.edloader.type;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.animation.AccelerateInterpolator;

import com.emadoki.edloader.model.Circle;

public class Jump extends BaseType
{
    private ValueAnimator animator;
    private float[] centers;

    @Override
    public void setup()
    {
        animator = ValueAnimator.ofInt(0, 180);
        animator.setDuration(1200);
        animator.setInterpolator(new AccelerateInterpolator());
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
        animator.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationStart(Animator animation)
            {
                // get every center point between two circles
                centers = new float[amount - 1];
                for (int i = 0; i < centers.length; i++)
                {
                    float center = (circles[i].position.x + circles[i + 1].position.x) / 2;
                    centers[i] = center;
                }
            }

            @Override
            public void onAnimationEnd(Animator animation)
            {
                Circle firstcircle = circles[0];
                // shift all circles to index - 1 position in list
                for (int i = 1; i < circles.length; i++)
                {
                    Circle temp = circles[i];
                    circles[i - 1] = temp;
                }
                // place the first circle in last index
                circles[amount - 1] = firstcircle;
            }
        });

        animator.start();
    }

    @Override
    public void render(Canvas canvas)
    {
        // take away one circle, because index 0 jump to last index
        float jump_distance = circles_width - (radius * 2);
        // make first circle leap over
        double rad = Math.toRadians(180 + progress);
        float dx = (float) (jump_distance / 2.0 * Math.cos(rad) + (width / 2.0));
        float dy = (float) (radius * 2.0 * Math.sin(rad) + (height / 2.0));
        // jump
        circles[0].position.set(dx, dy);
        circles[0].render(canvas);

        // move the rest of the circles
        for (int i = 1; i < circles.length; i++)
        {
            rad = Math.toRadians(progress);
            circles[i].position.x = (float) (radius * 1.5 * Math.cos(rad) + centers[i - 1]);
            circles[i].render(canvas);
        }
    }
}
