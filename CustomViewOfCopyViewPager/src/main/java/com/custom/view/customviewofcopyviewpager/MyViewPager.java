package com.custom.view.customviewofcopyviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * @author Z
 * 仿viewpager
 */
public class MyViewPager extends ViewGroup {

    private GestureDetector detector;
    /**
     * 当前页面下标
     */
    private int currentIndex;

    private float startX;
    /**
     * 处理滑动回弹效果
     */
    private Scroller scroller;

    private OnPageChangeListener listener;

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        scroller = new Scroller(context);
        //实例化手势识别器
        detector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            /**
             * 滑动
             * @param e1
             * @param e2
             * @param distanceX 在x轴滑动的距离
             * @param distanceY 在y轴滑动的距离
             * @return
             */
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                scrollBy((int) distanceX, getScrollY());
                return true;
            }
        });
    }

    /**
     * @param changed
     * @param l       子View的左上角x位置
     * @param t       子View的左上角y位置
     * @param r       子View的右下角x位置
     * @param b       子View的右下角y位置
     *                <p>
     *                子View(l,t)左上角坐标
     *                子View(r,b)右下角坐标
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //遍历孩子，指定每个孩子的位置
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        //传递触摸事件给手势识别器
        detector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                float endX = event.getX();
                int tempIndex = currentIndex;
                if ((startX - endX) > getWidth() / 2) {
                    //下一个界面
                    tempIndex++;
                } else if ((endX - startX) > getWidth() / 2) {
                    //上一个界面
                    tempIndex--;
                }
                scrollToPage(tempIndex);
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 根据位置指定相应页面
     *
     * @param tempIndex
     */
    private void scrollToPage(int tempIndex) {
        if (tempIndex < 0) {
            tempIndex = 0;
        }
        if (tempIndex > getChildCount() - 1) {
            tempIndex = getChildCount() - 1;
        }
        //页面改变监听
        if (listener!=null && currentIndex != tempIndex){
            listener.onPageChange(tempIndex);
        }
        //当前页面下标
        currentIndex = tempIndex;
        int distanceX = currentIndex * getWidth() - getScrollX();
        scroller.startScroll(getScrollX(), getScrollY(), distanceX, getScrollY());
        invalidate();//会促使onDraw computeScroll方法执行
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            //获取一小段对应的坐标
            float currX = scroller.getCurrX();
            scrollTo((int) currX, getScrollY());
            invalidate();
        }
    }

    public void setOnPageChangeListener(OnPageChangeListener listener) {
        this.listener = listener;
    }

    public interface OnPageChangeListener{
        /**
         * 页面改变监听
         * @param position
         */
        void onPageChange(int position);
    }
}
