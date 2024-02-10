package com.example.myapplication.homeActivity.allMealsFragment.presenter;

import com.example.myapplication.Utility.FavoriteMealInterface;
import com.example.myapplication.homeActivity.allMealsFragment.model.MealsData;
import com.example.myapplication.homeActivity.searchFragment.searchIngredients.presenter.SearchResultContract;

import java.util.List;

public interface AllMealContract {
    interface View extends SearchResultContract.View<MealsData>{
        public void showErrorMsg(String error);
        public void showAllMeal(List<MealsData> mealsDataList);
       // public void notifyMealExistence(boolean exists);
    }
    interface Presenter extends SearchResultContract.Presenter<MealsData> {
        public void getAllMealCountry(String country);

        public void getAllMealMealsIngredients(String ingredients);
        public void getAllMealCategory(String category);
    }
}
