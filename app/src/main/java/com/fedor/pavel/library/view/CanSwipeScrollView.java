package com.fedor.pavel.library.view;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class CanSwipeScrollView extends android.widget.ScrollView {

    private GestureDetectorCompat gestureDetectorCompat;

    public CanSwipeScrollView(Context context) {
        super(context);
    }

    public CanSwipeScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanSwipeScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setGestureListener(GestureDetectorCompat gestureDetectorCompat) {

        this.gestureDetectorCompat = gestureDetectorCompat;

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        boolean flag = super.dispatchTouchEvent(ev);

        if (gestureDetectorCompat != null) {

            return gestureDetectorCompat.onTouchEvent(ev)||super.dispatchTouchEvent(ev);

        } else {
            return flag;
        }
    }
}
