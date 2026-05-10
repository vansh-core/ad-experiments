# College Experiment: User Registration Form

This project is a simple Android application demonstrating a basic user registration form. It covers essential UI components, user input handling, and basic form validation.

## 🏗️ Project Setup & Structure

Follow these steps to set up the project in Android Studio.

### 1. Create a New Project
*   Open Android Studio.
*   Select **New Project** -> **Empty Views Activity**.
*   Name: `Registrationform`
*   Package Name: `com.example.registrationform`
*   Language: `Java`

### 2. Project File Structure
Ensure your files are placed in these exact locations:

```
Registrationform/
├── app/
│   ├── build.gradle.kts (Module:app)
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/com/example/registrationform/MainActivity.java
│           └── res/
│               ├── layout/activity_main.xml
│               └── values/
│                   ├── colors.xml
│                   ├── strings.xml
│                   └── themes.xml
```

---

## 🛠️ Implementation Details

### 1. Dependencies (`app/build.gradle.kts`)
Open `build.gradle.kts` (Module:app) and ensure these dependencies are present in the `dependencies` block:

```kotlin
dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}
```

### 2. Manifest Configuration (`app/src/main/AndroidManifest.xml`)
This file registers your activity and sets the application theme.

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Registrationform">

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

### 3. Layout XML (`app/src/main/res/layout/activity_main.xml`)
This defines the user interface using a `LinearLayout`.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="20dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="User Registration"
        android:textSize="26sp"
        android:textStyle="bold"
        android:layout_gravity="center_horizontal"
        android:layout_marginBottom="20dp"/>

    <EditText
        android:id="@+id/etName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Full Name"
        android:inputType="textPersonName"
        android:layout_marginBottom="10dp"/>

    <EditText
        android:id="@+id/etEmail"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Email Address"
        android:inputType="textEmailAddress"
        android:layout_marginBottom="10dp"/>

    <EditText
        android:id="@+id/etPassword"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Password"
        android:inputType="textPassword"
        android:layout_marginBottom="15dp"/>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Select Gender:"
        android:textSize="16sp"/>

    <RadioGroup
        android:id="@+id/radioGroup"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">
        <RadioButton android:id="@+id/rbMale" android:layout_width="wrap_content" android:layout_height="wrap_content" android:text="Male"/>
        <RadioButton android:id="@+id/rbFemale" android:layout_width="wrap_content" android:layout_height="wrap_content" android:text="Female"/>
    </RadioGroup>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Interests:"
        android:layout_marginTop="10dp"
        android:textSize="16sp"/>

    <CheckBox android:id="@+id/cbSports" android:layout_width="wrap_content" android:layout_height="wrap_content" android:text="Sports"/>
    <CheckBox android:id="@+id/cbMusic" android:layout_width="wrap_content" android:layout_height="wrap_content" android:text="Music"/>

    <Button
        android:id="@+id/btnSubmit"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Register Now"
        android:layout_marginTop="20dp"/>

</LinearLayout>
```

### 4. Activity File (`app/src/main/java/com/example/registrationform/MainActivity.java`)
This file contains the logic for finding views, validating inputs, and showing the result.

```java
package com.example.registrationform;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword;
    RadioGroup radioGroup;
    CheckBox cbSports, cbMusic;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind Views
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        radioGroup = findViewById(R.id.radioGroup);
        cbSports = findViewById(R.id.cbSports);
        cbMusic = findViewById(R.id.cbMusic);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Simple Validation
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Get Selected Gender
                int selectedId = radioGroup.getCheckedRadioButtonId();
                String gender = "None";
                if (selectedId != -1) {
                    RadioButton rb = findViewById(selectedId);
                    gender = rb.getText().toString();
                }

                // Get Selected Interests
                String interests = "";
                if (cbSports.isChecked()) interests += "Sports ";
                if (cbMusic.isChecked()) interests += "Music ";
                if (interests.isEmpty()) interests = "None";

                // Display Result in a Toast
                String result = "Name: " + name + "\nGender: " + gender + "\nInterests: " + interests;
                Toast.makeText(MainActivity.this, "Registration Successful!\n" + result, Toast.LENGTH_LONG).show();
            }
        });
    }
}
```

### 5. Essential Resources
Ensure these files exist in `res/values/` to avoid compilation errors.

#### `res/values/colors.xml`
```xml
<resources>
    <color name="purple_500">#6200EE</color>
    <color name="white">#FFFFFF</color>
</resources>
```

#### `res/values/strings.xml`
```xml
<resources>
    <string name="app_name">Registration Form</string>
</resources>
```

---

## 🚀 How to Run
1.  **Sync Gradle**: Click the **Sync Now** bar at the top of the editor after adding dependencies.
2.  **Select Device**: Choose an emulator or a connected physical device.
3.  **Run**: Click the green **Run** button (Play icon).
4.  **Test**: Enter your details and click **Register Now** to see the summary toast!
