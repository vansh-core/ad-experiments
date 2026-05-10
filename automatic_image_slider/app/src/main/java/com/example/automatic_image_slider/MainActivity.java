package com.example.automatic_image_slider;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private Handler handler;
    private List<String> imageList;
    private final Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            int currentItem = viewPager.getCurrentItem();
            currentItem++;
            if (currentItem >= imageList.size()) {
                currentItem = 0;
            }
            viewPager.setCurrentItem(currentItem, true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);

        imageList = new ArrayList<>();
        imageList.add("https://images.unsplash.com/photo-1506744038136-46273834b3fb?w=800");
        imageList.add("https://images.unsplash.com/photo-1469474968028-56623f02e42e?w=800");
        imageList.add("https://images.unsplash.com/photo-1447752875215-b2761acb3c5d?w=800");

        ImageAdapter adapter = new ImageAdapter(imageList);
        viewPager.setAdapter(adapter);

        handler = new Handler(Looper.getMainLooper());

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(sliderRunnable);
                handler.postDelayed(sliderRunnable, 3000);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(sliderRunnable, 3000);
    }
}