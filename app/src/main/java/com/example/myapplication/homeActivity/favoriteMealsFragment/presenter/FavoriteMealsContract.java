package com.example.myapplication.homeActivity.favoriteMealsFragment.presenter;

import com.example.myapplication.homeActivity.model.mealData.MealsItem;

import java.util.List;

public interface FavoriteMealsContract {
    interface View{
        public void showAllFavoriteMeals(List<MealsItem> meals);
        public void notifyMealExistence(boolean exists);
        public void deleteMealFromFavorite(MealsItem item);
        public void showErrorMsg(String error);
    }
    interface Presenter{
        public void deleteFavoriteMeal(MealsItem item);
        public void getAllFavoriteMeals();
    }

}
