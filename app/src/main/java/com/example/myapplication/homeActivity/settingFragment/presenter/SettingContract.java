package com.example.myapplication.homeActivity.settingFragment.presenter;

import com.example.myapplication.homeActivity.planMealFragment.model.MealsPlan;

import java.util.List;

public interface SettingContract {
    interface View{
        //public void updatedUI(List<MealsPlan> mealsPlans);
        public void showOnError(String error);
    }
    interface Presenter{
        public void deleteAllPlans();
    }

}
