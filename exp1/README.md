# Exp1 - Android Project

A simple Android application project with a "Hello World" main activity.

## 🚀 Setup Guide

Follow these steps to set up the project in Android Studio.

### 1. Project Structure
Create the following file structure in your project:

```text
exp1/
├── app/
│   ├── build.gradle.kts
│   └── src/
│       └── main/
│           ├── java/com/example/exp1/MainActivity.java
│           ├── res/
│           │   ├── layout/activity_main.xml
│           │   └── values/
│           │       ├── colors.xml
│           │       └── themes.xml
│           └── AndroidManifest.xml
├── gradle/
│   └── libs.versions.toml
└── build.gradle.kts (Root)
```

### 2. Dependencies & Build Configuration
In your `app/build.gradle.kts`, ensure you have the following dependencies:

```kotlin
dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)
}
```

Make sure your `gradle/libs.versions.toml` contains:
```toml
[versions]
appcompat = "1.7.1"
material = "1.13.0"

[libraries]
appcompat = { group = "androidx.appcompat", name = "appcompat", version.ref = "appcompat" }
material = { group = "com.google.android.material", name = "material", version.ref = "material" }
```

### 3. AndroidManifest Configuration
Path: `app/src/main/AndroidManifest.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <application
        android:allowBackup="true"
        android:supportsRtl="true"
        android:theme="@style/Theme.Exp1" >
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

### 4. Layout XML
Path: `app/src/main/res/layout/activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        android:layout_centerInParent="true"/>
</RelativeLayout>
```

### 5. Activity File
Path: `app/src/main/java/com/example/exp1/MainActivity.java`

```java
package com.example.exp1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
```

### 6. Resource Files (Themes & Colors)
Path: `app/src/main/res/values/colors.xml`
```xml
<resources>
    <color name="purple_500">#FF6200EE</color>
    <color name="purple_700">#FF3700B3</color>
    <color name="teal_200">#FF03DAC5</color>
    <color name="white">#FFFFFFFF</color>
    <color name="black">#FF000000</color>
</resources>
```

Path: `app/src/main/res/values/themes.xml`
```xml
<resources>
    <style name="Theme.Exp1" parent="Theme.MaterialComponents.NoActionBar">
        <item name="colorPrimary">@color/purple_500</item>
        <item name="colorPrimaryVariant">@color/purple_700</item>
        <item name="colorOnPrimary">@color/white</item>
    </style>
</resources>
```

## ▶️ How to Run
1.  **Sync Project with Gradle Files**: Open Android Studio and click "Sync Now" in the top bar.
2.  **Select Device**: Choose an emulator or a physical device from the device dropdown.
3.  **Run**: Press the green "Run" button (Shift + F10).
