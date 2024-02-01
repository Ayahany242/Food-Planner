package com.example.myapplication.repository.localData;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.example.myapplication.homeActivity.model.randomModel.MealsItem;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealLocalDataSourceImpl implements MealLocalDataSource {
    private static final String TAG = "DataBase";
    private MealDao dao;
    private static MealLocalDataSourceImpl localDataSource = null;
    private Single<List<MealsItem>> storedMeals;
    private MealLocalDataSourceImpl(Context context){
        AppDataBase db = AppDataBase.getInstance(context);
        dao = db.getMealDao();
        storedMeals =dao.getAllFavoriteMeal();
        Log.i(TAG, "Get fav meals: "+storedMeals.toString());
    }
    public static MealLocalDataSourceImpl getInstance(Context context){
        if(localDataSource ==null)
            localDataSource = new MealLocalDataSourceImpl(context);
        return localDataSource;
    }

    @Override
    public Single<List<MealsItem>>  getAllFavoriteMeal() {
        return storedMeals;
    }

    @Override
    public void addToFavoriteMeal(MealsItem meal) {
        new Thread(()->{
            try {
                dao.insertFavoriteMeal(meal);
                Log.i(TAG, "addToFavoriteMeal: success meal id" + meal.getIdMeal());
            }catch (SQLiteConstraintException e){
                Log.i(TAG, "deleteFromFavoriteMeal: failed ");
            }
        }).start();

    }
    @Override
    public void deleteFromFavoriteMeal(MealsItem meal) {
        new Thread(()-> {
            try{
                dao.deleteMealFromFavorite(meal);
                Log.i(TAG, "deleteFromFavoriteMeal: success meal id" + meal.getIdMeal());
            }catch (SQLiteConstraintException e){
                Log.i(TAG, "deleteFromFavoriteMeal: failed ");
            }
        }).start();
    }
    @Override
    public Single<Boolean> isMealExists(String idMeal) {
        return Single.fromCallable(() -> dao.getMealId(idMeal) != null)
                .subscribeOn(Schedulers.io()).contains(idMeal);
    }


}
