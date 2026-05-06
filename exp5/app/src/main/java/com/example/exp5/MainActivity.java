package com.example.exp5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;
    List<String> studentNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Step 1: Link RecyclerView
        recyclerView = findViewById(R.id.recyclerView);

        // Step 2: Prepare data list
        studentNames = new ArrayList<>();
        studentNames.add("Niraj Patil");
        studentNames.add("Priya Sharma");
        studentNames.add("Rohit Desai");
        studentNames.add("Sneha More");
        studentNames.add("Akash Jadhav");
        studentNames.add("Pooja Kulkarni");
        studentNames.add("Rahul Shinde");
        studentNames.add("Anita Pawar");
        studentNames.add("Vishal Naik");
        studentNames.add("Dipali Gaikwad");

        // Step 3: Create Adapter
        adapter = new MyAdapter(this, studentNames);

        // Step 4: Set LayoutManager (LinearLayoutManager = vertical list)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Step 5: Attach adapter to RecyclerView
        recyclerView.setAdapter(adapter);
    }
}
