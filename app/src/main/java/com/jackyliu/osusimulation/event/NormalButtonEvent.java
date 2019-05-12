package com.jackyliu.osusimulation.event;

import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;

import com.jackyliu.osusimulation.GameActivity;
import com.jackyliu.osusimulation.animation.ApproachingCircle;
import com.jackyliu.osusimulation.interactiveView.NormalButton;

import java.io.File;

public class NormalButtonEvent {
    private FrameLayout gameScreen;
    private NormalButton normalButton;
    private ApproachingCircle approachingCircle;
    private int normalButtonWidth = 200;

    private int color;

    public NormalButtonEvent(FrameLayout gameScreen, GameActivity gameActivity) {
        this.gameScreen = gameScreen;
        normalButton = new NormalButton(gameActivity);
        normalButton.setNormalButtonEvent(this);
        normalButton.setLayoutParams(new FrameLayout.LayoutParams(normalButtonWidth, normalButtonWidth));
        normalButton.setVisibility(View.GONE);
        approachingCircle = new ApproachingCircle(gameActivity);
        approachingCircle.setNormalButtonEvent(this);
        approachingCircle.setLayoutParams(
                new FrameLayout.LayoutParams(normalButtonWidth + approachingCircle.getRadiusMaxExpand() * 2,
                        normalButtonWidth + approachingCircle.getRadiusMaxExpand() * 2));
        approachingCircle.setMinRadius(normalButtonWidth/2);
        approachingCircle.setVisibility(View.GONE);


        //todo 之後加入緩存後 下面這兩行或許該拿掉
        gameScreen.addView(normalButton);
        gameScreen.addView(approachingCircle);
    }

    /**
     * 設定光圈圓心的座標
     *
     * @param x
     * @param y
     */
    public void setPosition(int x, int y) {
        normalButton.setPosition(x - normalButtonWidth/2, y -  normalButtonWidth/2);

//        y -= normalButtonWidth ;

        approachingCircle.setPosition(x -  normalButtonWidth/2 - approachingCircle.getRadiusMaxExpand(),
                y -  normalButtonWidth/2 - approachingCircle.getRadiusMaxExpand());
    }

    public void startEvent() {
        color = Color.argb(127,
                (int) (Math.random() * 200),
                (int) (Math.random() * 200),
                (int) (Math.random() * 200));
        normalButton.setColor(color);
        approachingCircle.setColor(color);
        approachingCircle.setAlpha(255);
        normalButton.setVisibility(View.VISIBLE);
        approachingCircle.setVisibility(View.VISIBLE);
        approachingCircle.startAnimation();
    }

    /**
     * 結束此事件 可能是使用者沒有點到 或使用者點到了
     */
    public void finishEvent() {
        normalButton.setVisibility(View.GONE);
        approachingCircle.setVisibility(View.GONE);
        //todo 下面或許還需做處理 比方說緩存事件之類的
    }
}
