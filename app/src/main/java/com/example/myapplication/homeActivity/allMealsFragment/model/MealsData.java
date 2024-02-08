package com.example.myapplication.homeActivity.allMealsFragment.model;

public class MealsData {
	private String strMealThumb;
	private String idMeal;
	private String strMeal;

	public void setStrMealThumb(String strMealThumb){
		this.strMealThumb = strMealThumb;
	}

	public String getStrMealThumb(){
		return strMealThumb;
	}

	public void setIdMeal(String idMeal){
		this.idMeal = idMeal;
	}

	public String getIdMeal(){
		return idMeal;
	}

	public void setStrMeal(String strMeal){
		this.strMeal = strMeal;
	}

	public String getStrMeal(){
		return strMeal;
	}

	@Override
 	public String toString(){
		return 
			"MealsItem{" + 
			"strMealThumb = '" + strMealThumb + '\'' + 
			",idMeal = '" + idMeal + '\'' + 
			",strMeal = '" + strMeal + '\'' + 
			"}";
		}
}
