# Implicit Intent Search App

A simple Android application that demonstrates the use of **Implicit Intents**. The app allows users to enter a URL in an `EditText` and open it in a web browser using `Intent.ACTION_VIEW`.

---

## 🚀 Setup Guide

Follow these steps to set up the project in Android Studio.

### 1. Project Structure
Ensure your files are placed in the following directories:

```text
implicit/
├── app/
│   ├── build.gradle.kts (Module-level Gradle)
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/com/example/implicit/
│           │   └── MainActivity.java
│           └── res/
│               ├── layout/
│               │   └── activity_main.xml
│               └── values/
│                   └── strings.xml
└── build.gradle.kts (Project-level Gradle)
```

### 2. Dependencies (`app/build.gradle.kts`)
Add the following dependencies to your module-level `build.gradle.kts` file:

```kotlin
dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
}
```
*Note: If you are not using Version Catalogs (`libs.versions.toml`), use standard strings like `"androidx.appcompat:appcompat:1.7.0"`.*

### 3. Permissions & Configuration (`AndroidManifest.xml`)
Open `app/src/main/AndroidManifest.xml` and ensure it includes the Internet permission and the Activity declaration:

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/Theme.Implicit">

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

### 4. Layout (`activity_main.xml`)
Create `app/src/main/res/layout/activity_main.xml` and paste the following:

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <EditText
        android:id="@+id/editText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="@string/search_hint"
        android:inputType="textUri"
        android:autofillHints="none"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/btn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/search_button"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/editText" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### 5. Java Code (`MainActivity.java`)
Create `app/src/main/java/com/example/implicit/MainActivity.java` and paste the following:

```java
package com.example.implicit;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editText.getText().toString();
                if (!url.isEmpty()) {
                    // Prepend https:// if missing to ensure Intent works
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        url = "https://" + url;
                    }
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
            }
        });
    }
}
```

### 6. String Resources (`strings.xml`)
Add these values to `app/src/main/res/values/strings.xml`:

```xml
<resources>
    <string name="app_name">Implicit Intent</string>
    <string name="search_hint">Enter URL (e.g. google.com)</string>
    <string name="search_button">Open Browser</string>
</resources>
```

---

## 🛠 How to Run
1.  Open the project in **Android Studio**.
2.  Connect an Android device or start an **Emulator**.
3.  Click the **Run** button (green play icon) in the toolbar.
4.  Type a website address (e.g., `github.com`) in the text box.
5.  Click **Open Browser** to launch the site.
