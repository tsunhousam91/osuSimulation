package com.jackyliu.osusimulation;

import android.widget.LinearLayout;

import com.jackyliu.osusimulation.event.NormalButtonEvent;

public class GameManager {
    public static GameManager getInstance() {
        return instance;
    }

    private static GameManager instance = new GameManager();

    private GameActivity gameActivity;

    public void init(GameActivity gameActivity){
        this.gameActivity = gameActivity;
    }

    public void trigerEvent(GameActivity.TrigerEvent event, int x, int y){
        switch (event){
            case NORMAL_BUTTON_EVENT:

                //先試著從緩存尋找有沒有 已經new 過還沒回收的 物件 有就拿來重複利用

                //如果沒有 就要產生一個新的 包裝物件
                NormalButtonEvent normalButtonEvent = new NormalButtonEvent(gameActivity.getGameScreen(), gameActivity);

                //todo test
                normalButtonEvent.setPosition(x,y);
                normalButtonEvent.startEvent();

                //todo 把 產生出來的加進緩存

                break;
        }
    }
}
