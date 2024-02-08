package com.example.myapplication.mealDetails.presenter;

import com.example.myapplication.Utility.FavoriteMealInterface;
import com.example.myapplication.homeActivity.model.mealData.MealsItem;

public class MealDetailContract {
    public interface View{
        public void showErrorMsg(String error);
        public void showMeal(MealsItem mealsItem);
        public void notifyMealExistence(boolean exists);
    }
    public interface Presenter extends FavoriteMealInterface {
        public void getMealDetails(String mealId);
    }

}
