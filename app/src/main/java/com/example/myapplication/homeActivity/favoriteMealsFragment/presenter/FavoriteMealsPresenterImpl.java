package com.example.myapplication.homeActivity.favoriteMealsFragment.presenter;

import android.content.Context;

import com.example.myapplication.Utility.FavoriteMealInterface;
import com.example.myapplication.Utility.UtilityFavoriteBtn;
import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.repository.RepositoryView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteMealsPresenterImpl implements FavoriteMealsContract.Presenter , FavoriteMealInterface {
    private static final String TAG = "TAG";
    private FavoriteMealsContract.View view;
    private  RepositoryView repositoryView;
    private UtilityFavoriteBtn utilityFavoriteBtn;
    private boolean isFavoriteMeal;
    public FavoriteMealsPresenterImpl(FavoriteMealsContract.View view, RepositoryView repositoryView, Context context){
        this.view = view;
        this.repositoryView = repositoryView;
        utilityFavoriteBtn = new UtilityFavoriteBtn(context);
    }
    @Override
    public void getAllFavoriteMeals() {
        repositoryView.getAllFavoriteMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {
                            view.showAllFavoriteMeals(meals);
                        },
                        throwable -> view.showErrorMsg(throwable.getMessage()));
    }
    @Override
    public void addFavoriteMeal(MealsItem item) {
        utilityFavoriteBtn.addFavoriteMeal(item);
    }

    @Override
    public void deleteFavoriteMeal(MealsItem item) {
        utilityFavoriteBtn.deleteFavoriteMeal(item);
    }

    public void isMealExists(String idMeal) {
        utilityFavoriteBtn.isMealInFavorite(idMeal)
                .subscribe(
                        exists -> {
                            // Notify the view (activity) about the result
                            view.notifyMealExistence(exists);
                        },
                        Throwable::printStackTrace
                );
    }
}
