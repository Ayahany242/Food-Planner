package com.example.myapplication.homeActivity.model.allCountries;

import java.util.List;

public class CountryListResponse{
	private List<Country> meals;

	public void setCountries(List<Country> meals){
		this.meals = meals;
	}

	public List<Country> getCountries(){
		return meals;
	}

	@Override
 	public String toString(){
		return 
			"CountryListResponse{" + 
			"meals = '" + meals + '\'' +
			"}";
		}
}