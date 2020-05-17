package com.example.software_engineeringproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
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
    private Canvas canvas;

    private int mPointStrokeWidth;
    private int x;
    private int y;

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
        mPointStrokeWidth=4;
        mPointPaint=new Paint();
        mPointPaint.setStrokeWidth(mPointStrokeWidth);
        mPointPaint.setStyle(Paint.Style.FILL);
        mPath=new Path();
        mPath1=new Path();
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        mPointPaint.setColor(Color.BLACK);
        //中心点(480,480)
        canvas.drawCircle(480,480,10,mPointPaint);
        canvas.drawLine(0,0,880,880,mPointPaint);
        for(int i=0;i<=10;i++) {
            canvas.drawLine(80 + 80 * i, 80, 80 + 80 * i, 880, mPointPaint);
            canvas.drawLine(80, 80 + 80 * i, 880, 80 + 80 * i, mPointPaint);
        }
        this.canvas=canvas;
        drawChessPiece(0,0);
    }

    public void drawChessPiece(int x,int y){
        mPointPaint.setColor(Color.BLUE);
        this.canvas.drawCircle(80+80*x,80+80*y,40,mPointPaint);
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                float downX=event.getRawX();
                float downY=event.getRawY();
                this.x=(int)downX/80;
                this.y=(int)(downY-900)/80;
                String s="";
                s="("+String.valueOf(downX)+","+String.valueOf(downY)+")";
                Log.e("(downX,downY)=", s);
                s="("+String.valueOf(this.x)+","+String.valueOf(this.y)+")";
                Log.e("(x,y)=", s);
                //drawChessPiece(this.x,this.y);
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }
}
