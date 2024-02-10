package com.example.myapplication.homeActivity.searchFragment.presenter;
import android.util.Log;

import com.example.myapplication.homeActivity.model.allCountries.Country;
import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.homeActivity.searchFragment.model.IngredientsData;
import com.example.myapplication.repository.RepositoryView;
import com.example.myapplication.repository.network.NetworkCallback;

import java.util.List;

public class SearchPresenter implements SearchContract.Presenter , NetworkCallback.SearchRequestData {
    private static final String TAG = "search";
    private SearchContract.View view;
    private final RepositoryView repositoryView;

    public SearchPresenter(SearchContract.View view, RepositoryView repositoryView) {
        this.view = view;
        this.repositoryView = repositoryView;
    }

    @Override
    public void getAllIngredients() {
        repositoryView.makeNetworkCallForAllIngredients(this);
    }

    @Override
    public void getAllCountries() {
        repositoryView.makeNetworkCallForAllCountries(this);
        Log.i(TAG, "getAllCountries: make Networks call ");
    }

   /* @Override
    public void search(String name) {
        repositoryView.makeNetworkCallForSearch(this,name);
    }*/

    @Override
    public void onSuccessAllIngredientsResponse(List<IngredientsData> ingredientsData) {
        Log.i(TAG, "onSuccessAllIngredientsResponse: "+ingredientsData.size());
        view.showAllIngredients(ingredientsData);
    }

    @Override
    public void onSuccessAllCountryResponse(List<Country> countries) {
        view.showAllCountries(countries);
        Log.i(TAG, "onSuccessResult: Random Call presenter onSuccessAllCountryResponse search pre "+countries.size());
    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.i(TAG, "onFailureResult: search"+errorMsg);
        view.showErrorMsg(errorMsg);
    }
   /* @Override
    public void onSuccessMealSearchResponse(List<MealsItem> mealsItem) {
        view.showResult(mealsItem);
    }*/
}
