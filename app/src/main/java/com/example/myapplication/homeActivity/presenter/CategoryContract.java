package com.example.myapplication.homeActivity.presenter;

import com.example.myapplication.homeActivity.model.allCategory.CategoriesItem;
import com.example.myapplication.homeActivity.model.allCountries.Country;
import com.example.myapplication.homeActivity.model.randomModel.MealsItem;

import java.util.List;

public interface CategoryContract {
     interface View{
         public void showErrorMsg(String error);
         public void showRandomMeal(MealsItem meal);
         public void showAllCategory(List<CategoriesItem> categoriesItems);
         public void showAllCountries(List<Country> countriesItems);
         public void addMealToFavorite(MealsItem item);
         public void deleteMealFromFavorite(MealsItem item);
         public boolean isMealFavorite(String idMeal);
     }
     interface Presenter{
        public void getRandomMeal();
        public void getAllCategory();
         public void getAllCountries();
         public void addFavoriteMeal(MealsItem item);
         public void deleteFavoriteMeal(MealsItem item);
         public boolean isMealExists(String idMeal);

     }
}
