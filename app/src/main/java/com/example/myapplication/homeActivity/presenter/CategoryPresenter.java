package com.example.myapplication.homeActivity.presenter;

import android.util.Log;

import com.example.myapplication.homeActivity.model.allCategory.CategoriesItem;
import com.example.myapplication.homeActivity.model.randomModel.MealsItem;
import com.example.myapplication.repository.RepositoryView;
import com.example.myapplication.repository.network.NetworkCallback;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class CategoryPresenter implements CategoryContract.Presenter , NetworkCallback {
    private static final String TAG = "TAG";
    private CategoryContract.View view;
    private final RepositoryView repositoryView;
    private boolean isFavoriteMeal;
    public CategoryPresenter(CategoryContract.View randomMeal, RepositoryView repositoryView){
        this.view = randomMeal;
        this.repositoryView = repositoryView;
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

    @Override
    public void getAllCountries() {
        repositoryView.makeNetworkCallForAllCountries(this);
        Log.i(TAG, "getAllCountries: make Networks call ");
    }


    @Override
    public void addFavoriteMeal(MealsItem item) {
        repositoryView.addMealToFavorite(item);
        Log.i(TAG, "addFavoriteMeal: Category Presenter");
    }

    @Override
    public void deleteFavoriteMeal(MealsItem item) {
        repositoryView.deleteMealFromFavorite(item);
        Log.i(TAG, "deleteFavoriteMeal: Category Presenter");
    }
    public void isMealInFavorite(String idMeal) {
        repositoryView.isMealExists(idMeal).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        exists -> {
                            if (exists) {
                                Log.i(TAG,"Meal with ID " + idMeal + " exists in the database."+exists);
                                setFavoriteResult(exists);
                            } else {
                                Log.i(TAG,"Meal with ID " + idMeal + " does not exist in the database."+exists);
                                setFavoriteResult(exists);
                            }
                        },
                        Throwable::printStackTrace
                );
    }
    @Override
    public boolean isMealExists(String idMeal) {
        isMealInFavorite(idMeal);
        Log.i(TAG, "isMealExists: "+isFavoriteMeal);
        return isFavoriteMeal;
    }

    @Override
    public void onSuccessRandomResponse(MealsItem mealsItem) {
        view.showRandomMeal(mealsItem);
        Log.i(TAG, "onSuccessResult: Random Call presenter CategoryPresenter "+mealsItem.getIdMeal());
    }
    private void setFavoriteResult(boolean result){
        this.isFavoriteMeal = result;
    }
    @Override
    public void onSuccessAllCountryResponse(List countries) {
        view.showAllCountries(countries);
        Log.i(TAG, "onSuccessResult: Random Call presenter onSuccessAllCountryResponse "+countries);
    }

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
}
