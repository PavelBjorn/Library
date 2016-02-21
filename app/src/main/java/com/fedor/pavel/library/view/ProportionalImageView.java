package com.fedor.pavel.library.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;


public class ProportionalImageView extends ImageView {

    public static float radius = 20.0f;


    public ProportionalImageView(Context context) {

        super(context);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB && Build.VERSION.SDK_INT <=Build.VERSION_CODES.KITKAT) {

            setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        }else if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.KITKAT) {

            setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
    }

    public ProportionalImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB && Build.VERSION.SDK_INT <=Build.VERSION_CODES.KITKAT) {

            setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        }else if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.KITKAT) {

            setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
    }

    public ProportionalImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB && Build.VERSION.SDK_INT <=Build.VERSION_CODES.KITKAT) {

            setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        }else if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.KITKAT) {

            setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(getMeasuredWidth(), (int) (getMeasuredWidth() / 0.7));

    }

    @Override
    protected void onDraw(Canvas canvas) {


        Path clipPath = new Path();

        RectF rect = new RectF(0, 0, this.getWidth(), this.getHeight());

        clipPath.addRoundRect(rect, radius, radius, Path.Direction.CW);

        canvas.clipPath(clipPath);

        super.onDraw(canvas);
    }
}
