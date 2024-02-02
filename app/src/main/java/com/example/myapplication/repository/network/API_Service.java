package com.example.myapplication.repository.network;


import com.example.myapplication.homeActivity.model.allCategory.AllCategoryResponse;
import com.example.myapplication.homeActivity.model.allCountries.CountryListResponse;
import com.example.myapplication.homeActivity.model.randomModel.RandomMealResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface API_Service {
    @GET("random.php")
    Call<RandomMealResponse> requestRandomMeal();
    @GET("categories.php")
    Call<AllCategoryResponse> requestAllCategory();
    @GET("list.php?a=list")
    Call<CountryListResponse> requestAllCountries();
}
