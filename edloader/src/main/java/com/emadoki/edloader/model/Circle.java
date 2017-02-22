package com.emadoki.edloader.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.emadoki.edloader.Vector2;

public class Circle
{
    public Vector2 position;
    public Paint paint;
    public float radius;

    public Circle(float x, float y, float radius)
    {
        position = new Vector2(x, y);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        this.radius = radius;
    }

    public void render(Canvas canvas)
    {
        canvas.drawCircle(position.x, position.y, radius, paint);
    }

    /**
     * Set Circle color
     * @param color
     */
    public void setColor(int color)
    {
        this.paint.setColor(color);
    }

    @Override
    public String toString()
    {
        return "x: " + position.x + " y: " + position.y + " r: "+ radius;
    }
}