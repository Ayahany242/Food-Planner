package com.example.myapplication.repository.localData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.homeActivity.model.randomModel.MealsItem;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface MealDao {
    @Query("SELECT * FROM MEALTABLE")
    public Flowable<List<MealsItem>> getAllFavoriteMeal();
    @Query("Select idMeal FROM MEALTABLE WHERE idMeal =:idMeal")
    public Single<String> getMealId(String idMeal);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavoriteMeal(MealsItem meal);
    @Delete
    void deleteMealFromFavorite(MealsItem meal);
}
