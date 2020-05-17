package com.example.software_engineeringproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ChessboxView extends View {
    private Paint mPointPaint;
    private float[] mFPts;
    private RectF mRectF;
    private RectF mRectOvalF;
    private RectF mRightBottomRectF;
    private Path mPath;
    private Path mPath1;

    private  int mPointStrokeWidth;

    public ChessboxView(Context context) {
        super(context);
        init();
    }

    public ChessboxView(Context context, @Nullable AttributeSet attrs){
        super(context,attrs);
        init();
    }

    public ChessboxView(Context context,@Nullable AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
        init();
    }

    private void init(){
        mPointStrokeWidth=20;
        mPointPaint=new Paint();
        mPointPaint.setColor(Color.BLACK);
        mPointPaint.setStrokeWidth(mPointStrokeWidth);
        mPointPaint.setStyle(Paint.Style.FILL);

        mPath=new Path();
        mPath1=new Path();
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawCircle(100,100,50,mPointPaint);
    }
}
