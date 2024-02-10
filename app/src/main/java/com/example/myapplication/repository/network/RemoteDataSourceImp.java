package com.example.myapplication.repository.network;

import android.util.Log;

import com.example.myapplication.homeActivity.model.allCategory.AllCategoryResponse;
import com.example.myapplication.homeActivity.model.allCountries.CountryListResponse;
import com.example.myapplication.homeActivity.model.mealData.MealResponse;
import com.example.myapplication.homeActivity.allMealsFragment.model.MealsListResponse;
import com.example.myapplication.homeActivity.searchFragment.model.AllIngredientsResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
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
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        service = retrofit.create(API_Service.class);
    }
    public static RemoteDataSourceImp getInstance(){
        if (client == null)
            client = new RemoteDataSourceImp();
        return client;
    }
    @Override
    public void makeNetworkCallForRandomMeal(NetworkCallback.HomeRequestData  callbackRandomMeal) {
        Single<MealResponse> call = service.requestRandomMeal();
                call.subscribeOn(Schedulers.io())
                .map(response ->response.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> callbackRandomMeal.onSuccessRandomResponse(item.get(0)),
                        throwable->callbackRandomMeal.onFailureResult(throwable.getMessage()));
    }
    @Override
    public void makeNetworkCallForAllCategory(NetworkCallback.HomeRequestData  callback) {
        Single<AllCategoryResponse> call = service.requestAllCategory();
        call.subscribeOn(Schedulers.io())
                .map(response ->response.getCategories())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> callback.onSuccessAllCategoryResponse(item),
                        throwable->callback.onFailureResult(throwable.getMessage()));
    }

    @Override
    public void makeNetworkCallForSearchByName(NetworkCallback.ResultSearchByName callback, String text) {
        Flowable<MealResponse> call = service.requestSearch(text);
        call.subscribeOn(Schedulers.io())
                .debounce(500, TimeUnit.MILLISECONDS)
                .map(mealResponse -> mealResponse.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealsItems -> callback.onSuccessMealSearchResponse(mealsItems),
                        throwable -> callback.onFailureResult(throwable.getMessage()),
                        ()-> Log.i(TAG, "makeNetworkCallForSearch: search complete ")
                );
    }

    /* @Override
     public void makeNetworkCallForAllCountries(NetworkCallback.HomeRequestData  callback) {
         Single<CountryListResponse> call = service.requestAllCountries();
         call.subscribeOn(Schedulers.io())
                 .map(response ->response.getCountries())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(
                         item -> callback.onSuccessAllCountryResponse(item),
                         throwable->callback.onFailureResult(throwable.getMessage()));
     }*/
//search
    @Override
    public void makeNetworkCallForAllCountries(NetworkCallback.SearchRequestData callback) {
        Single<CountryListResponse> call = service.requestAllCountries();
        call.subscribeOn(Schedulers.io())
                .map(response ->response.getCountries())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> callback.onSuccessAllCountryResponse(item),
                        throwable->callback.onFailureResult(throwable.getMessage()));
    }

    @Override
    public void makeNetworkCallForAllIngredients(NetworkCallback.SearchRequestData callback) {
       Single<AllIngredientsResponse> call = service.requestAllIngredients();
       call.subscribeOn(Schedulers.io())
               .map(response -> response.getMeals())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                       ingredientsData -> callback.onSuccessAllIngredientsResponse(ingredientsData),
                       throwable -> callback.onFailureResult(throwable.getMessage())
               );
    }

    @Override
    public void makeNetworkCallMealsDataInCategory(NetworkCallback.MealsDataRequest callback, String category) {
        Single<MealsListResponse> call = service.requestAllMealsInCategory(category);
        call.subscribeOn(Schedulers.io())
                .map(response ->response.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> callback.onSuccessMealsDataResponse(meals),
                        throwable->callback.onFailureResult(throwable.getMessage()));
    }

    @Override
    public void makeNetworkCallMealsDataInArea(NetworkCallback.MealsDataRequest callback, String area) {
        Single<MealsListResponse> call = service.requestAllMealsInArea(area);
        call.subscribeOn(Schedulers.io())
                .map(response ->response.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> callback.onSuccessMealsDataResponse(meals),
                        throwable->callback.onFailureResult(throwable.getMessage()));
    }

    @Override
    public void makeNetworkCallMealsDataInIngredient(NetworkCallback.MealsDataRequest callback, String ingredient) {
        Single<MealsListResponse> call = service.requestFilterByIngredients(ingredient);
        call.subscribeOn(Schedulers.io())
                .map(response ->response.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> callback.onSuccessMealsDataResponse(meals),
                        throwable->callback.onFailureResult(throwable.getMessage()));
    }

    @Override
    public void makeNetworkCallMealDetails(NetworkCallback.MealsDetailsRequest callback, String mealId) {
        Single<MealResponse> call = service.requestMealDetail(mealId);
        call.subscribeOn(Schedulers.io()).map(response -> response.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealData->callback.onSuccessMealDetailsResponse(mealData.get(0)),
                        throwable -> callback.onFailureResult(throwable.getMessage())
                );
    }
}
