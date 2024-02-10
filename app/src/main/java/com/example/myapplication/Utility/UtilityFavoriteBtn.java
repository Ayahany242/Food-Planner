package com.example.myapplication.Utility;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.repository.RepositoryImpl;
import com.example.myapplication.repository.RepositoryView;
import com.example.myapplication.repository.localData.MealLocalDataSourceImpl;
import com.example.myapplication.repository.network.RemoteDataSourceImp;
import com.example.myapplication.repository.realTime.RealTimeImplementation;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UtilityFavoriteBtn implements FavoriteMealActions{
    private static final String TAG = "TAG";
    RepositoryView repositoryView;
    private RealTimeImplementation realTimeDB;
    Context context;
    private boolean isFavoriteMeal;
    public UtilityFavoriteBtn(Context context){
        this.context=context;
        repositoryView = RepositoryImpl.getInstance(RemoteDataSourceImp.getInstance(), MealLocalDataSourceImpl.getInstance(context));

    }
    @Override
    public void addFavoriteMeal(MealsItem item) {
        repositoryView.addMealToFavorite(item);
        realTimeDB =new RealTimeImplementation();
        realTimeDB.addFav(item);
        Log.i(TAG, "addFavoriteMeal: UtilityFavoriteBtn");
    }

    @Override
    public void deleteFavoriteMeal(MealsItem item) {
        repositoryView.deleteMealFromFavorite(item);
        Log.i(TAG, "deleteFavoriteMeal: UtilityFavoriteBtnr");
    }
    public Single<Boolean> isMealInFavorite(String idMeal) {
        return repositoryView.isMealExists(idMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private void setFavoriteResult(boolean result){
        this.isFavoriteMeal = result;
    }

}
