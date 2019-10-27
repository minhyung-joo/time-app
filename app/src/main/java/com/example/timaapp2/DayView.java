package com.example.timaapp2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class DayView extends View {
    private final Resources r = getResources();

    private final int gridLineColor = Color.argb(200, 0, 0, 0);
    private Paint gridLinePaint;

    private int numberOfGrids = 20;
    private int gridHeight = 40;
    private int gridLeftOffset = 40;

    public DayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        gridLinePaint = new Paint();
        gridLinePaint.setColor(gridLineColor);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 1; i <= numberOfGrids; i++) {
            float y = dipToPixels(gridHeight * i);
            canvas.drawLine(dipToPixels(gridLeftOffset), y, canvas.getClipBounds().width(), y, gridLinePaint);
            canvas.drawText("Timestamp", 0, y, gridLinePaint);
        }

        canvas.drawLine(
                dipToPixels(gridLeftOffset + 2),
                dipToPixels(gridHeight - 2),
                dipToPixels(gridLeftOffset + 2),
                canvas.getClipBounds().height(), gridLinePaint
        );
    }

    private float dipToPixels(float dip) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
    }
}
