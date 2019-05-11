package com.jackyliu.osusimulation.event;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jackyliu.osusimulation.GameActivity;
import com.jackyliu.osusimulation.animation.MatchCircleAnimation;
import com.jackyliu.osusimulation.interactiveView.NormalButton;

public class NormalButtonEvent {
    private LinearLayout gameScreen;
    private NormalButton normalButton;
    private MatchCircleAnimation matchCircleAnimation;
    private int normalButtonWidth = 200;

    private int color;

    public NormalButtonEvent(LinearLayout gameScreen, GameActivity gameActivity) {
        this.gameScreen = gameScreen;
        normalButton = new NormalButton(gameActivity);
        normalButton.setNormalButtonEvent(this);
        normalButton.setLayoutParams(new LinearLayout.LayoutParams(normalButtonWidth, normalButtonWidth));
        normalButton.setVisibility(View.GONE);
        matchCircleAnimation = new MatchCircleAnimation(gameActivity);
        matchCircleAnimation.setNormalButtonEvent(this);
        matchCircleAnimation.setLayoutParams(
                new LinearLayout.LayoutParams(normalButtonWidth + matchCircleAnimation.getRadiusMaxExpand() * 2,
                        normalButtonWidth + matchCircleAnimation.getRadiusMaxExpand() * 2));
        matchCircleAnimation.setMinRadius(normalButtonWidth/2);
        matchCircleAnimation.setVisibility(View.GONE);

        //todo 之後加入緩存後 下面這兩行或許該拿掉
        gameScreen.addView(normalButton);
        gameScreen.addView(matchCircleAnimation);
    }

    /**
     * 設定光圈圓心的座標
     *
     * @param x
     * @param y
     */
    public void setPosition(int x, int y) {
        normalButton.setPosition(300, 300);

//        y -= normalButtonWidth ;

        matchCircleAnimation.setPosition(300,
                300);
    }

    public void startEvent() {
        color = Color.argb(127,
                (int) (Math.random() * 200),
                (int) (Math.random() * 200),
                (int) (Math.random() * 200));
        normalButton.setColor(color);
        matchCircleAnimation.setColor(color);
        matchCircleAnimation.setAlpha(255);
        normalButton.setVisibility(View.VISIBLE);
        matchCircleAnimation.setVisibility(View.VISIBLE);
        matchCircleAnimation.startAnimation();
    }

    /**
     * 結束此事件 可能是使用者沒有點到 或使用者點到了
     */
    public void finishEvent() {
        normalButton.setVisibility(View.GONE);
        matchCircleAnimation.setVisibility(View.GONE);
        //todo 下面或許還需做處理 比方說緩存事件之類的
    }
}
