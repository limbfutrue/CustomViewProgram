package com.custom.view.viewpagerwuxianlunbo;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * @author Z
 */
public class MyViewPageAdapter extends PagerAdapter{
    private List<ImageView> imageViewList;
    public MyViewPageAdapter(List<ImageView> imageViewList) {
        this.imageViewList = imageViewList;
    }

    @Override
    public int getCount() {
        //返回Integer最大值大概有2亿多，可以一直滑下去
        return imageViewList!=null?Integer.MAX_VALUE:0;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //防止position不符合数据集合真实长度，所以取imageViewList的模，得到imageViewList循环下标
        int realPos = position%imageViewList.size();
        container.addView(imageViewList.get(realPos));
        return imageViewList.get(realPos);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        int realPos = position%imageViewList.size();
        container.removeView(imageViewList.get(realPos));
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
