package com.example.myapplication.homeActivity.allMealsFragment.presenter;

import android.content.Context;

import com.example.myapplication.Utility.UtilityFavoriteBtn;
import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.homeActivity.allMealsFragment.model.MealsData;
import com.example.myapplication.homeActivity.searchFragment.searchIngredients.presenter.SearchResultContract;
import com.example.myapplication.mealDetails.model.IngredientModel;
import com.example.myapplication.repository.RepositoryView;
import com.example.myapplication.repository.network.NetworkCallback;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AllMealPresenter implements AllMealContract.Presenter, NetworkCallback.MealsDataRequest {
    private AllMealContract.View view;
    private RepositoryView repositoryView;
    private List<MealsData> mealsDataList;
    private UtilityFavoriteBtn utilityFavoriteBtn;

    public AllMealPresenter(AllMealContract.View view, RepositoryView repositoryView, List<MealsData> mealsDataList){
        this.view = view;
        this.repositoryView = repositoryView;
        this.mealsDataList = mealsDataList;
    }
    @Override
    public void getAllMealCountry(String country) {
        repositoryView.makeNetworkCallMealsDataInArea(this,country);
    }

    @Override
    public void getAllMealMealsIngredients(String ingredients) {
        repositoryView.makeNetworkCallMealsDataInIngredient(this,ingredients);
    }

    @Override
    public void getAllMealCategory(String category) {
        repositoryView.makeNetworkCallMealsDataInCategory(this,category);
    }
    @Override
    public void onSuccessMealsDataResponse(List<MealsData> allMealsInCategory) {
        view.showAllMeal(allMealsInCategory);
    }
    @Override
    public void onFailureResult(String errorMsg) {
        view.showErrorMsg(errorMsg);
    }

    @Override
    public void search(String name) {
        List<MealsData> result = new ArrayList<>();
        Observable<MealsData> observable = Observable.fromIterable(mealsDataList);
        observable.subscribeOn(Schedulers.io())
                .filter((MealsData model )-> model.getStrMeal().contains(name))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ingredient -> {
                            result.add(ingredient);
                        },
                        throwable -> view.showError(throwable.getMessage()),
                        ()-> view.updateUI(result));
    }

    @Override
    public void setModelList(List<MealsData> mealsDataList) {
        this.mealsDataList = mealsDataList;
    }
}
