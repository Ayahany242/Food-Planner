package com.example.myapplication.homeActivity.homeScreen.presenter;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.Utility.UtilityFavoriteBtn;
import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.repository.RepositoryView;
import com.example.myapplication.repository.network.NetworkCallback;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter , NetworkCallback.HomeRequestData {
    private static final String TAG = "TAG";
    private HomeContract.View view;
    private final RepositoryView repositoryView;
    private UtilityFavoriteBtn utilityFavoriteBtn;

    public HomePresenter(HomeContract.View view, RepositoryView repositoryView, Context context){
        this.view = view;
        this.repositoryView = repositoryView;
        utilityFavoriteBtn = new UtilityFavoriteBtn(context);
    }

    @Override
    public void getRandomMeal() {
        repositoryView.makeNetworkCallForRandomMeal(this);
        Log.i(TAG, "getRandomMeal: make Networks call ");
    }

    @Override
    public void getAllCategory() {
        repositoryView.makeNetworkCallForAllCategory(this);
        Log.i(TAG, "getAllCategory: make Networks call ");
    }

   /* @Override
    public void getAllCountries() {
        repositoryView.makeNetworkCallForAllCountries(this);
        Log.i(TAG, "getAllCountries: make Networks call ");
    }*/

    @Override
    public void onSuccessRandomResponse(MealsItem mealsItem) {
        view.showRandomMeal(mealsItem);
        Log.i(TAG, "onSuccessResult: Random Call presenter CategoryPresenter "+mealsItem.getIdMeal());
    }
    /*@Override
    public void onSuccessAllCountryResponse(List countries) {
        view.showAllCountries(countries);
        Log.i(TAG, "onSuccessResult: Random Call presenter onSuccessAllCountryResponse "+countries);
    }*/
    @Override
    public void onSuccessAllCategoryResponse(List categories) {
        view.showAllCategory(categories);
        Log.i(TAG, "onSuccessResult: Random Call presenter CategoryPresenter "+categories.size());
    }
    @Override
    public void onFailureResult(String errorMsg) {
        Log.i(TAG, "onFailureResult: Presenter "+errorMsg);
        view.showErrorMsg(errorMsg);
    }
    @Override
    public void addFavoriteMeal(MealsItem item) {
        utilityFavoriteBtn.addFavoriteMeal(item);
    }

    @Override
    public void deleteFavoriteMeal(MealsItem item) {
        utilityFavoriteBtn.deleteFavoriteMeal(item);
    }
    @Override
    public void isMealExists(String idMeal) {
        utilityFavoriteBtn.isMealInFavorite(idMeal)
                .subscribe(
                        exists -> {
                            // Notify the view (activity) about the result
                            view.notifyMealExistence(exists);
                        },
                        Throwable::printStackTrace
                );
    }
}
