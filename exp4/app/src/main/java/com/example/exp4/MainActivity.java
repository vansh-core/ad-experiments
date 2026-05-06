package com.example.exp4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Data array — items to show in grid
    String[] courses = {
            "Android Dev",
            "Web Design",
            "Python",
            "Java",
            "Machine Learning",
            "Data Science",
            "Cloud Computing",
            "Networking",
            "Cybersecurity",
            "IoT",
            "VLSI Design",
            "Optical Comm"
    };

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Step 1: Link GridView from XML
        gridView = findViewById(R.id.gridView);

        // Step 2: Create ArrayAdapter
        // ArrayAdapter connects our String array to GridView
        // android.R.layout.simple_list_item_1 = built-in single TextView item layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                courses
        );

        // Step 3: Attach adapter to GridView
        gridView.setAdapter(adapter);

        // Step 4: Handle item click
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // position = index of clicked item (0-based)
                String clickedItem = courses[position];
                Toast.makeText(MainActivity.this,
                        "You clicked: " + clickedItem,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
