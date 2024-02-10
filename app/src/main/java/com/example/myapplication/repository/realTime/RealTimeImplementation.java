package com.example.myapplication.repository.realTime;

import static androidx.fragment.app.FragmentManager.TAG;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myapplication.Utility.UtilityFavoriteBtn;
import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.homeActivity.planMealFragment.model.MealsPlan;
import com.example.myapplication.repository.localData.MealLocalDataSourceImpl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RealTimeImplementation {
    private FirebaseDatabase database;
    private DatabaseReference favReference;
    private DatabaseReference planReference;

    public RealTimeImplementation(){
        database = FirebaseDatabase.getInstance();
        favReference = database.getReference().child("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("fav");
        planReference = database.getReference().child("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("plan");
    }

    public void addFav(MealsItem item){
        favReference.child(item.getIdMeal()).setValue(item).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    Log.i("TAG", "onComplete: added");
                else
                    Log.i("TAG", "onComplete: false");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("TAG", "onFailure: "+e.getMessage());
            }
        });
    }
    public void getAllFavMeals(Context context){
        favReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<MealsItem> mealsItems = new ArrayList<>();
                for (DataSnapshot item: snapshot.getChildren()) {
                    mealsItems.add(item.getValue(MealsItem.class));
                }
                UtilityFavoriteBtn fav =new UtilityFavoriteBtn(context);
                for (MealsItem item:
                        mealsItems
                     ) {
                    fav.addFavoriteMeal(item);
                }
                Log.i("TAG", "onDataChange: "+mealsItems.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getAllPlanedMeals(Context context){
        planReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<MealsPlan> mealsPlans = new ArrayList<>();
                for (DataSnapshot item: snapshot.getChildren()) {
                    mealsPlans.add(item.getValue(MealsPlan.class));
                }
                MealLocalDataSourceImpl local = MealLocalDataSourceImpl.getInstance(context);
                for (MealsPlan item:
                        mealsPlans
                ) {
                    local.addPlannedMeal(item);
                }
                Log.i("TAG", "onDataChange: "+mealsPlans.size());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addPlan(MealsPlan item){
        planReference.child(item.getIdMeal()).setValue(item).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    Log.i("TAG", "onComplete: added");
                else
                    Log.i("TAG", "onComplete: false");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("TAG", "onFailure: "+e.getMessage());
            }
        });
    }
}
