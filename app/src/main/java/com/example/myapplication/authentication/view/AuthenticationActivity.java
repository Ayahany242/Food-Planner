package com.example.myapplication.authentication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.homeActivity.view.HomeActivity;

import java.util.Objects;

public class AuthenticationActivity extends AppCompatActivity implements MainCommunication{
    private final static String SCREEN = "screen";
    private NavController navController;
    private static final String TAG = "AuthenticationActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        Log.i(TAG, "onCreate: Activity");
        navController = Navigation.findNavController(this,R.id.nav_auth_host_fragment);
        navController.navigate(R.id.loginFragment);
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
    public void navigationBetweenAuth(int Direction) {
        navController.navigate(Direction);
    }

    @Override
    public void navOnSuccess() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    @Override
    public void navOnGuest() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }
}