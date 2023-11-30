package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.assignment.Model.Task;
import com.google.gson.Gson;

import java.util.ArrayList;

public class dueTasks extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_due_tasks);
        listView = findViewById(R.id.listDueTask);
        setUpSharedPreference();
        populateListView();
    }

    private void setUpSharedPreference() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }

    private void populateListView(){
        Gson gson = new Gson();
        String str = sharedPreferences.getString("TASKSTRING", "");
        Task [] Tasks = gson.fromJson(str, Task[].class);
        ArrayList <String> list = new ArrayList<>();
        for(int i = 0 ; i < Tasks.length; i++){
            if(Tasks[i].getTaskStatus().equals("due")){
                list.add(Tasks[i].getTaskName());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }


    public void btnBackOnclick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}