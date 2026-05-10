# Exp10: Google Maps Android Implementation

A simple Android application demonstrating the integration of Google Maps SDK. This app allows users to view a map centered on SKNCOE, Pune, and switch between different map types (Normal, Satellite, Hybrid, and Terrain) using buttons.

---

## 🛠 Prerequisites

1.  **Android Studio** installed on your machine.
2.  **Google Cloud Console Account** to generate a Google Maps API Key.
3.  **Physical Device or Emulator** with Google Play Services installed.

---

## 🚀 Setup Guide

### 1. Get a Google Maps API Key
1.  Go to the [Google Cloud Console](https://console.cloud.google.com/).
2.  Create a new project.
3.  Search for **"Maps SDK for Android"** and click **Enable**.
4.  Navigate to **Credentials** -> **Create Credentials** -> **API Key**.
5.  Copy this key for later use.

### 2. Project Structure & File Configuration

Follow these steps to place the code in the correct locations:

#### A. Configure Dependencies (`gradle/libs.versions.toml`)
Add the following versions and libraries to your version catalog.

```toml
[versions]
# ... existing versions
googleMaps = "19.0.0"

[libraries]
# ... existing libraries
play-services-maps = { group = "com.google.android.gms", name = "play-services-maps", version.ref = "googleMaps" }
```

#### B. App Build Script (`app/build.gradle.kts`)
Include the Google Maps dependency in your app-level build file.

```kotlin
dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.play.services.maps) // Added this
    // ... other dependencies
}
```

#### C. Android Manifest (`app/src/main/AndroidManifest.xml`)
Update your manifest to include permissions, the API Key, and the Activity declaration.

**Path:** `app/src/main/AndroidManifest.xml`
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

    <application ...>
        
        <!-- PASTE YOUR API KEY HERE -->
        <meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="YOUR_API_KEY_HERE" />

        <activity
            android:name=".MapsActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```

#### D. Layout File (`app/src/main/res/layout/activity_maps.xml`)
Create this file to define the UI with the Map fragment and control buttons.

**Path:** `app/src/main/res/layout/activity_maps.xml`
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <fragment
        android:id="@+id/map"
        android:name="com.google.android.gms.maps.SupportMapFragment"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintBottom_toTopOf="@+id/layoutButtons"
        app:layout_constraintTop_toTopOf="parent" />

    <LinearLayout
        android:id="@+id/layoutButtons"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        app:layout_constraintBottom_toBottomOf="parent">

        <Button android:id="@+id/btnNormal" android:layout_width="0dp" android:layout_height="wrap_content" android:layout_weight="1" android:text="Normal" />
        <Button android:id="@+id/btnSatellite" android:layout_width="0dp" android:layout_height="wrap_content" android:layout_weight="1" android:text="Satellite" />
        <Button android:id="@+id/btnHybrid" android:layout_width="0dp" android:layout_height="wrap_content" android:layout_weight="1" android:text="Hybrid" />
        <Button android:id="@+id/btnTerrain" android:layout_width="0dp" android:layout_height="wrap_content" android:layout_weight="1" android:text="Terrain" />
    </LinearLayout>

</androidx.constraintlayout.widget.ConstraintLayout>
```

#### E. Activity Class (`app/src/main/java/com/example/exp10/MapsActivity.java`)
This Java file handles the map logic and button click listeners.

**Path:** `app/src/main/java/com/example/exp10/MapsActivity.java`
```java
package com.example.exp10;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button btnNormal, btnSatellite, btnHybrid, btnTerrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        btnNormal = findViewById(R.id.btnNormal);
        btnSatellite = findViewById(R.id.btnSatellite);
        btnHybrid = findViewById(R.id.btnHybrid);
        btnTerrain = findViewById(R.id.btnTerrain);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnNormal.setOnClickListener(v -> mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL));
        btnSatellite.setOnClickListener(v -> mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE));
        btnHybrid.setOnClickListener(v -> mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID));
        btnTerrain.setOnClickListener(v -> mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng skncoe = new LatLng(18.4529, 73.8572);
        mMap.addMarker(new MarkerOptions().position(skncoe).title("SKNCOE Pune"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(skncoe, 15));
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }
}
```

---

## 🏃 How to Run
1.  **Sync Gradle**: Click "Sync Now" in Android Studio after updating the files.
2.  **Add API Key**: Ensure you've replaced `YOUR_API_KEY_HERE` in `AndroidManifest.xml`.
3.  **Run**: Click the "Run" button (green play icon) in Android Studio.
