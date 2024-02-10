package com.example.myapplication.repository;

import android.util.Log;

import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.homeActivity.planMealFragment.model.MealsPlan;
import com.example.myapplication.repository.localData.MealLocalDataSource;
import com.example.myapplication.repository.network.NetworkCallback;
import com.example.myapplication.repository.network.RemoteDataSource;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class RepositoryImpl implements RepositoryView{
    private final RemoteDataSource remoteDataSource;
    private final MealLocalDataSource localDataSource;
    private static RepositoryImpl repository = null;
    private static final String TAG = "repo";
    private RepositoryImpl(RemoteDataSource remoteDataSource, MealLocalDataSource localDataSource){
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }
    public static RepositoryImpl getInstance(RemoteDataSource remoteDataSource, MealLocalDataSource localDataSource){
        if(repository == null)
            repository = new RepositoryImpl(remoteDataSource,localDataSource);
        return repository;
    }
    @Override
    public void makeNetworkCallForRandomMeal(NetworkCallback.HomeRequestData  callbackRandomMeal) {
        remoteDataSource.makeNetworkCallForRandomMeal(callbackRandomMeal);
    }
    @Override
    public void makeNetworkCallForAllCategory(NetworkCallback.HomeRequestData  callback) {
        remoteDataSource.makeNetworkCallForAllCategory(callback);
    }

    @Override
    public void makeNetworkCallForSearchByName(NetworkCallback.ResultSearchByName callback, String text) {
        remoteDataSource.makeNetworkCallForSearchByName(callback,text);
    }

  /*   @Override
    public void makeNetworkCallForSearch(NetworkCallback.ResultSearchByName callback, String text) {
       // remoteDataSource.makeNetworkCallForSearchByName(callback, text);
    }

   @Override
    public void makeNetworkCallForAllCountries(NetworkCallback.HomeRequestData  callback) {
        remoteDataSource.makeNetworkCallForAllCountries(callback);
    }*/

    @Override
    public void makeNetworkCallForAllCountries(NetworkCallback.SearchRequestData callback) {
        remoteDataSource.makeNetworkCallForAllCountries(callback);
    }

    @Override
    public void makeNetworkCallForAllIngredients(NetworkCallback.SearchRequestData callback) {
        remoteDataSource.makeNetworkCallForAllIngredients(callback);
    }

    @Override
    public void makeNetworkCallMealsDataInCategory(NetworkCallback.MealsDataRequest callback, String category) {
        remoteDataSource.makeNetworkCallMealsDataInCategory(callback,category);
    }

    @Override
    public void makeNetworkCallMealsDataInArea(NetworkCallback.MealsDataRequest callback, String area) {
        remoteDataSource.makeNetworkCallMealsDataInArea(callback,area);
    }

    @Override
    public void makeNetworkCallMealsDataInIngredient(NetworkCallback.MealsDataRequest callback, String ingredient) {
        remoteDataSource.makeNetworkCallMealsDataInIngredient(callback,ingredient);
    }

    @Override
    public void makeNetworkCallMealDetails(NetworkCallback.MealsDetailsRequest callback, String mealId) {
        remoteDataSource.makeNetworkCallMealDetails(callback, mealId);
    }
    @Override
    public Flowable<List<MealsItem>> getAllFavoriteMeal() {
        Log.i(TAG, "getAllFavoriteMeals: repo");
        return localDataSource.getAllFavoriteMeal();
    }

    @Override
    public Flowable<List<MealsPlan>> getAllPlannedMeal(String day) {
        return localDataSource.getAllPlannedMeal(day);
    }

    @Override
    public void addPlannedMeal(MealsPlan mealsPlan) {
        localDataSource.addPlannedMeal(mealsPlan);
    }

    @Override
    public void deletePlannedMeal(MealsPlan mealsPlan) {
        localDataSource.deletePlannedMeal(mealsPlan);
    }
    @Override
    public void deleteAllMeals() {
        localDataSource.deleteAllMeals();
    }

    @Override
    public void deleteAllMealsPlan() {
        localDataSource.deleteAllMealsPlan();
    }

    @Override
    public void deleteMealFromFavorite(MealsItem item) {
        localDataSource.deleteFromFavoriteMeal(item);
        Log.i(TAG, "deleteMealFromFavorite: repo");
    }

    @Override
    public void addMealToFavorite(MealsItem item) {
        localDataSource.addToFavoriteMeal(item);
        Log.i(TAG, "addMealToFavorite: repo ");
    }
    @Override
    public Single<Boolean> isMealExists(String idMeal) {
        return localDataSource.isMealExists(idMeal);
    }
}
