package com.jackyliu.osusimulation.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.jackyliu.osusimulation.event.NormalButtonEvent;

import static com.jackyliu.osusimulation.UIUtils.dpToPx;

public class ApproachingCircle extends View {
    private static final int BORDER_WIDTH_DP = 3;
    //    private static final int INNER_RADIUS_DP = 15;
    private Context context;
    private int borderWidth;

    public void setNormalButtonEvent(NormalButtonEvent normalButtonEvent) {
        this.normalButtonEvent = normalButtonEvent;
    }

    private NormalButtonEvent normalButtonEvent;

    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            normalButtonEvent.finishEvent();
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    public void setRadius(int radius) {
        this.radius = radius;
        invalidate();
    }

    private int radius;
    private Paint paint = new Paint();
    private boolean isPrepared = false;

    public int getRadiusMaxExpand() {
        return radiusMaxExpand;
    }


    private int radiusMaxExpand = 100;


    public void setMinRadius(int minRadius) {
        this.minRadius = minRadius;
    }

    private int minRadius;

    public ApproachingCircle(Context context) {
        super(context);
        this.context = context;
    }

    public ApproachingCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

    }

    public ApproachingCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

    }


    private void init() {
        if (!isPrepared) {
            //初始化繪圖參數
            borderWidth = dpToPx(context, BORDER_WIDTH_DP);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(borderWidth);

            isPrepared = true;
        }
    }

    public void setColor(int color){
        paint.setColor(color);
    }

    public void setAlpha(int alpha){
        paint.setAlpha(alpha);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
        canvas.drawCircle((float) getWidth() / 2, (float) getHeight() / 2, (float) radius, paint);
    }

    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }

    public void startAnimation(){
        ObjectAnimator anim = ObjectAnimator.ofInt(this,"radius",minRadius+radiusMaxExpand, minRadius);
        anim.setDuration(1000);
        anim.addListener(animatorListener);
        anim.start();
    }
}
