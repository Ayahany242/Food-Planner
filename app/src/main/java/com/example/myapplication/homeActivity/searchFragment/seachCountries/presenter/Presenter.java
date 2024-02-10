package com.example.myapplication.homeActivity.searchFragment.seachCountries.presenter;

import android.util.Log;

import com.example.myapplication.homeActivity.model.allCountries.Country;
import com.example.myapplication.homeActivity.searchFragment.searchIngredients.presenter.SearchResultContract;
import com.example.myapplication.mealDetails.model.IngredientModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Presenter implements SearchResultContract.Presenter<Country> {
    private SearchResultContract.View view;
    private List<Country> countries;
    public Presenter(SearchResultContract.View view, List<Country> countries){
        this.view = view;
        this.countries = countries;
        Log.i("TAG", "Presenter: ingredientModels size "+countries.size());
    }
    @Override
    public void search(String name) {
        List<Country> result = new ArrayList<>();
        Observable<Country> observable = Observable.fromIterable(countries);
        observable.subscribeOn(Schedulers.io())
                .filter((Country model )-> model.getStrArea().contains(name))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        country -> {
                            result.add(country);
                        },
                        throwable -> view.showError(throwable.getMessage()),
                        ()-> view.updateUI(result));
    }

    @Override
    public void setModelList(List<Country> countries) {
        this.countries = countries;
    }
}
