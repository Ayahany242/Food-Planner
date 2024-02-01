package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.authentication.view.AuthenticationActivity;
import com.example.myapplication.authentication.view.MainCommunication;

public class MainActivity extends AppCompatActivity {
    private Button skipBtn;
    public static final String SKIP = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        skipBtn = findViewById(R.id.skipBtn);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(),AuthenticationActivity.class);
                startActivity(intent);
            }
        });

    }


}