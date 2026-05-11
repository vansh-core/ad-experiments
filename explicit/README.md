# Android Explicit Intent Experiment

This project demonstrates how to use **Explicit Intents** in Android to pass data between two activities.

## 1. Project Dependencies (app/build.gradle.kts)
Ensure you have the following dependencies added to handle the UI and AppCompat features.

```kotlin
dependencies {
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    // ... other dependencies
}
```

## 2. Manifest Registration (AndroidManifest.xml)
Both activities must be declared as direct children of the `<application>` tag.

```xml
<application ...>
    <activity
        android:name=".MainActivity"
        android:exported="true">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>

    <activity android:name=".MainActivity2" />
</application>
```

## 3. First Screen: MainActivity
### Layout (activity_main.xml)
Contains an `EditText` for input and a `Button` to trigger the intent.
- **EditText ID**: `@+id/editText`
- **Button onClick**: `newsScreen`

### Code (MainActivity.java)
Reads text from the EditText and passes it to the next activity using `putExtra`.

```java
public void newsScreen(View view) {
    EditText et = findViewById(R.id.editText);
    String message = et.getText().toString();

    Intent i = new Intent(this, MainActivity2.class);
    i.putExtra("MSG", message);
    startActivity(i);
}
```

## 4. Second Screen: MainActivity2
### Layout (activity_main2.xml)
Contains a `TextView` to display the incoming message and a `Button` to return home.
- **TextView ID**: `@+id/editText`
- **Button onClick**: `homeScreen`

### Code (MainActivity2.java)
Retrieves the string from the Intent and displays it.

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main2);

    TextView tv = findViewById(R.id.editText);
    Intent intent = getIntent();
    String message = intent.getStringExtra("MSG");

    if (message != null) {
        tv.setText(message);
    }
}
```

## 5. Theme Configuration (themes.xml)
Since we are using `AppCompatActivity`, the theme must inherit from an AppCompat parent.

```xml
<style name="Theme.Explicit" parent="Theme.AppCompat.DayNight.NoActionBar" />
```

## How to Run
1. Open the app on an emulator or physical device.
2. Enter text into the input field on the First Screen.
3. Click "Go to Second Screen".
4. Observe the message being displayed on the Second Screen.
5. Click "Go to First Screen" to return.
