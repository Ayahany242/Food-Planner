package com.example.myapplication.homeActivity.model.randomModel;

import java.util.List;

public class RandomMealResponse {
	private List<MealsItem> meals;

	public void setMeals(List<MealsItem> meals){
		this.meals = meals;
	}

	public List<MealsItem> getMeals(){
		return meals;
	}

	@Override
 	public String toString(){
		return 
			"RandomMeal{" + 
			"meals = '" + meals + '\'' + 
			"}";
		}
}