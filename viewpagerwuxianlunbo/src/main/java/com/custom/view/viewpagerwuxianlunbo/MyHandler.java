package com.custom.view.viewpagerwuxianlunbo;


import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;

public class MyHandler extends Handler {
    private ViewPager vPager;
    private long duration;
    public static final int what = 0;
    public MyHandler(ViewPager vPager,long duration) {
        this.vPager = vPager;
        this.duration = duration;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        vPager.setCurrentItem(vPager.getCurrentItem() + 1);
        send(duration);
    }

    public void send(long duration){
        sendEmptyMessageDelayed(what, duration);
    }
}
