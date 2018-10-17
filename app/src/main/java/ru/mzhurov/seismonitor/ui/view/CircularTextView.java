package ru.mzhurov.seismonitor.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class CircularTextView extends AppCompatTextView {

    private float strokeWidth;
    private int strokeColor;
    private int solidColor;

    public CircularTextView(Context context) {
        super(context);
    }

    public CircularTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        final Paint circlePaint = new Paint();
        circlePaint.setColor(solidColor);
        circlePaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        final Paint strokePaint = new Paint();
        strokePaint.setColor(strokeColor);
        strokePaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        final int h = this.getHeight();
        final int w = this.getWidth();

        final int diameter = ((h > w) ? h : w);
        final int radius = diameter / 2;

        this.setHeight(diameter);
        this.setWidth(diameter);

        canvas.drawCircle(diameter / 2, diameter / 2, radius, strokePaint);

        canvas.drawCircle(diameter / 2, diameter / 2, radius - strokeWidth, circlePaint);

        super.draw(canvas);
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    @Override
    public int getSolidColor() {
        return solidColor;
    }

    public void setSolidColor(int solidColor) {
        this.solidColor = solidColor;
    }
}
