package com.example.myapplication.homeActivity.searchFragment.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class IngredientsData  {
	private String strDescription;
	private String strIngredient;
	private Object strType;
	private String idIngredient;

	protected IngredientsData(Parcel in) {
		strDescription = in.readString();
		strIngredient = in.readString();
		idIngredient = in.readString();
	}

	public void setStrDescription(String strDescription){
		this.strDescription = strDescription;
	}

	public String getStrDescription(){
		return strDescription;
	}

	public void setStrIngredient(String strIngredient){
		this.strIngredient = strIngredient;
	}

	public String getStrIngredient(){
		return strIngredient;
	}

	public void setStrType(Object strType){
		this.strType = strType;
	}

	public Object getStrType(){
		return strType;
	}

	public void setIdIngredient(String idIngredient){
		this.idIngredient = idIngredient;
	}

	public String getIdIngredient(){
		return idIngredient;
	}

	@Override
 	public String toString(){
		return 
			"MealsItem{" + 
			"strDescription = '" + strDescription + '\'' + 
			",strIngredient = '" + strIngredient + '\'' + 
			",strType = '" + strType + '\'' + 
			",idIngredient = '" + idIngredient + '\'' + 
			"}";
		}

}
