package com.example.myapplication.homeActivity.model.allCountries;

public class Country {
	private String strArea;

	public void setStrArea(String strArea){
		this.strArea = strArea;
	}

	public String getStrArea(){
		return strArea;
	}

	@Override
 	public String toString(){
		return 
			"MealsItem{" + 
			"strArea = '" + strArea + '\'' + 
			"}";
		}
}
