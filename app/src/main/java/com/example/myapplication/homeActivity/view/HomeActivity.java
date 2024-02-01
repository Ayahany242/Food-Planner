package com.example.myapplication.homeActivity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.homeActivity.view.homeFragment.MealsHomeFragment;

public class HomeActivity extends AppCompatActivity implements HomeActivityCommunicator{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        showHomeFragment();

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public void showHomeFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.homeFragment, new MealsHomeFragment())
                .commit();
    }
    @Override
    public Context getContext() {
        return this;
    }
}