package com.azhyun.massxj.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

public class RoundBackgroundColorSpan extends ReplacementSpan {
    private int bgColor;
    private int textColor;
    public RoundBackgroundColorSpan(int bgColor, int textColor) {
        super();
        this.bgColor = bgColor;
        this.textColor = textColor;
    }
    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return ((int)paint.measureText(text, start, end)+30);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        int color1 = paint.getColor();
        paint.setColor(this.bgColor);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(new RectF(x, top, x + ((int) paint.measureText(text, start, end)+20), bottom), 10, 10, paint);
        paint.setColor(this.textColor);
        canvas.drawText(text, start, end, x+10, y, paint);
//        paint.setColor(color1);
    }
}
