package com.example.myapplication.homeActivity.searchFragment.searchIngredients.presenter;

import android.util.Log;

import com.example.myapplication.mealDetails.model.IngredientModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Presenter implements SearchResultContract.Presenter <IngredientModel>{
    private SearchResultContract.View view;
    private List<IngredientModel> ingredientModels;
    public Presenter(SearchResultContract.View view, List<IngredientModel> ingredientModels){
        this.view = view;
        this.ingredientModels = ingredientModels;
        Log.i("TAG", "Presenter: ingredientModels size "+ingredientModels.size());
    }
    public void setModelList(List<IngredientModel> ingredientModels){
        this.ingredientModels = ingredientModels;
    }
    @Override
    public void search(String name) {
        List<IngredientModel> result = new ArrayList<>();
        Observable<IngredientModel> observable = Observable.fromIterable(ingredientModels);
        observable.subscribeOn(Schedulers.io())
                .filter((IngredientModel model )-> model.getName().contains(name))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ingredient -> {
                            result.add(ingredient);
                        },
                        throwable -> view.showError(throwable.getMessage()),
                        ()-> view.updateUI(result));
    }
}
