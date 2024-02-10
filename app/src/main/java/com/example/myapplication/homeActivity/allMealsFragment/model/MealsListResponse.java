package com.example.myapplication.homeActivity.allMealsFragment.model;

import java.util.List;

public class MealsListResponse{
	private List<MealsData> meals;
	public void setMeals(List<MealsData> meals){
		this.meals = meals;
	}
	public List<MealsData> getMeals(){
		return meals;
	}
	@Override
 	public String toString(){
		return 
			"MealsListResponse{" + 
			"meals = '" + meals + '\'' + 
			"}";
		}
}