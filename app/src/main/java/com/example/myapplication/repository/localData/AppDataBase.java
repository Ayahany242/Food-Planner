package com.example.myapplication.repository.localData;

import android.content.Context;
import android.util.Log;

import androidx.constraintlayout.helper.widget.MotionEffect;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.homeActivity.model.randomModel.MealsItem;

@Database(entities = {MealsItem.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance = null;
    public abstract MealDao getMealDao();
    public static synchronized AppDataBase getInstance(Context context){
        if(instance == null){
            Log.i(MotionEffect.TAG, "getInstance: On AppDataBase ");
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class,"foodPlannerdb").build();
        }
        return instance;
    }
}
