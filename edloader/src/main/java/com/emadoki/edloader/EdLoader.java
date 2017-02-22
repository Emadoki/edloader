package com.emadoki.edloader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.emadoki.edloader.type.BaseType;
import com.emadoki.edloader.type.Classic;
import com.emadoki.edloader.type.Grow;
import com.emadoki.edloader.type.Jump;
import com.emadoki.edloader.type.Pacman;
import com.emadoki.edloader.type.Swap;

public class EdLoader extends View
{
    private Builder builder;
    private BaseType model;
    private LoaderType type;

    public EdLoader(Context context)
    {
        super(context);
        init(context, null);
    }

    public EdLoader(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }

    public EdLoader(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs)
    {
        builder = new Builder();
        type = LoaderType.CLASSIC;
        builder.radius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, context.getResources().getDisplayMetrics());
        builder.margin = builder.radius;
        builder.amount = 3;
        builder.speed = 1f;
        builder.color = Color.WHITE;

        if (attrs != null)
        {
            TypedArray set = context.getTheme()
                    .obtainStyledAttributes(attrs, R.styleable.EdLoader, 0, 0);

            if (set != null)
            {
                type = LoaderType.fromId(set.getInt(R.styleable.EdLoader_loader_type, 0));
                builder.radius = set.getDimension(R.styleable.EdLoader_loader_radius, builder.radius);
                builder.margin = set.getDimension(R.styleable.EdLoader_loader_margin, builder.margin);
                builder.amount = set.getInteger(R.styleable.EdLoader_loader_amount, builder.amount);
                builder.amount = builder.amount >= 2 ? builder.amount : 3;
                builder.speed = set.getFloat(R.styleable.EdLoader_loader_speed, builder.speed);
                builder.speed = builder.speed >= 10 ? 10: builder.speed;
                builder.color = set.getColor(R.styleable.EdLoader_loader_color, builder.color);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);
        build();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (model != null)
            model.render(canvas);
    }

    private void build()
    {
        switch (type){
            case CLASSIC:
                model = new Classic();
                break;
            case GROW:
                model = new Grow();
                break;
            case SWAP:
                model = new Swap();
                break;
            case JUMP:
                model = new Jump();
                break;
            case PACMAN:
                model = new Pacman();
                break;
        }

        model.setup(builder);
        model.setListener(new InvalidateListener());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        // dimeter + (margin top + margin bottom) or (margin left + margin right)
        float one_circle_size = (builder.radius * 2) + (builder.margin * 2);
        // total circles x size of each circle
        float calculated_width = builder.amount * one_circle_size;
        // for now just keep it the diamter + margin
        float calculated_height = one_circle_size * 2;
        int desiredWidth = (int) calculated_width;
        int desiredHeight = (int) calculated_height;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY)
        {
            //Must be this size
            width = widthSize;
        }
        else if (widthMode == MeasureSpec.AT_MOST)
        {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        }
        else
        {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY)
        {
            //Must be this size
            height = heightSize;
        }
        else if (heightMode == MeasureSpec.AT_MOST)
        {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        }
        else
        {
            //Be whatever you want
            height = desiredHeight;
        }

        builder.width = width;
        builder.height = height;
        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

    public static class Builder
    {
        public float width;
        public float height;
        public float radius;
        public float margin;
        public float speed;
        public int amount;
        public int color;
    }

    /**
     * Listening for animator event callback from listener,
     * to invalidate the canvas
     */
    private class InvalidateListener implements OnInvalidateListener
    {
        @Override
        public void onInvalidate()
        {
            invalidate();
        }
    }
}
