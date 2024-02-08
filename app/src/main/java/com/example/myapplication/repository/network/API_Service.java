package com.example.myapplication.repository.network;


import com.example.myapplication.homeActivity.model.allCategory.AllCategoryResponse;
import com.example.myapplication.homeActivity.model.allCountries.CountryListResponse;
import com.example.myapplication.homeActivity.model.mealData.MealResponse;
import com.example.myapplication.homeActivity.allMealsFragment.model.MealsListResponse;
import com.example.myapplication.homeActivity.searchFragment.model.AllIngredientsResponse;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface API_Service {
    @GET("random.php")
    Single<MealResponse> requestRandomMeal();
    @GET("categories.php")
    Single<AllCategoryResponse> requestAllCategory();
    @GET("list.php?a=list")
    Single<CountryListResponse> requestAllCountries();
    @GET("list.php?i=list")
    Single<AllIngredientsResponse> requestAllIngredients();
    @GET("filter.php")
    Single<MealsListResponse> requestAllMealsInCategory(@Query("c") String category);
    @GET("filter.php")
    Single<MealsListResponse> requestAllMealsInArea(@Query("a") String country);
    @GET("filter.php")
    Single<MealsListResponse> requestFilterByIngredients(@Query("i") String ingredients);
    @GET("lookup.php")
    Single<MealResponse> requestMealDetail(@Query("i") String mealId);
    @GET("search.php")
    Flowable<MealResponse> requestSearch(@Query("s") String searchText);

}
