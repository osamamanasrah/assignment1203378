package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.Model.Task;
import com.google.gson.Gson;

public class AddTask extends AppCompatActivity {

    private static final String TASKSTRING = "TASKSTRING";
    private EditText edtName;
    private EditText edtStatus;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        setViews();
        setPreference();
    }


    private void setPreference() {
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor=sharedPreferences.edit();
    }
    private void setViews() {
        edtName = findViewById(R.id.edtName);
        edtStatus = findViewById(R.id.edtStatus);
    }


    public void btnAddOnclick(View view) {
        String str = sharedPreferences.getString(TASKSTRING, "");
        if(str.equals("")){
            Task Tasks[] = new Task[1];
            Tasks[0]= new Task(edtName.getText().toString(),edtStatus.getText().toString());
            Gson gson = new Gson();
            String taskString = gson.toJson(Tasks);

            editor.putString(TASKSTRING, taskString);
            editor.commit();
        }
        else{
            Gson gson = new Gson();
            Task[] temp = gson.fromJson(str, Task[].class);
            Task[] tasks = new Task[temp.length+1];

            for(int i = 0 ; i < temp.length ; i++){
                tasks[i]=temp[i];
            }
            tasks[tasks.length-1]= new Task(edtName.getText().toString(),edtStatus.getText().toString());

            String taskString = gson.toJson(tasks);
            Toast.makeText(this, taskString, Toast.LENGTH_SHORT).show();
            editor.putString(TASKSTRING, taskString);
            editor.commit();
        }
    }

    public void btnBackOnclick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}