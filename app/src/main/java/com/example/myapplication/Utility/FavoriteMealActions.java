package com.example.myapplication.Utility;

import com.example.myapplication.homeActivity.model.mealData.MealsItem;

import io.reactivex.rxjava3.core.Single;

public interface FavoriteMealActions {
    public void addFavoriteMeal(MealsItem item);
    public void deleteFavoriteMeal(MealsItem item);
    public Single<Boolean> isMealInFavorite(String idMeal);
}
