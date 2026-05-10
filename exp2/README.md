# Intent Demo App (exp2)

A simple Android application demonstrating the usage of **Implicit Intents** and **Explicit Intents** in Java.

---

## 🚀 How to Run
1. **Import:** Open Android Studio, select `File > Open`, and choose the `exp2` folder.
2. **Sync:** Wait for Gradle to finish syncing.
3. **Deploy:** Connect a device or start an emulator and click the **Run** button (Shift + F10).

---

## 🛠 Step-by-Step Implementation Guide

If you are building this from scratch, follow these steps and place the code in the respective files:

### 1. Android Manifest
**File Path:** `app/src/main/AndroidManifest.xml`
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Exp2">

        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <activity
            android:name=".SecondActivity"
            android:exported="false" />

    </application>

</manifest>
```

### 2. Layouts (XML)

#### Main Screen Layout
**File Path:** `app/src/main/res/layout/activity_main.xml`
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center"
    android:padding="20dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Intent Demo App"
        android:textSize="22sp"
        android:textStyle="bold"
        android:layout_marginBottom="40dp"/>

    <Button
        android:id="@+id/btnImplicit"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Open Website (Implicit Intent)"
        android:layout_marginBottom="20dp"/>

    <Button
        android:id="@+id/btnExplicit"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Go to Second Screen (Explicit Intent)"/>

</LinearLayout>
```

#### Second Screen Layout
**File Path:** `app/src/main/res/layout/activity_second.xml`
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center"
    android:padding="20dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="This is Second Activity"
        android:textSize="22sp"
        android:textStyle="bold"
        android:layout_marginBottom="40dp"/>

    <Button
        android:id="@+id/btnBack"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Go Back"/>

</LinearLayout>
```

### 3. Java Classes

#### MainActivity.java
**File Path:** `app/src/main/java/com/example/exp2/MainActivity.java`
```java
package com.example.exp2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnImplicit, btnExplicit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnImplicit = findViewById(R.id.btnImplicit);
        btnExplicit = findViewById(R.id.btnExplicit);

        // Implicit Intent: Opens Browser
        btnImplicit.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.google.com"));
            startActivity(intent);
        });

        // Explicit Intent: Opens SecondActivity
        btnExplicit.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });
    }
}
```

#### SecondActivity.java
**File Path:** `app/src/main/java/com/example/exp2/SecondActivity.java`
```java
package com.example.exp2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());
    }
}
```

---

## 📦 Dependencies
Ensure your `app/build.gradle.kts` includes:
```kotlin
dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
}
```
*(Using Version Catalog/libs.versions.toml)*
