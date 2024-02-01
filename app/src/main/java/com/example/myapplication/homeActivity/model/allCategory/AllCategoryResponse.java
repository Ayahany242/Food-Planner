package com.example.myapplication.homeActivity.model.allCategory;

import java.util.List;

public class AllCategoryResponse {
	private List<CategoriesItem> categories;

	public void setCategories(List<CategoriesItem> categories){
		this.categories = categories;
	}

	public List<CategoriesItem> getCategories(){
		return categories;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"categories = '" + categories + '\'' + 
			"}";
		}
}