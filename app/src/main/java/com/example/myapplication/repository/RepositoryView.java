package com.example.myapplication.repository;

import com.example.myapplication.homeActivity.model.randomModel.MealsItem;
import com.example.myapplication.repository.network.NetworkCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface RepositoryView {
    public void makeNetworkCallForRandomMeal(NetworkCallback callbackRandomMeal);
    public void makeNetworkCallForAllCategory(NetworkCallback callback);
    public void makeNetworkCallForAllCountries(NetworkCallback callback);
    public Single<List<MealsItem>> getAllFavoriteMeals();
    public void deleteMealFromFavorite(MealsItem item);
    public void addMealToFavorite(MealsItem item);
    public Single<Boolean> isMealExists(String idMeal);
}
