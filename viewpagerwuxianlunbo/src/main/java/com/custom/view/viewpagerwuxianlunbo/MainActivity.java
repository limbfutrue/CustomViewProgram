package com.custom.view.viewpagerwuxianlunbo;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mVpPage;
    private List<ImageView> imageViewList = new ArrayList<>();
    private int[] resIdList = {R.mipmap.lunbo1, R.mipmap.lunbo2, R.mipmap.lunbo3, R.mipmap.lunbo4};
    private long duration = 3000;
    private MyHandler handler;
    private ViewPagerTools vpTools;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVpPage = (ViewPager) findViewById(R.id.vp_page);
        handler = new MyHandler(mVpPage,duration);
        ImageView imageView;
        for (int i = 0; i < resIdList.length; i++) {
            imageView = new ImageView(this);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 180);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(resIdList[i]);
            imageView.setLayoutParams(params);
            imageViewList.add(imageView);
        }
        MyViewPageAdapter adapter = new MyViewPageAdapter(imageViewList);
        mVpPage.setAdapter(adapter);
        vpTools = new ViewPagerTools(mVpPage,handler);
        vpTools.initCurrentItem(imageViewList);
        handler.send(duration);
        vpTools.setViewPagerListener(duration);
    }
}
