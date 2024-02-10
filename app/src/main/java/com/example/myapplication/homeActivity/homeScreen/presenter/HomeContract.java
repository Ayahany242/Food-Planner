package com.example.myapplication.homeActivity.homeScreen.presenter;

import com.example.myapplication.Utility.FavoriteMealInterface;
import com.example.myapplication.homeActivity.model.allCategory.CategoriesItem;
import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.homeActivity.model.allCountries.Country;

import java.util.List;

public interface HomeContract {
    interface View{
        public void showErrorMsg(String error);
        public void showRandomMeal(MealsItem meal);
        public void showAllCategory(List<CategoriesItem> categoriesItems);
       // public void showAllCountries(List<Country> countriesItems);
        public void addMealToFavorite(MealsItem item);
        public void deleteMealFromFavorite(MealsItem item);
        public void notifyMealExistence(boolean exists);
    }
    interface Presenter extends FavoriteMealInterface {
        public void getRandomMeal();
        public void getAllCategory();
        //public void getAllCountries();
        public void addFavoriteMeal(MealsItem item);
        public void deleteFavoriteMeal(MealsItem item);
    }
}
