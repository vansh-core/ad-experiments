# Automatic Image Slider Application

This is a simple Android experiment designed for college projects. It features an image slider that automatically transitions between high-quality internet images every 3 seconds.

---

## 🚀 Project Setup Guide

Follow these steps to set up the project in Android Studio.

### 1. Project Dependencies & Versions
You need to add the **Glide** library to load images from the internet.

#### **File:** `gradle/libs.versions.toml`
Add these lines under `[versions]` and `[libraries]`:
```toml
[versions]
# ... other versions
glide = "4.16.0"

[libraries]
# ... other libraries
glide = { group = "com.github.bumptech.glide", name = "glide", version.ref = "glide" }
```

#### **File:** `app/build.gradle.kts`
Add the glide implementation in the `dependencies` block:
```kotlin
dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.glide) // Add this line
    // ...
}
```
**Important:** After editing these files, click **"Sync Now"** in the top right of Android Studio.

---

### 2. Android Manifest Configuration
To load images from the internet, you must grant internet permission.

#### **File:** `app/src/main/AndroidManifest.xml`
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <!-- 1. Add Internet Permission -->
    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:allowBackup="true"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/Theme.Automatic_image_slider">

        <!-- 2. Ensure MainActivity is the Launcher -->
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHer" />
            </intent-filter>
        </activity>

    </application>
</manifest>
```

---

### 3. Resource Files (Strings & Themes)

#### **File:** `app/src/main/res/values/strings.xml`
```xml
<resources>
    <string name="app_name">Automatic Image Slider</string>
    <string name="app_title">My Image Slider</string>
    <string name="slider_image">Slider Image</string>
</resources>
```

#### **File:** `app/src/main/res/values/themes.xml`
Change the parent theme to `NoActionBar` to remove the default top bar.
```xml
<style name="Theme.Automatic_image_slider" parent="Theme.MaterialComponents.DayNight.NoActionBar">
    <!-- ... -->
</style>
```

---

### 4. Layout Files

#### **File:** `app/src/main/res/layout/activity_main.xml`
This file contains the Title and the `ViewPager2` (the sliding container).
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/titleTextView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/app_title"
        android:textSize="24sp"
        android:textStyle="bold"
        android:layout_marginTop="32dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        app:layout_constraintTop_toBottomOf="@id/titleTextView"
        app:layout_constraintBottom_toBottomOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

#### **File:** `app/src/main/res/layout/slider_item.xml`
This file defines how each single image looks.
```xml
<?xml version="1.0" encoding="utf-8"?>
<ImageView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/imageView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:scaleType="centerCrop"
    android:contentDescription="@string/slider_image" />
```

---

### 5. Java Activity Files
Place these files in: `app/src/main/java/com/example/automatic_image_slider/`

#### **File:** `ImageAdapter.java`
Handles loading images into the slider.
```java
package com.example.automatic_image_slider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private List<String> imageUrls;

    public ImageAdapter(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(imageUrls.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() { return imageUrls.size(); }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
```

#### **File:** `MainActivity.java`
Controls the logic and the automatic timer.
```java
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
            if (currentItem >= imageList.size()) currentItem = 0;
            viewPager.setCurrentItem(currentItem, true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        imageList = new ArrayList<>();
        
        // Add image URLs here
        imageList.add("https://images.unsplash.com/photo-1506744038136-46273834b3fb?w=800");
        imageList.add("https://images.unsplash.com/photo-1469474968028-56623f02e42e?w=800");
        imageList.add("https://images.unsplash.com/photo-1447752875215-b2761acb3c5d?w=800");

        viewPager.setAdapter(new ImageAdapter(imageList));
        handler = new Handler(Looper.getMainLooper());

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(sliderRunnable);
                handler.postDelayed(sliderRunnable, 3000); // 3 seconds delay
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
```

---

## 🛠️ How to Run
1.  Open **Android Studio**.
2.  Paste the code into the respective files as shown above.
3.  Click **"Sync Project with Gradle Files"**.
4.  Connect an Android device or emulator.
5.  Click the **Run (Green Play button)**.
6.  Ensure your device has an active **Internet connection** to load the images.
