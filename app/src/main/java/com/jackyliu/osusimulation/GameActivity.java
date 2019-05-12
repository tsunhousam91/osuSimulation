package com.jackyliu.osusimulation;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.jackyliu.osusimulation.interactiveView.NormalButton;

public class GameActivity extends AppCompatActivity {

    public enum TrigerEvent{
        NORMAL_BUTTON_EVENT
    }

    public FrameLayout getGameScreen() {
        return gameScreen;
    }

    private FrameLayout gameScreen;
    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        findViews();
        GameManager.getInstance().init(this);

        mainThreadHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GameManager.getInstance().trigerEvent(TrigerEvent.NORMAL_BUTTON_EVENT, 300, 300);
                mainThreadHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        GameManager.getInstance().trigerEvent(TrigerEvent.NORMAL_BUTTON_EVENT, 600, 400);
                        mainThreadHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                GameManager.getInstance().trigerEvent(TrigerEvent.NORMAL_BUTTON_EVENT, 200, 200);
                            }
                        },5000);
                    }
                },5000);
            }
        },8000);

//        mainThreadHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                myTestNormalButton.setPosition(100,100);
//                mainThreadHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        myTestNormalButton.setPosition(200,200);
//                        mainThreadHandler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                myTestNormalButton.setPosition(300,300);
//                                mainThreadHandler.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        myTestNormalButton.setPosition(400,100);
//                                    }
//                                },5000);
//                            }
//                        },5000);
//                    }
//                },5000);
//            }
//        },5000);
    }

    private void findViews(){
        gameScreen = findViewById(R.id.game_screen);
    }



}
