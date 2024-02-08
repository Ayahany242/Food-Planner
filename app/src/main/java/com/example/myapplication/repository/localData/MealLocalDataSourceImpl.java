package com.example.myapplication.repository.localData;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.example.myapplication.homeActivity.model.mealData.MealsItem;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealLocalDataSourceImpl implements MealLocalDataSource {
    private static final String TAG = "Tag";
    private MealDao dao;
    private static MealLocalDataSourceImpl localDataSource = null;
    private Flowable<List<MealsItem>> storedMeals;
    private MealLocalDataSourceImpl(Context context){
        AppDataBase db = AppDataBase.getInstance(context);
        dao = db.getMealDao();
        storedMeals = dao.getAllFavoriteMeal();
        Log.i(TAG, "MealLocalDataSourceImpl: storedMeals ");
       // Log.i(TAG, "Get fav meals: "+storedMeals.toString());
    }
    public static MealLocalDataSourceImpl getInstance(Context context){
        if(localDataSource ==null)
            localDataSource = new MealLocalDataSourceImpl(context);
        return localDataSource;
    }
    @Override
    public Flowable<List<MealsItem>> getAllFavoriteMeal() {
       /* storedMeals.subscribe(
                meals -> Log.i(TAG, "Favorite meals emitted: " + meals),
                throwable -> Log.i(TAG, "Error retrieving favorite meals", throwable)
        );*/
     //  return dao.getAllFavoriteMeal().subscribeOn(Schedulers.io());
        Log.i(TAG, "getAllFavoriteMeal: local DB");
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
        return dao.getMealId(idMeal).subscribeOn(Schedulers.io()).contains(idMeal);
//        return Single.fromCallable(() -> dao.getMealId(idMeal) != null)
//                .subscribeOn(Schedulers.io()).contains(idMeal);
    }
}
