package com.example.myapplication.homeActivity.searchFragment.presenter;

import com.example.myapplication.homeActivity.model.allCategory.CategoriesItem;
import com.example.myapplication.homeActivity.model.allCountries.Country;
import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.homeActivity.searchFragment.model.IngredientsData;
import com.example.myapplication.mealDetails.model.IngredientModel;

import java.util.List;

public interface SearchContract {
    interface View {
        public void showErrorMsg(String error);
        public void showAllIngredients(List<IngredientsData> ingredientsItems);
        public void showAllCountries(List<Country> countriesItems);
      //  public void showResult(List<MealsItem> mealsItems);
    }
    interface Presenter{
        public void getAllIngredients();
        public void getAllCountries();
    }
}
