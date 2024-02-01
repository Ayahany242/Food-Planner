package com.example.myapplication.repository.network;

import android.util.Log;

import com.example.myapplication.homeActivity.model.allCategory.AllCategoryResponse;
import com.example.myapplication.homeActivity.model.allCountries.CountryListResponse;
import com.example.myapplication.homeActivity.model.randomModel.RandomMealResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSourceImp implements RemoteDataSource {
    private static final String  BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private final String TAG = "TAG";
    private static RemoteDataSourceImp client = null;
    private Retrofit retrofit;
    private API_Service service;
    private RemoteDataSourceImp(){
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(API_Service.class);
    }
    public static RemoteDataSourceImp getInstance(){
        if (client == null)
            client = new RemoteDataSourceImp();
        return client;
    }

    @Override
    public void makeNetworkCallForRandomMeal(NetworkCallback callbackRandomMeal) {
        Call<RandomMealResponse> call = service.requestRandomMeal();
        call.enqueue(new Callback<RandomMealResponse>() {
            @Override
            public void onResponse(Call<RandomMealResponse> call, Response<RandomMealResponse> response) {
                Log.i(TAG, "onResponse: makeNetworkCallForRandomMeal meal size  "+response.body().getMeals().size());
                callbackRandomMeal.onSuccessRandomResponse(response.body().getMeals().get(0));
            }

            @Override
            public void onFailure(Call<RandomMealResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: makeNetworkCallForRandomMeal "+t.getMessage());
                callbackRandomMeal.onFailureResult(t.getMessage());
            }
        });
    }

    @Override
    public void makeNetworkCallForAllCategory(NetworkCallback callback) {
        Call<AllCategoryResponse> call = service.requestAllCategory();
        call.enqueue(new Callback<AllCategoryResponse>() {
            @Override
            public void onResponse(Call<AllCategoryResponse> call, Response<AllCategoryResponse> response) {
                Log.i(TAG, "onResponse: makeNetworkCallForAllCategory Category size Remote Date Source "+response.body().getCategories().size());
                callback.onSuccessAllCategoryResponse(response.body().getCategories());
            }

            @Override
            public void onFailure(Call<AllCategoryResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: makeNetworkCallForAllCategory Category size Remote Date Source "+t.getMessage());
                callback.onFailureResult(t.getMessage());
            }
        });
    }

    @Override
    public void makeNetworkCallForAllCountries(NetworkCallback callback) {
        Call<CountryListResponse> call = service.requestAllCountries();
        call.enqueue(new Callback<CountryListResponse>() {
            @Override
            public void onResponse(Call<CountryListResponse> call, Response<CountryListResponse> response) {
                callback.onSuccessAllCountryResponse(response.body().getCountries());
                Log.i(TAG, "onResponse: makeNetworkCallForAllCountry Category size Remote Date Source "+response.body().getCountries().get(0));
            }

            @Override
            public void onFailure(Call<CountryListResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: makeNetworkCallForAllCategory Category size Remote Date Source "+t.getMessage());
                callback.onFailureResult(t.getMessage());
            }
        });
    }

}
