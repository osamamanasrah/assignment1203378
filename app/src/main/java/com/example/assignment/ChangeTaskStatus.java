package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.Model.Task;
import com.google.gson.Gson;

public class ChangeTaskStatus extends AppCompatActivity {

    private EditText editText;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_task_status);
        setUpViews();
        setUpPreferences();
    }

    private void setUpPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }

    private void setUpViews() {
        editText=findViewById(R.id.edtByName);
    }

    public void btnChangeOnclick(View view) {
            String taskString = sharedPreferences.getString("TASKSTRING","");
            if(taskString.equals("")){
                Toast.makeText(this , "No such Task", Toast.LENGTH_SHORT).show();
            }
            else{
                Gson gson = new Gson();
                Task[] task = gson.fromJson(taskString, Task[].class);

                for(int i = 0 ; i < task.length ; i++){
                    if(task[i].getTaskName().equals(editText)){
                        String temp = task[i].getTaskStatus();
                        if (task[i].getTaskStatus().equals("due")){
                            task[i].setTaskStatus("done");
                        }
                        else{
                            task[i].setTaskStatus("due");
                        }
                    }
                }
            }
    }
}