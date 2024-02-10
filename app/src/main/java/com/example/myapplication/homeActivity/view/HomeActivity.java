package com.example.myapplication.homeActivity.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.mealDetails.view.MealsDetailsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements HomeActivityCommunicator{
    private BottomNavigationView bottomNavigationView;
    public static final String SELECTED_MEAL = "selectedMeal";
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        bottomNavigationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
    public void goToMealDetails(String mealId) {
        startActivity(new Intent(this, MealsDetailsActivity.class).putExtra(SELECTED_MEAL,mealId));
    }
    @Override
    public void goToAllMealData(String contentName,String source) {
        Bundle bundle = new Bundle();
        bundle.putString("contentName",contentName);
        bundle.putString("source",source);
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        navController.navigate(R.id.allMealsFragment,bundle);
    }

    @Override
    public void goToSearchResult(int destination) {
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        navController.navigate(destination);
    }
}