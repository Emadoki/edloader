package com.emadoki.edloader.type;

import android.graphics.Canvas;

import com.emadoki.edloader.OnInvalidateListener;
import com.emadoki.edloader.model.Circle;
import static com.emadoki.edloader.EdLoader.Builder;

public abstract class BaseType
{
    protected Builder builder;
    protected Circle[] circles;
    protected float circles_width;
    protected float progress;
    protected OnInvalidateListener listener;

    public void setListener(OnInvalidateListener listener)
    {
        this.listener = listener;
    }

    public void setup(Builder builder)
    {
        this.builder = builder;

        // generate circles
        circles = new Circle[builder.amount];
        float x = 0;
        float y = builder.height / 2;
        for (int i = 0; i < circles.length; i++)
        {
            // left margin and left radius
            x += builder.margin + builder.radius;
            // set at center point
            circles[i] = new Circle(x, y, builder.radius);
            // add right radius and right margin
            x += builder.radius + builder.margin;
        }

        circles_width = x;
        for (int i = 0; i < circles.length; i++)
        {
            float offset = (builder.width / 2) - (circles_width / 2);
            circles[i].position.x += offset;
        }

        setup();
    }

    public abstract void setup();
    public abstract void render(Canvas canvas);
}
