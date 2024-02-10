package com.example.myapplication.homeActivity.planMealFragment.presenter;

import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.homeActivity.planMealFragment.model.MealsPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface PlannedMealsContract {
    interface View{
        public void updatedUI(List<MealsPlan> mealsPlans);
        public void showOnError(String error);
    }
    interface Presenter{
        public void deletePlannedMeal(MealsPlan mealsPlan);
        public void getAllPlannedMeal(String day);
    }
}
