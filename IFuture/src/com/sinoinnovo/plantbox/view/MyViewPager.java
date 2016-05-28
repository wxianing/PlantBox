package com.sinoinnovo.plantbox.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/5/10 0010.
 * 测量ViewPager的高度
 */
public class MyViewPager extends ViewPager {

    private float xLast,yLast;

    public boolean onInterceptTouchEvent(MotionEvent ev){
        return true;
    }
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
    public boolean dispatchTouchEvent(MotionEvent ev){
        boolean isTouch=false;
        float xDistance ,yDistance;
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                xDistance=yDistance=0f;
                xLast=ev.getX();
                yLast=ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                float curX=ev.getX();
                float curY=ev.getY();
                xDistance=Math.abs(curX-xLast);
                yDistance=Math.abs(curY-yLast);
                float t=xDistance-yDistance;
                if(xDistance>60.00){
                    isTouch=true;
                }
                else
                {
                    isTouch=false;
                }
                break;
        }
        setTemp(isTouch);

        return true;
    }
    boolean temp = false;
    public boolean isTemp() {
        return temp;
    }
    public void setTemp(boolean temp) {
        this.temp = temp;
    }

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height)
                height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
