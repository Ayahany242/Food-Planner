package com.example.myapplication.repository;

import android.util.Log;

import com.example.myapplication.homeActivity.model.randomModel.MealsItem;
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
    public void makeNetworkCallForRandomMeal(NetworkCallback callbackRandomMeal) {
        remoteDataSource.makeNetworkCallForRandomMeal(callbackRandomMeal);
    }
    @Override
    public void makeNetworkCallForAllCategory(NetworkCallback callback) {
        remoteDataSource.makeNetworkCallForAllCategory(callback);
    }

    @Override
    public void makeNetworkCallForAllCountries(NetworkCallback callback) {
        remoteDataSource.makeNetworkCallForAllCountries(callback);
    }

    @Override
    public Flowable<List<MealsItem>> getAllFavoriteMeal() {
        Log.i(TAG, "getAllFavoriteMeals: repo");
        return localDataSource.getAllFavoriteMeal();
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
