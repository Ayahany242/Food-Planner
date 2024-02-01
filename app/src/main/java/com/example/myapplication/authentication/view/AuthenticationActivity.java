package com.example.myapplication.authentication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.Objects;

public class AuthenticationActivity extends AppCompatActivity implements MainCommunication{
    private final static String SCREEN = "screen";

    private static final String TAG = "AuthenticationActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        if(savedInstanceState == null){
            showLoginFragment();
        }
        Log.i(TAG, "onCreate: Activity");

    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    @Override
    public boolean isInputValid(String txt) {
        return !TextUtils.isEmpty(txt);
    }
    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public Context getContext() {
        return this;
    }
    @Override
    public void showLoginFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.authFragment, new LoginFragment())
                .commit();
    }
    @Override
    public void showSignUpFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.authFragment, new SignUpFragment())
                .addToBackStack(null)
                .commit();
    }
}