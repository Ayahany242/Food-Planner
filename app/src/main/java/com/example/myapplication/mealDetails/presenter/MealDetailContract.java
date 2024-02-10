package com.example.myapplication.mealDetails.presenter;

import com.example.myapplication.Utility.FavoriteMealInterface;
import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.homeActivity.planMealFragment.model.MealsPlan;
public class MealDetailContract {
    public interface View{
        public void showErrorMsg(String error);
        public void showMeal(MealsItem mealsItem);
        public void notifyMealExistence(boolean exists);
        public void addToPlannedMeal(MealsPlan mealsPlan);
    }
    public interface Presenter extends FavoriteMealInterface {
        public void getMealDetails(String mealId);
        public void addToPlannedMeal(MealsPlan mealsPlan);
    }
}
