package com.example.myapplication.Utility;

import com.example.myapplication.homeActivity.model.mealData.MealsItem;

public interface FavoriteMealInterface {
    public void addFavoriteMeal(MealsItem item);
    public void deleteFavoriteMeal(MealsItem item);
    public void isMealExists(String idMeal);
}
