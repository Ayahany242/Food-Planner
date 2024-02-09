package com.example.myapplication.mealDetails.presenter;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.Utility.UtilityFavoriteBtn;
import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.homeActivity.allMealsFragment.model.MealsData;
import com.example.myapplication.homeActivity.planMealFragment.model.MealsPlan;
import com.example.myapplication.repository.RepositoryView;
import com.example.myapplication.repository.network.NetworkCallback;

import java.util.List;

public class MealDetailPresenter implements MealDetailContract.Presenter, NetworkCallback.MealsDetailsRequest {
    private static final String TAG ="details";
    private MealDetailContract.View view;
    private RepositoryView repositoryView;
    private UtilityFavoriteBtn utilityFavoriteBtn;
    public MealDetailPresenter(MealDetailContract.View view, RepositoryView repositoryView, Context context){
        this.view = view;
        this.repositoryView = repositoryView;
        utilityFavoriteBtn = new UtilityFavoriteBtn(context);
    }
    @Override
    public void getMealDetails(String mealId) {
        repositoryView.makeNetworkCallMealDetails(this,mealId);
    }

    @Override
    public void addToPlannedMeal(MealsPlan mealsPlan) {
        repositoryView.addPlannedMeal(mealsPlan);
        Log.i(TAG, "addToPlannedMeal:MealDetailPresenter  "+mealsPlan.getIdMeal() +"...."+mealsPlan.getDateWithDayOfWeek());
    }

    @Override
    public void onSuccessMealDetailsResponse(MealsItem mealsItem) {
        view.showMeal(mealsItem);
    }

    @Override
    public void onFailureResult(String errorMsg) {
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
