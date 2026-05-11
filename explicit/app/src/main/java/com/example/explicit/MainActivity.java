package com.example.explicit;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newsScreen(View view) {
        EditText et = findViewById(R.id.editText);
        String message = et.getText().toString();

        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("MSG", message);
        startActivity(i);
    }
}
