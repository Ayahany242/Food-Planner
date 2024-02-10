package com.example.myapplication.repository.localData;

import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.homeActivity.planMealFragment.model.MealsPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface MealLocalDataSource {
    public Flowable<List<MealsItem>> getAllFavoriteMeal();
    void addToFavoriteMeal(MealsItem meal);
    void deleteFromFavoriteMeal(MealsItem meal);
    public Single<Boolean> isMealExists(String idMeal);
    Flowable<List<MealsPlan>> getAllPlannedMeal(String day);
    public void addPlannedMeal(MealsPlan mealsPlan);
    public void deletePlannedMeal(MealsPlan mealsPlan);
    public void deleteAllMeals();
    public void deleteAllMealsPlan();
}
