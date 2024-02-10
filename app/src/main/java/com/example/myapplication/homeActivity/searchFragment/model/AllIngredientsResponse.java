package com.example.myapplication.homeActivity.searchFragment.model;

import java.util.List;

public class AllIngredientsResponse {
	private List<IngredientsData> meals;

	public void setMeals(List<IngredientsData> meals){
		this.meals = meals;
	}

	public List<IngredientsData> getMeals(){
		return meals;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"meals = '" + meals + '\'' + 
			"}";
		}
}