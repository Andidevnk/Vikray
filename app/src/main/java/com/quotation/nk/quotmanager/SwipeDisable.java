package com.quotation.nk.quotmanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class SwipeDisable extends ViewPager {
    private boolean enabled;
    public SwipeDisable(@NonNull Context context, AttributeSet attr) {
        super(context,attr);
        this.enabled=true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (this.enabled) {
            return super.onTouchEvent(ev);
        }
        return false;

    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }
    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

