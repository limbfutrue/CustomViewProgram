package com.custom.view.youkucaidan;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class AnimalUtils {

    public void hiddenView(View view,long duration){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"rotation",0,180);
        animator.setDuration(duration);
        view.setPivotX(view.getWidth()/2);
        view.setPivotY(view.getHeight());
        animator.start();

    }

    public void showView(View view,long duration){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"rotation",180,360);
        animator.setDuration(duration);
        view.setPivotX(view.getWidth()/2);
        view.setPivotY(view.getHeight());
        animator.start();
    }
}
