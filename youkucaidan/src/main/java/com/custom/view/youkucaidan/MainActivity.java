package com.custom.view.youkucaidan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout mRlTopCircle;
    private RelativeLayout mRlMiddleCircle;
    private RelativeLayout mRlHomeCircle;
    private ImageView mIvTopCircle;
    private AnimalUtils animalUtils;
    private boolean topIsShow = true;
    private boolean middleIsShow = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animalUtils = new AnimalUtils();
        mIvTopCircle = (ImageView) findViewById(R.id.iv_top_circle);
        mRlTopCircle = (RelativeLayout) findViewById(R.id.rl_top_circle);
        mRlMiddleCircle = (RelativeLayout) findViewById(R.id.rl_middle_circle);
        mRlHomeCircle = (RelativeLayout) findViewById(R.id.rl_home_circle);

        mRlHomeCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (middleIsShow){
                    animalUtils.hiddenView(mRlMiddleCircle,1000);
                    middleIsShow = false;
                }else {
                    animalUtils.showView(mRlMiddleCircle,1000);
                    middleIsShow = true;
                }

                if (topIsShow){
                    animalUtils.hiddenView(mRlTopCircle,500);
                    topIsShow = false;
                }

            }
        });

        mRlMiddleCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topIsShow){
                    animalUtils.hiddenView(mRlTopCircle,1000);
                    topIsShow = false;
                }else {
                    animalUtils.showView(mRlTopCircle,1000);
                    topIsShow = true;
                }
            }
        });
    }
}
