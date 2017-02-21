package com.emadoki.edloader.type;

import android.graphics.Canvas;

import com.emadoki.edloader.EdLoader;
import com.emadoki.edloader.OnInvalidateListener;
import com.emadoki.edloader.model.Circle;

public abstract class BaseType
{
    protected Circle[] circles;
    protected float width;
    protected float height;
    protected float radius;
    protected int amount;
    protected int color;
    protected float circles_width;
    protected float progress;
    protected OnInvalidateListener listener;

    public void setListener(OnInvalidateListener listener)
    {
        this.listener = listener;
    }

    public void setup(EdLoader edLoader)
    {
        this.width = edLoader.getWidth();
        this.height = edLoader.getHeight();
        this.radius = edLoader.radius;
        this.amount = edLoader.amount;
        this.color = edLoader.color;

        // generate circles
        circles = new Circle[amount];
        float x = 0;
        float y = height / 2;
        // create circles
        for (int i = 0; i < circles.length; i++)
        {
            x += radius;
            circles[i] = new Circle(x, y, radius);
            circles[i].setColor(color);
            float margin_right = radius;
            // calculate next circle position
            x = circles[i].position.x + circles[i].radius + margin_right;
            // get the total size for center_horizontal adjustment
            if (i == circles.length - 1)
                circles_width = circles[i].position.x + circles[i].radius;
        }

        // adjust to center_horizontal
        for (int i = 0; i < circles.length; i++)
        {
            float offset = (width / 2) - (circles_width / 2);
            circles[i].position.x += offset;
        }

        setup();
    }

    public abstract void setup();
    public abstract void render(Canvas canvas);
}
