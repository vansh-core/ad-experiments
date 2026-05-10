# Experiment 3: Fragment Communication & RecyclerView

This project demonstrates two core Android concepts:
1. **Fragment Communication**: Passing data from one Fragment to another using `Bundle`.
2. **RecyclerView Implementation**: A custom adapter and model for displaying lists.

---

## 🚀 Setup Guide

### 1. Project Configuration

#### **`build.gradle.kts` (Module: :app)**
Ensure you have the following dependencies in your `app/build.gradle.kts` file.

```kotlin
dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation("androidx.recyclerview:recyclerview:1.3.2")
}
```

#### **`AndroidManifest.xml`**
Located at `app/src/main/AndroidManifest.xml`. Ensure `MainActivity` is declared as the entry point.

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/Theme.Exp3">
        
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

### 2. Layout XML Files
Add these files to `app/src/main/res/layout/`.

#### **`activity_main.xml`**
The main container for fragments.
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <FrameLayout
        android:id="@+id/fragment_container"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
</LinearLayout>
```

#### **`fragment_first.xml`**
Layout for the sender fragment.
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center"
    android:padding="20dp">

    <EditText
        android:id="@+id/etMessage"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter message"/>

    <Button
        android:id="@+id/btnSend"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Send to Fragment 2"/>
</LinearLayout>
```

#### **`fragment_second.xml`**
Layout for the receiver fragment.
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">

    <TextView
        android:id="@+id/tvReceivedMessage"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="No message yet"
        android:textSize="18sp"/>
</LinearLayout>
```

#### **`item_row.xml`**
Layout for a single item in the RecyclerView.
```xml
<androidx.cardview.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="8dp">

    <TextView
        android:id="@+id/tvItemName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="20dp"
        android:textSize="18sp" />
</androidx.cardview.widget.CardView>
```

---

### 3. Java Files
Add these files to `app/src/main/java/com/example/exp3/`.

#### **`MainActivity.java`**
Loads the initial fragment.
```java
package com.example.exp3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new FirstFragment())
                    .commit();
        }
    }
}
```

#### **`FirstFragment.java`**
Handles sending data via Bundle.
```java
package com.example.exp3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        EditText etMessage = view.findViewById(R.id.etMessage);
        Button btnSend = view.findViewById(R.id.btnSend);

        btnSend.setOnClickListener(v -> {
            String message = etMessage.getText().toString();
            SecondFragment secondFragment = new SecondFragment();
            Bundle bundle = new Bundle();
            bundle.putString("message_key", message);
            secondFragment.setArguments(bundle);

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, secondFragment)
                    .addToBackStack(null)
                    .commit();
        });
        return view;
    }
}
```

#### **`SecondFragment.java`**
Retrieves data from the Bundle.
```java
package com.example.exp3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        TextView tvReceivedMessage = view.findViewById(R.id.tvReceivedMessage);

        Bundle bundle = getArguments();
        if (bundle != null) {
            tvReceivedMessage.setText("Received: " + bundle.getString("message_key"));
        }
        return view;
    }
}
```

#### **`ItemModel.java`**
Data model for RecyclerView.
```java
package com.example.exp3;

public class ItemModel {
    private String name;
    public ItemModel(String name) { this.name = name; }
    public String getName() { return name; }
}
```

#### **`MyAdapter.java`**
The RecyclerView Adapter.
```java
package com.example.exp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<ItemModel> itemList;
    public MyAdapter(List<ItemModel> itemList) { this.itemList = itemList; }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvItemName);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvName.setText(itemList.get(position).getName());
    }

    @Override
    public int getItemCount() { return itemList.size(); }
}
```

---

## 🛠 How to Run
1. Create a new Android Studio Project with the package name `com.example.exp3`.
2. Copy the dependencies into your `build.gradle.kts` (app).
3. Create the XML files in `res/layout` and Java files in `java/com/example/exp3/` as shown above.
4. Sync Gradle and Run the app on an Emulator or Physical Device.
