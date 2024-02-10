package com.example.myapplication.homeActivity.model.allCountries;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Country implements Parcelable {
	private String strArea;
	private String strFlag;

	protected Country(Parcel in) {
		strArea = in.readString();
		strFlag = in.readString();
	}

	public static final Creator<Country> CREATOR = new Creator<Country>() {
		@Override
		public Country createFromParcel(Parcel in) {
			return new Country(in);
		}

		@Override
		public Country[] newArray(int size) {
			return new Country[size];
		}
	};

	public void setStrArea(String strArea){
		this.strArea = strArea;
	}

	public String getStrArea(){
		return strArea;
	}
	public void setStrFlag(String strFlag){
		this.strFlag = strFlag;
	}

	public String getStrFlag(){
		return strFlag;
	}
	@Override
 	public String toString(){
		return 
			"MealsItem{" + 
			"strArea = '" + strArea + '\'' +
			"strFlag = '" + strFlag + '\'' +
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel dest, int flags) {
		dest.writeString(strArea);
	}
}
