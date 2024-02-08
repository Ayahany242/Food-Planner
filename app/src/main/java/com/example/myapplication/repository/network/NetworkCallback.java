package com.example.myapplication.repository.network;

import com.example.myapplication.homeActivity.allMealsFragment.model.MealsData;
import com.example.myapplication.homeActivity.model.allCategory.CategoriesItem;
import com.example.myapplication.homeActivity.model.allCountries.Country;
import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.homeActivity.searchFragment.model.IngredientsData;

import java.util.List;

public interface NetworkCallback {
    interface HomeRequestData{
        void onSuccessRandomResponse(MealsItem mealsItem);
        void onSuccessAllCategoryResponse(List<CategoriesItem> categories);
        void onFailureResult(String errorMsg);
    }
    interface SearchRequestData{
        void onSuccessAllIngredientsResponse(List<IngredientsData> ingredientsData);
        void onSuccessAllCountryResponse(List<Country> countries);
        void onFailureResult(String errorMsg);

    }
    interface MealsDetailsRequest {
        void onSuccessMealDetailsResponse(MealsItem mealsItem);
        void onFailureResult(String errorMsg);
    }
    interface MealsDataRequest{
        void onSuccessMealsDataResponse(List<MealsData> allMealsInCategory);
        void onFailureResult(String errorMsg);
    }

    public interface ResultSearchByName {
        void onSuccessMealSearchResponse(List<MealsItem> mealsItem);
        void onFailureResult(String errorMsg);
    }
}