package com.example.myapplication.homeActivity.planMealFragment.presenter;

import android.content.Context;

import com.example.myapplication.homeActivity.favoriteMealsFragment.presenter.FavoriteMealsContract;
import com.example.myapplication.homeActivity.planMealFragment.model.MealsPlan;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;
import com.example.myapplication.repository.RepositoryView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlannedMealPresenter implements PlannedMealsContract.Presenter{
    private static final String TAG = "TAG";
    private PlannedMealsContract.View view;
    private RepositoryView repositoryView;
    private HomeActivityCommunicator communicator;
    public PlannedMealPresenter(PlannedMealsContract.View view, RepositoryView repositoryView){
        this.view = view;
        this.repositoryView = repositoryView;
    }
    @Override
    public void deletePlannedMeal(MealsPlan mealsPlan) {
        repositoryView.deletePlannedMeal(mealsPlan);
    }

    @Override
    public void getAllPlannedMeal(String day) {
        repositoryView.getAllPlannedMeal(day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {
                            view.updatedUI(meals);
                        },
                        throwable -> view.showOnError(throwable.getMessage()));
    }
}
