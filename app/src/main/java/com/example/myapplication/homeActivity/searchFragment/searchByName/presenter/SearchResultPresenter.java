package com.example.myapplication.homeActivity.searchFragment.searchByName.presenter;

import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.repository.RepositoryView;
import com.example.myapplication.repository.network.NetworkCallback;

import java.util.List;

public class SearchResultPresenter implements SearchByNameContract.Presenter , NetworkCallback.ResultSearchByName {
    private SearchByNameContract.View view;
    private final RepositoryView repositoryView;

    public SearchResultPresenter(SearchByNameContract.View view, RepositoryView repositoryView) {
        this.view = view;
        this.repositoryView = repositoryView;
    }
    @Override
    public void search(String name) {
        repositoryView.makeNetworkCallForSearchByName(this,name);
    }

    @Override
    public void onSuccessMealSearchResponse(List<MealsItem> mealsItem) {
        view.showResult(mealsItem);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        view.showErrorMsg(errorMsg);
    }
}
