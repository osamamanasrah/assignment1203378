package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnDueOnclick(View view) {
        Intent intent = new Intent(this, dueTasks.class);
        startActivity(intent);
    }

    public void btnAddOnclick(View view) {
        Intent intent = new Intent(this, AddTask.class);
        startActivity(intent);
    }

    public void btnChangeStatusOnclick(View view) {
        Intent intent = new Intent(this, ChangeTaskStatus.class);
        startActivity(intent);
    }
}