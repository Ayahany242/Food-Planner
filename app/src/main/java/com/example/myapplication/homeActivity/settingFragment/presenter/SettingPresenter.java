package com.example.myapplication.homeActivity.settingFragment.presenter;

import android.content.Context;

import com.example.myapplication.repository.RepositoryView;

public class SettingPresenter implements SettingContract.Presenter{
    private final RepositoryView repositoryView;
    private SettingContract.View view;
    public SettingPresenter(SettingContract.View view, RepositoryView repositoryView, Context context){
        this.view = view;
        this.repositoryView = repositoryView;
    }
    @Override
    public void deleteAllPlans() {
        repositoryView.deleteAllMeals();
        repositoryView.deleteAllMealsPlan();
    }

}
