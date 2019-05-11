package com.jackyliu.osusimulation.interactiveView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.jackyliu.osusimulation.event.NormalButtonEvent;

import static com.jackyliu.osusimulation.UIUtils.dpToPx;

public class NormalButton extends View {
    private static final int BORDER_WIDTH_DP = 3;
//    private static final int INNER_RADIUS_DP = 15;
    private Context context;
    private int borderWidth;
    private int innerRadius;
    private Paint innerPaint = new Paint();
    private Paint borderPaint = new Paint();
    private boolean isPrepared = false;

    public void setNormalButtonEvent(NormalButtonEvent normalButtonEvent) {
        this.normalButtonEvent = normalButtonEvent;
    }

    private NormalButtonEvent normalButtonEvent;

    public NormalButton(Context context) {
        super(context);
        this.context = context;

//        init();
    }

    public NormalButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
//        init();
    }

    public NormalButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
//        init();
    }

    private void init() {
        if(!isPrepared){
            //初始化繪圖參數
            borderWidth = dpToPx(context, BORDER_WIDTH_DP);
//        innerRadius = dpToPx(context, INNER_RADIUS_DP);
            innerRadius = getWidth() / 2 - borderWidth;
            innerPaint.setAntiAlias(true);
            borderPaint.setColor(Color.WHITE);
            borderPaint.setAntiAlias(true);

            //初始化點擊事件
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "你點到按鈕啦!!!", Toast.LENGTH_SHORT).show();
                }
            });
            isPrepared = true;
        }

    }

    public void setColor(int color){
        innerPaint.setColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();

        canvas.drawCircle((float) getWidth()/2, (float)getHeight()/2, (float)getWidth()/2, borderPaint);
        canvas.drawCircle((float) getWidth()/2, (float)getHeight()/2, (float)innerRadius, innerPaint);

    }

    public void setPosition(int x, int y){
        setX(x);
        setY(y);
    }
}
