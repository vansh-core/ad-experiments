# Exp8 - Image Slider Application (Internet Images)

This project demonstrates how to create a smooth, swipeable image slider in Android using `ViewPager` and the `Glide` library to fetch and display images directly from URLs.

## Project Structure

```text
exp8/
├── app/
│   ├── build.gradle.kts       # App-level build configuration
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml # Permissions and Activity declarations
│           ├── java/com/example/exp8/
│           │   ├── MainActivity.java # Sets up the UI
│           │   └── ImageAdapter.java # Logic for loading images
│           └── res/
│               └── layout/
│                   └── activity_main.xml # UI Layout with ViewPager
├── gradle/
│   └── libs.versions.toml      # Dependency version management
```

---

## 1. Setup Dependencies

### `gradle/libs.versions.toml`
We use the **Version Catalog** to manage Glide. Add the following lines to your `libs.versions.toml` file:

```toml
[versions]
glide = "4.16.0"

[libraries]
glide = { group = "com.github.bumptech.glide", name = "glide", version.ref = "glide" }
```

### `app/build.gradle.kts`
Apply the Glide library to your application module:

```kotlin
dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.glide) // Injected from libs.versions.toml
}
```

---

## 2. Internet Permission

### `app/src/main/AndroidManifest.xml`
Since we are loading images from the web, the `INTERNET` permission is **mandatory**. Add it above the `<application>` tag:

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <uses-permission android:name="android.permission.INTERNET" />
    
    <application
        android:allowBackup="true"
        android:label="@string/app_name"
        android:theme="@style/Theme.Exp8">
        
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```

---

## 3. Layout XML

### `app/src/main/res/layout/activity_main.xml`
The `ViewPager` is a layout manager that allows the user to flip left and right through pages of data.

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/tvTitle"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Internet Image Slider"
        android:textSize="20sp"
        android:textStyle="bold"
        android:gravity="center"
        android:padding="14dp"
        android:background="#3F51B5"
        android:textColor="#FFFFFF"/>

    <androidx.viewpager.widget.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@id/tvTitle"/>

</RelativeLayout>
```

---

## 4. Java Files

### `app/src/main/java/com/example/exp8/ImageAdapter.java`
The `ImageAdapter` extends `PagerAdapter`. It acts as a bridge between the list of image URLs and the `ViewPager`. 
- **`instantiateItem`**: This is where the magic happens. We create an `ImageView` for each page and use **Glide** to load the URL into it asynchronously.
- **`destroyItem`**: Removes the view when it's no longer needed to save memory.

```java
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

    // List of internet URLs to display
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
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        
        // Glide handles network requests, caching, and bitmap scaling automatically
        Glide.with(mContext)
                .load(sliderImageUrl[position])
                .into(imageView);

        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
```

### `app/src/main/java/com/example/exp8/MainActivity.java`
Connects the `ViewPager` from the layout to the `ImageAdapter`.

```java
package com.example.exp8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        imageAdapter = new ImageAdapter(this);
        
        // Link the adapter to the ViewPager
        viewPager.setAdapter(imageAdapter);
    }
}
```

---

## Summary of Logic
1.  **Dependency**: We use Glide because manually downloading and caching internet images is complex and prone to memory leaks.
2.  **Adapter**: The `ViewPager` asks the `ImageAdapter` for a view at a specific position. The adapter provides an `ImageView` and tells Glide to fill it.
3.  **Permissions**: Without `INTERNET` permission, Glide will fail to fetch the images, and you will see a blank screen or error placeholders.

## How to Run
1. Open the project in **Android Studio**.
2. **Sync Gradle**: Click "Sync Now" to download the Glide library.
3. **Internet**: Ensure your test device or emulator has an active internet connection.
4. **Run**: Click the green "Run" icon. You should be able to swipe between 5 different high-resolution images.
