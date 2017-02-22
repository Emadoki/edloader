package com.emadoki.edloader.type;

import android.animation.ValueAnimator;
import android.graphics.Canvas;

import com.emadoki.edloader.model.Pac;

public class Pacman extends BaseType
{
    private ValueAnimator animator;
    private Pac pac;

    @Override
    public void setup()
    {
        float x = circles[0].position.x - builder.radius - builder.margin;
        float pac_radius = builder.radius * 2;
        pac = new Pac(x - pac_radius, builder.height / 2, pac_radius);
        pac.setColor(builder.color);

        animator = ValueAnimator.ofInt(0, 180);
        animator.setDuration((long) (2200 * builder.speed));
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
        float width = pac.rect.width() + circles_width + pac.rect.width();
        pac.moveX(-pac.rect.width() + (width * (progress / 180)));

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
