package com.example.myapplication.repository.localData;

import com.example.myapplication.homeActivity.model.randomModel.MealsItem;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface MealLocalDataSource {
    public Flowable<List<MealsItem>> getAllFavoriteMeal();
    void addToFavoriteMeal(MealsItem meal);
    void deleteFromFavoriteMeal(MealsItem meal);
    public Single<Boolean> isMealExists(String idMeal);
}
