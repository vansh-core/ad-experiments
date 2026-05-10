# Exp4 - GridView Android Application

This is a simple Android application that demonstrates how to use a `GridView` to display a list of items (courses) in a 2D grid layout. It includes handling item clicks using a `Toast`.

---

## 🛠 Project Setup Guide

Follow these steps to set up and run the project in Android Studio.

### 1. Project Structure
Ensure your files are placed in the following directories:

- **Java Code**: `app/src/main/java/com/example/exp4/MainActivity.java`
- **Layout XML**: `app/src/main/res/layout/activity_main.xml`
- **Manifest**: `app/src/main/AndroidManifest.xml`
- **Themes**: `app/src/main/res/values/themes.xml` (and `values-night/themes.xml`)
- **Build Configuration**: `app/build.gradle`

---

### 2. Dependencies
Add the following dependencies to your `app/build.gradle` file:

```gradle
dependencies {
    implementation 'androidx.appcompat:appcompat:1.7.1'
    implementation 'com.google.android.material:material:1.13.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.2.0'
}
```

---

### 3. Layout File (`activity_main.xml`)
Create a file at `app/src/main/res/layout/activity_main.xml` and paste the following:

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="10dp">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Home Page - Grid Layout"
        android:textSize="22sp"
        android:textStyle="bold"
        android:gravity="center"
        android:padding="15dp"
        android:background="#3F51B5"
        android:textColor="#FFFFFF"
        android:layout_marginBottom="10dp"/>

    <GridView
        android:id="@+id/gridView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:numColumns="2"
        android:horizontalSpacing="10dp"
        android:verticalSpacing="10dp"
        android:stretchMode="columnWidth"
        android:gravity="center"
        android:padding="5dp"/>

</LinearLayout>
```

---

### 4. Java File (`MainActivity.java`)
Create a file at `app/src/main/java/com/example/exp4/MainActivity.java` and paste the following:

```java
package com.example.exp4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] courses = {
            "Android Dev", "Web Design", "Python", "Java",
            "Machine Learning", "Data Science", "Cloud Computing",
            "Networking", "Cybersecurity", "IoT", "VLSI Design", "Optical Comm"
    };

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                courses
        );

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String clickedItem = courses[position];
                Toast.makeText(MainActivity.this,
                        "You clicked: " + clickedItem,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
```

---

### 5. Manifest Configuration (`AndroidManifest.xml`)
Update your `app/src/main/AndroidManifest.xml` to declare the activity:

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Exp4">
        
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

### 6. Theme Setup
To remove the default Action Bar (so the "exp4" label is hidden), update `res/values/themes.xml`:

```xml
<style name="Theme.Exp4" parent="Theme.MaterialComponents.DayNight.NoActionBar">
    <!-- Customize your theme here -->
</style>
```

---

## 🚀 How to Run
1. Open **Android Studio**.
2. Create a new project with an **Empty Views Activity** (or use your existing one).
3. Copy and paste the files according to the structure above.
4. Click **Sync Project with Gradle Files**.
5. Connect an Android device or start an emulator.
6. Click the **Run** button (Green Play Icon).
