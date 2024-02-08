package com.example.myapplication.repository.network;

public interface RemoteDataSource {

    public void makeNetworkCallForRandomMeal(NetworkCallback.HomeRequestData  callback);
    public void makeNetworkCallForAllCategory(NetworkCallback.HomeRequestData  callback);
    public void makeNetworkCallForSearchByName(NetworkCallback.ResultSearchByName  callback, String text);
    public void makeNetworkCallForAllCountries(NetworkCallback.SearchRequestData  callback);
    public void makeNetworkCallForAllIngredients(NetworkCallback.SearchRequestData  callback);
    public void makeNetworkCallMealsDataInCategory(NetworkCallback.MealsDataRequest callback, String category);
    public void makeNetworkCallMealsDataInArea(NetworkCallback.MealsDataRequest callback, String area);
    public void makeNetworkCallMealsDataInIngredient(NetworkCallback.MealsDataRequest callback, String ingredient);
    public void makeNetworkCallMealDetails(NetworkCallback.MealsDetailsRequest callback, String mealId);
}
