package com.lv.http.worldclock.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class QuickIndexBar extends View {

    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"
    };

    private Paint paint;
    private int mHeight;
    private int cellWidth;
    private float cellHeight;

    public QuickIndexBar(Context context) {
        this(context, null);
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.rgb(225,149,0));
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextAlign(Paint.Align.CENTER);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < LETTERS.length; i++) {
            String letter = LETTERS[i];

            float x = cellWidth / 2.0f;
            Rect bounds = new Rect();
            paint.getTextBounds(letter, 0, letter.length(), bounds);
            int textHeight = bounds.height();
            float y = cellHeight / 2.0f + textHeight / 2.0f + i * cellHeight;
            canvas.drawText(letter, x, y, paint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        cellWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        cellHeight = mHeight * 1.0f / LETTERS.length;
    }

    int currentIndex = -1;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                calcCurrentLetter(event);
                break;
            case MotionEvent.ACTION_MOVE:
                calcCurrentLetter(event);
                break;
            case MotionEvent.ACTION_UP:
                currentIndex = -1;
                break;
        }

        return true;
    }


    private void calcCurrentLetter(MotionEvent event) {
        int index;
        index = (int) (event.getY() / cellHeight);
        if(index >= 0 && index < LETTERS.length){
            if(index != currentIndex){
//                System.out.println("index: " + index + " " + LETTERS[index]);
                String letter = LETTERS[index];

                if(onLetterUpdateListener != null){
                    onLetterUpdateListener.onLetterUpdate(letter);
                }
                currentIndex =  index;
            }
        }
    }

    OnLetterUpdateListener onLetterUpdateListener;
    public void setOnLetterUpdateListener(OnLetterUpdateListener onLetterUpdateListener) {
        this.onLetterUpdateListener = onLetterUpdateListener;
    }

    public interface OnLetterUpdateListener {
        void onLetterUpdate(String letter);
    }

}
