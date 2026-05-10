# Experiment 7: Fragment Lifecycle Demo

This project demonstrates the Android Fragment lifecycle by hosting a Fragment within an Activity and logging each lifecycle stage to Logcat.

## Project Setup Guide

Follow these steps to set up and run the project in Android Studio.

### 1. Project Structure
Ensure your project structure matches the following (using the package name `com.example.exp7`):

```text
app/
├── src/main/
│   ├── java/com/example/exp7/
│   │   ├── MainActivity.java
│   │   └── MyFragment.java
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml
│   │   │   └── fragment_my.xml
│   │   └── values/
│   │       └── themes.xml
│   └── AndroidManifest.xml
└── build.gradle.kts (Module: app)
```

---

### 2. Dependencies
Add the following to your `app/build.gradle.kts` file:

```kotlin
dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.fragment:fragment:1.6.1") // Required for Fragments
}
```

---

### 3. Layout Files

#### **File:** `res/layout/activity_main.xml`
This layout contains a `FrameLayout` which serves as the container for the fragment.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Activity with Fragment Lifecycle"
        android:textSize="18sp"
        android:textStyle="bold"
        android:gravity="center"
        android:padding="15dp"
        android:background="#3F51B5"
        android:textColor="#FFFFFF"/>

    <FrameLayout
        android:id="@+id/fragment_container"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#F5F5F5"/>

</LinearLayout>
```

#### **File:** `res/layout/fragment_my.xml`
The UI for the fragment itself.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center"
    android:padding="24dp"
    android:background="#FFFFFF">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="My Fragment"
        android:textSize="26sp"
        android:textStyle="bold"
        android:textColor="#3F51B5"
        android:layout_marginBottom="20dp"/>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="This Fragment is hosted inside MainActivity.\nCheck Logcat to see lifecycle methods!"
        android:textSize="16sp"
        android:gravity="center"
        android:textColor="#555555"
        android:layout_marginBottom="30dp"/>

    <Button
        android:id="@+id/btnShow"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Show Lifecycle Info"/>

</LinearLayout>
```

---

### 4. Java Files

#### **File:** `java/com/example/exp7/MyFragment.java`
Handles the Fragment lifecycle events and logging.

```java
package com.example.exp7;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    private static final String TAG = "FragmentDemo";
    Button btnShow;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "1. onAttach() → Fragment attached to Activity");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "2. onCreate() → Fragment initializing");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "3. onCreateView() → Fragment UI created");
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        btnShow = view.findViewById(R.id.btnShow);
        btnShow.setOnClickListener(v -> Toast.makeText(getActivity(),
                "Fragment Active! Check Logcat for lifecycle!",
                Toast.LENGTH_LONG).show());

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "5. onStart() → Fragment is VISIBLE");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "6. onResume() → Fragment ACTIVE");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "7. onPause() → Fragment PAUSED");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "8. onStop() → Fragment STOPPED");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "9. onDestroyView() → Fragment View DESTROYED");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "11. onDetach() → Fragment DETACHED from Activity");
    }
}
```

#### **File:** `java/com/example/exp7/MainActivity.java`
The host activity that loads the fragment.

```java
package com.example.exp7;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new MyFragment())
                    .commit();
        }
    }
}
```

---

### 5. Configuration Files

#### **File:** `AndroidManifest.xml`
Ensure the activity is declared and the theme is set.

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Exp7">
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

#### **File:** `res/values/themes.xml` (Removing the Top Banner)
To remove the top bar (Action Bar), use the `NoActionBar` parent.

```xml
<resources>
    <style name="Theme.Exp7" parent="Theme.MaterialComponents.DayNight.NoActionBar">
        <!-- Customize your theme here -->
        <item name="colorPrimary">@color/purple_500</item>
        <item name="android:statusBarColor">?attr/colorPrimaryVariant</item>
    </style>
</resources>
```

---

### 6. How to Run
1. Open **Logcat** in Android Studio (usually at the bottom).
2. Type `FragmentDemo` in the search/filter bar.
3. Run the app on an emulator or physical device.
4. Watch the logs as you:
    * Open the app.
    * Minimize the app (onStop).
    * Re-open the app (onStart, onResume).
    * Rotate the device (Lifecycle destruction and recreation).
