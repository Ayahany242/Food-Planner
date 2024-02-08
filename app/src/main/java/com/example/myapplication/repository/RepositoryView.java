package com.example.myapplication.repository;

import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.repository.network.NetworkCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface RepositoryView {
    public void makeNetworkCallForRandomMeal(NetworkCallback.HomeRequestData callbackRandomMeal);
    public void makeNetworkCallForAllCategory(NetworkCallback.HomeRequestData  callback);
    //public void makeNetworkCallForAllCountries(NetworkCallback.HomeRequestData  callback);
    public void makeNetworkCallForSearchByName(NetworkCallback.ResultSearchByName callback, String text);
    public void makeNetworkCallForAllCountries(NetworkCallback.SearchRequestData  callback);
    public void makeNetworkCallForAllIngredients(NetworkCallback.SearchRequestData  callback);
    public void makeNetworkCallMealsDataInCategory(NetworkCallback.MealsDataRequest callback, String category);
    public void makeNetworkCallMealsDataInArea(NetworkCallback.MealsDataRequest callback, String area);
    public void makeNetworkCallMealsDataInIngredient(NetworkCallback.MealsDataRequest callback, String ingredient);
    public void makeNetworkCallMealDetails(NetworkCallback.MealsDetailsRequest callback, String mealId);
    public void deleteMealFromFavorite(MealsItem item);
    public void addMealToFavorite(MealsItem item);
    public Single<Boolean> isMealExists(String idMeal);
    public Flowable<List<MealsItem>> getAllFavoriteMeal();

}
