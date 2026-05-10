package com.example.exp8;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;

public class ImageAdapter extends PagerAdapter {

    Context mContext;

    public ImageAdapter(Context context) {
        this.mContext = context;
    }

    // Array of image URLs from the internet
    private String[] sliderImageUrl = new String[]{
            "https://picsum.photos/id/10/800/400",
            "https://picsum.photos/id/20/800/400",
            "https://picsum.photos/id/30/800/400",
            "https://picsum.photos/id/40/800/400",
            "https://picsum.photos/id/50/800/400"
    };

    @Override
    public int getCount() {
        return sliderImageUrl.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (ImageView) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        
        // Load image using Glide
        Glide.with(mContext)
                .load(sliderImageUrl[position])
                .placeholder(android.R.drawable.progress_horizontal) // optional placeholder
                .error(android.R.drawable.stat_notify_error)       // optional error image
                .into(imageView);

        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
