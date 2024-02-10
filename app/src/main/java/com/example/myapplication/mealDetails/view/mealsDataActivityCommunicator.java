package com.example.myapplication.mealDetails.view;

import android.content.Context;

public interface mealsDataActivityCommunicator {
    public void showToast(String message);
    public Context getContext();
    public String getContent(String content);
}
