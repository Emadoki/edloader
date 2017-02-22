package com.emadoki.edloader.type;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.view.animation.AccelerateInterpolator;

import com.emadoki.edloader.model.Circle;

public class Swap extends BaseType
{
    private ValueAnimator animator;
    private float distance;
    private float center_x;
    private int index;
    private boolean isLeapOver;

    @Override
    public void setup()
    {
        if (circles.length < 2)
            return;

        index = 0;
        isLeapOver = true;
        animator = ValueAnimator.ofInt(0, 180);
        animator.setDuration((long) ((260 * builder.amount) * builder.speed));
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
                Circle c1 = circles[index];
                Circle c2 = circles[index + 1];
                center_x = (c1.position.x + c2.position.x) / 2;
                distance = builder.radius + builder.margin;
            }

            @Override
            public void onAnimationRepeat(Animator animation)
            {
                // rearrange the position of circles in the list
                Circle temp = circles[index];
                circles[index] = circles[index + 1];
                circles[index + 1] = temp;
                // increment for next animation
                index = (index + 1) == builder.amount - 1 ? 0 : index + 1;
                // get the calculations for next animation
                Circle c1 = circles[index];
                Circle c2 = circles[index + 1];
                // get center of x for rotation
                center_x = (c1.position.x + c2.position.x) / 2;
                distance = builder.radius + builder.margin;
                // adjust y to original position, cause animation sometimes offset by 1 or 2 pixels
                for (Circle circle: circles)
                    circle.position.y = builder.height / 2;
                isLeapOver = !isLeapOver;
            }
        });

        animator.start();
    }

    @Override
    public void render(Canvas canvas)
    {
        if (circles.length < 2)
            return;

        if (isLeapOver)
        {
            // make first circle leap over
            double rad = Math.toRadians(180 + progress);
            float dx = (float) (distance * Math.cos(rad) + center_x);
            float dy = (float) (distance * Math.sin(rad) + (builder.height / 2.0));
            circles[index].position.set(dx, dy);
        }
        else
        {
            // make first circle leap over
            double rad = Math.toRadians(180 + progress);
            float dx = (float) (distance * Math.cos(rad) + center_x);
            rad = Math.toRadians(progress);
            float dy = (float) (distance * Math.sin(rad) + (builder.height / 2.0));
            circles[index].position.set(dx, dy);
        }

        // translate second circle to left
        double rad = Math.toRadians(progress);
        circles[index + 1].position.x = (float) (distance * Math.cos(rad) + center_x);

        for (Circle circle: circles)
            circle.render(canvas);
    }
}
