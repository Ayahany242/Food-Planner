package com.example.myapplication.homeActivity.allMealsFragment.view;

import android.view.View;

import com.example.myapplication.homeActivity.model.mealData.MealsItem;

public interface OnClickedListener {
    public void isInFavorite(String idMeal);
    public void sendSelectedCard(View v , String selected);
    public void deleteFavoriteMeal(MealsItem item);
    public void addFavoriteMeal(MealsItem item);
//    public void addMealToDB(String mealId);
//    public void deleteMealFromDB(String mealId);
}
