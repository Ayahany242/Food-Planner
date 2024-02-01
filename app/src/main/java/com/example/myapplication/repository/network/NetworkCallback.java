package com.example.myapplication.repository.network;

import com.example.myapplication.homeActivity.model.allCategory.CategoriesItem;
import com.example.myapplication.homeActivity.model.allCountries.Country;
import com.example.myapplication.homeActivity.model.randomModel.MealsItem;

import java.util.List;

public interface NetworkCallback<T> {
    //void onSuccessResult(T result);
    void onSuccessRandomResponse(MealsItem mealsItem);
    void onSuccessAllCategoryResponse(List<CategoriesItem> categories);
    void onSuccessAllCountryResponse(List<Country> countries);
    void onFailureResult(String errorMsg);
}