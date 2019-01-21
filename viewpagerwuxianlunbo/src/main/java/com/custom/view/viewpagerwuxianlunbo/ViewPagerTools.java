package com.custom.view.viewpagerwuxianlunbo;

import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

public class ViewPagerTools {
    private ViewPager viewPager;
    private MyHandler handler;
    private boolean isDragging = false;

    public ViewPagerTools(ViewPager viewPager,MyHandler handler) {
        this.viewPager = viewPager;
        this.handler = handler;
    }

    /**
     * 设置总长度的中心位置
     * 保证左右都可以无限滑动
     * @param imageViewList
     */
    public void initCurrentItem(List<ImageView> imageViewList){
        //获取总长度的中心坐标
        int startIndex = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageViewList.size();
        //设置item
        viewPager.setCurrentItem(startIndex);
    }

    /**
     * 设置viewpager触摸监听和滑动监听
     * 当点击viewpager页面的时候取消消息延时发送
     * 当手离开的时候重新发送延时消息
     * 处理 页面拖拽状态下  会自动播放的问题，当拖拽的时候取消延时发送，释放状态重新发送延时消息
     * @param duration
     */
    public void setViewPagerListener(final long duration){
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.removeCallbacksAndMessages(null);
                        handler.send(duration);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        //处理拖拽状态下  自动播放的问题
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (i == ViewPager.SCROLL_STATE_DRAGGING) {
                    //拖拽状态,取消消息发送
                    isDragging = true;
                    handler.removeCallbacksAndMessages(null);

                } else if (i == ViewPager.SCROLL_STATE_SETTLING) {

                }
                if (i == ViewPager.SCROLL_STATE_IDLE && isDragging) {
                    //释放状态，重新发起
                    isDragging = false;
                    handler.removeCallbacksAndMessages(null);
                    handler.send(duration);
                }
            }
        });
    }
}
