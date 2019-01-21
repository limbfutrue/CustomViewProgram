package com.custom.view.customviewofcopyviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private MyViewPager viewPager;
    private int[] resIdList = {R.mipmap.lunbo1, R.mipmap.lunbo2, R.mipmap.lunbo3, R.mipmap.lunbo4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.myViewPager);

        for (int i = 0; i < resIdList.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(resIdList[i]);
            viewPager.addView(imageView);
        }

        viewPager.setOnPageChangeListener(new MyViewPager.OnPageChangeListener() {
            @Override
            public void onPageChange(int position) {
                Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
