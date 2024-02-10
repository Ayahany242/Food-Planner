package com.example.myapplication.homeActivity.view;

import android.content.Context;

public interface HomeActivityCommunicator {
    public void showToast(String message);
    public Context getContext();
    public void goToMealDetails(String mealId);
    public void goToAllMealData(String contentName,String source);
    public void goToSearchResult(int destination);

}
