package com.example.myapplication.homeActivity.searchFragment.searchByName.presenter;

import com.example.myapplication.homeActivity.model.mealData.MealsItem;

import java.util.List;

public interface SearchByNameContract {
    interface View{
        public void showResult(List<MealsItem> mealsItems);
        public void showErrorMsg(String error);
    }
    interface Presenter{
        public void search(String name);
    }
}
