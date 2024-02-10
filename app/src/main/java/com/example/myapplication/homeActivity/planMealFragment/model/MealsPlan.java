package com.example.myapplication.homeActivity.planMealFragment.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.myapplication.homeActivity.model.mealData.MealsItem;

@Entity(tableName = "mealsPlanTable" ,primaryKeys = {"idMeal","dateWithDayOfWeek"})
public class MealsPlan {
	@NonNull
	private String dateWithDayOfWeek;
	private String strImageSource;
	private String strIngredient10;
	private String strIngredient12;
	private String strIngredient11;
	private String strIngredient14;
	private String strCategory;
	private String strIngredient13;
	private String strIngredient16;
	private String strIngredient15;
	private String strIngredient18;
	private String strIngredient17;
	private String strArea;
	private String strCreativeCommonsConfirmed;
	private String strIngredient19;
	private String strTags;
	@NonNull
	private String idMeal;
	private String strInstructions;
	private String strIngredient1;
	private String strIngredient3;
	private String strIngredient2;
	private String strIngredient20;
	private String strIngredient5;
	private String strIngredient4;
	private String strIngredient7;
	private String strIngredient6;
	private String strIngredient9;
	private String strIngredient8;
	private String strMealThumb;
	private String strMeasure20;
	private String strYoutube;
	private String strMeal;
	private String strMeasure12;
	private String strMeasure13;
	private String strMeasure10;
	private String strMeasure11;
	private String dateModified;
	private String strDrinkAlternate;
	private String strSource;
	private String strMeasure9;
	private String strMeasure7;
	private String strMeasure8;
	private String strMeasure5;
	private String strMeasure6;
	private String strMeasure3;
	private String strMeasure4;
	private String strMeasure1;
	private String strMeasure18;
	private String strMeasure2;
	private String strMeasure19;
	private String strMeasure16;
	private String strMeasure17;
	private String strMeasure14;
	private String strMeasure15;
	public MealsPlan(){}
	public void setStrImageSource(String  strImageSource){
		this.strImageSource = strImageSource;
	}

	public String getStrImageSource(){
		return strImageSource;
	}

	public void setStrIngredient10(String strIngredient10){
		this.strIngredient10 = strIngredient10;
	}

	public String getStrIngredient10(){
		return strIngredient10;
	}

	public void setStrIngredient12(String strIngredient12){
		this.strIngredient12 = strIngredient12;
	}

	public String getStrIngredient12(){
		return strIngredient12;
	}

	public void setStrIngredient11(String strIngredient11){
		this.strIngredient11 = strIngredient11;
	}

	public String getStrIngredient11(){
		return strIngredient11;
	}

	public void setStrIngredient14(String strIngredient14){
		this.strIngredient14 = strIngredient14;
	}

	public String getStrIngredient14(){
		return strIngredient14;
	}

	public void setStrCategory(String strCategory){
		this.strCategory = strCategory;
	}

	public String getStrCategory(){
		return strCategory;
	}

	public void setStrIngredient13(String strIngredient13){
		this.strIngredient13 = strIngredient13;
	}

	public String getStrIngredient13(){
		return strIngredient13;
	}

	public void setStrIngredient16(String strIngredient16){
		this.strIngredient16 = strIngredient16;
	}

	public String getStrIngredient16(){
		return strIngredient16;
	}

	public void setStrIngredient15(String strIngredient15){
		this.strIngredient15 = strIngredient15;
	}

	public String getStrIngredient15(){
		return strIngredient15;
	}

	public void setStrIngredient18(String strIngredient18){
		this.strIngredient18 = strIngredient18;
	}

	public String getStrIngredient18(){
		return strIngredient18;
	}

	public void setStrIngredient17(String strIngredient17){
		this.strIngredient17 = strIngredient17;
	}

	public String getStrIngredient17(){
		return strIngredient17;
	}

	public void setStrArea(String strArea){
		this.strArea = strArea;
	}

	public String getStrArea(){
		return strArea;
	}

	public void setStrCreativeCommonsConfirmed(String strCreativeCommonsConfirmed){
		this.strCreativeCommonsConfirmed = strCreativeCommonsConfirmed;
	}

	public String getStrCreativeCommonsConfirmed(){
		return strCreativeCommonsConfirmed;
	}

	public void setStrIngredient19(String strIngredient19){
		this.strIngredient19 = strIngredient19;
	}

	public String getStrIngredient19(){
		return strIngredient19;
	}

	public void setStrTags(String strTags){
		this.strTags = strTags;
	}

	public String getStrTags(){
		return strTags;
	}

	public void setIdMeal(String idMeal){
		this.idMeal = idMeal;
	}

	public String getIdMeal(){
		return idMeal;
	}

	public void setStrInstructions(String strInstructions){
		this.strInstructions = strInstructions;
	}

	public String getStrInstructions(){
		return strInstructions;
	}

	public void setStrIngredient1(String strIngredient1){
		this.strIngredient1 = strIngredient1;
	}

	public String getStrIngredient1(){
		return strIngredient1;
	}

	public void setStrIngredient3(String strIngredient3){
		this.strIngredient3 = strIngredient3;
	}

	public String getStrIngredient3(){
		return strIngredient3;
	}

	public void setStrIngredient2(String strIngredient2){
		this.strIngredient2 = strIngredient2;
	}

	public String getStrIngredient2(){
		return strIngredient2;
	}

	public void setStrIngredient20(String strIngredient20){
		this.strIngredient20 = strIngredient20;
	}

	public String getStrIngredient20(){
		return strIngredient20;
	}

	public void setStrIngredient5(String strIngredient5){
		this.strIngredient5 = strIngredient5;
	}

	public String getStrIngredient5(){
		return strIngredient5;
	}

	public void setStrIngredient4(String strIngredient4){
		this.strIngredient4 = strIngredient4;
	}

	public String getStrIngredient4(){
		return strIngredient4;
	}

	public void setStrIngredient7(String strIngredient7){
		this.strIngredient7 = strIngredient7;
	}

	public String getStrIngredient7(){
		return strIngredient7;
	}

	public void setStrIngredient6(String strIngredient6){
		this.strIngredient6 = strIngredient6;
	}

	public String getStrIngredient6(){
		return strIngredient6;
	}

	public void setStrIngredient9(String strIngredient9){
		this.strIngredient9 = strIngredient9;
	}

	public String getStrIngredient9(){
		return strIngredient9;
	}

	public void setStrIngredient8(String strIngredient8){
		this.strIngredient8 = strIngredient8;
	}

	public String getStrIngredient8(){
		return strIngredient8;
	}

	public void setStrMealThumb(String strMealThumb){
		this.strMealThumb = strMealThumb;
	}

	public String getStrMealThumb(){
		return strMealThumb;
	}

	public void setStrMeasure20(String strMeasure20){
		this.strMeasure20 = strMeasure20;
	}

	public String getStrMeasure20(){
		return strMeasure20;
	}

	public void setStrYoutube(String strYoutube){
		this.strYoutube = strYoutube;
	}

	public String getStrYoutube(){
		return strYoutube;
	}

	public void setStrMeal(String strMeal){
		this.strMeal = strMeal;
	}

	public String getStrMeal(){
		return strMeal;
	}

	public void setStrMeasure12(String strMeasure12){
		this.strMeasure12 = strMeasure12;
	}

	public String getStrMeasure12(){
		return strMeasure12;
	}

	public void setStrMeasure13(String strMeasure13){
		this.strMeasure13 = strMeasure13;
	}

	public String getStrMeasure13(){
		return strMeasure13;
	}

	public void setStrMeasure10(String strMeasure10){
		this.strMeasure10 = strMeasure10;
	}

	public String getStrMeasure10(){
		return strMeasure10;
	}

	public void setStrMeasure11(String strMeasure11){
		this.strMeasure11 = strMeasure11;
	}

	public String getStrMeasure11(){
		return strMeasure11;
	}

	public void setDateModified(String dateModified){
		this.dateModified = dateModified;
	}

	public String getDateModified(){
		return dateModified;
	}

	public void setStrDrinkAlternate(String strDrinkAlternate){
		this.strDrinkAlternate = strDrinkAlternate;
	}

	public String getStrDrinkAlternate(){
		return strDrinkAlternate;
	}

	public void setStrSource(String strSource){
		this.strSource = strSource;
	}

	public String getStrSource(){
		return strSource;
	}

	public void setStrMeasure9(String strMeasure9){
		this.strMeasure9 = strMeasure9;
	}

	public String getStrMeasure9(){
		return strMeasure9;
	}

	public void setStrMeasure7(String strMeasure7){
		this.strMeasure7 = strMeasure7;
	}

	public String getStrMeasure7(){
		return strMeasure7;
	}

	public void setStrMeasure8(String strMeasure8){
		this.strMeasure8 = strMeasure8;
	}

	public String getStrMeasure8(){
		return strMeasure8;
	}

	public void setStrMeasure5(String strMeasure5){
		this.strMeasure5 = strMeasure5;
	}

	public String getStrMeasure5(){
		return strMeasure5;
	}

	public void setStrMeasure6(String strMeasure6){
		this.strMeasure6 = strMeasure6;
	}

	public String getStrMeasure6(){
		return strMeasure6;
	}

	public void setStrMeasure3(String strMeasure3){
		this.strMeasure3 = strMeasure3;
	}

	public String getStrMeasure3(){
		return strMeasure3;
	}

	public void setStrMeasure4(String strMeasure4){
		this.strMeasure4 = strMeasure4;
	}

	public String getStrMeasure4(){
		return strMeasure4;
	}

	public void setStrMeasure1(String strMeasure1){
		this.strMeasure1 = strMeasure1;
	}

	public String getStrMeasure1(){
		return strMeasure1;
	}

	public void setStrMeasure18(String strMeasure18){
		this.strMeasure18 = strMeasure18;
	}

	public String getStrMeasure18(){
		return strMeasure18;
	}

	public void setStrMeasure2(String strMeasure2){
		this.strMeasure2 = strMeasure2;
	}

	public String getStrMeasure2(){
		return strMeasure2;
	}

	public void setStrMeasure19(String strMeasure19){
		this.strMeasure19 = strMeasure19;
	}

	public String getStrMeasure19(){
		return strMeasure19;
	}

	public void setStrMeasure16(String strMeasure16){
		this.strMeasure16 = strMeasure16;
	}

	public String getStrMeasure16(){
		return strMeasure16;
	}

	public void setStrMeasure17(String strMeasure17){
		this.strMeasure17 = strMeasure17;
	}

	public String getStrMeasure17(){
		return strMeasure17;
	}

	public void setStrMeasure14(String strMeasure14){
		this.strMeasure14 = strMeasure14;
	}

	public String getStrMeasure14(){
		return strMeasure14;
	}

	public void setStrMeasure15(String strMeasure15){
		this.strMeasure15 = strMeasure15;
	}

	public String getStrMeasure15(){
		return strMeasure15;
	}

	public String getDateWithDayOfWeek() {
		return dateWithDayOfWeek;
	}

	public void setDateWithDayOfWeek(String dateWithDayOfWeek) {
		this.dateWithDayOfWeek = dateWithDayOfWeek;
	}

	@Override
 	public String toString(){
		return 
			"MealsItem{" +
			"dateWithDayOfWeek = '" + dateWithDayOfWeek +'\'' +
			"strImageSource = '" + strImageSource + '\'' + 
			",strIngredient10 = '" + strIngredient10 + '\'' + 
			",strIngredient12 = '" + strIngredient12 + '\'' + 
			",strIngredient11 = '" + strIngredient11 + '\'' + 
			",strIngredient14 = '" + strIngredient14 + '\'' + 
			",strCategory = '" + strCategory + '\'' + 
			",strIngredient13 = '" + strIngredient13 + '\'' + 
			",strIngredient16 = '" + strIngredient16 + '\'' + 
			",strIngredient15 = '" + strIngredient15 + '\'' + 
			",strIngredient18 = '" + strIngredient18 + '\'' + 
			",strIngredient17 = '" + strIngredient17 + '\'' + 
			",strArea = '" + strArea + '\'' + 
			",strCreativeCommonsConfirmed = '" + strCreativeCommonsConfirmed + '\'' + 
			",strIngredient19 = '" + strIngredient19 + '\'' + 
			",strTags = '" + strTags + '\'' + 
			",idMeal = '" + idMeal + '\'' + 
			",strInstructions = '" + strInstructions + '\'' + 
			",strIngredient1 = '" + strIngredient1 + '\'' + 
			",strIngredient3 = '" + strIngredient3 + '\'' + 
			",strIngredient2 = '" + strIngredient2 + '\'' + 
			",strIngredient20 = '" + strIngredient20 + '\'' + 
			",strIngredient5 = '" + strIngredient5 + '\'' + 
			",strIngredient4 = '" + strIngredient4 + '\'' + 
			",strIngredient7 = '" + strIngredient7 + '\'' + 
			",strIngredient6 = '" + strIngredient6 + '\'' + 
			",strIngredient9 = '" + strIngredient9 + '\'' + 
			",strIngredient8 = '" + strIngredient8 + '\'' + 
			",strMealThumb = '" + strMealThumb + '\'' + 
			",strMeasure20 = '" + strMeasure20 + '\'' + 
			",strYoutube = '" + strYoutube + '\'' + 
			",strMeal = '" + strMeal + '\'' + 
			",strMeasure12 = '" + strMeasure12 + '\'' + 
			",strMeasure13 = '" + strMeasure13 + '\'' + 
			",strMeasure10 = '" + strMeasure10 + '\'' + 
			",strMeasure11 = '" + strMeasure11 + '\'' + 
			",dateModified = '" + dateModified + '\'' + 
			",strDrinkAlternate = '" + strDrinkAlternate + '\'' + 
			",strSource = '" + strSource + '\'' + 
			",strMeasure9 = '" + strMeasure9 + '\'' + 
			",strMeasure7 = '" + strMeasure7 + '\'' + 
			",strMeasure8 = '" + strMeasure8 + '\'' + 
			",strMeasure5 = '" + strMeasure5 + '\'' + 
			",strMeasure6 = '" + strMeasure6 + '\'' + 
			",strMeasure3 = '" + strMeasure3 + '\'' + 
			",strMeasure4 = '" + strMeasure4 + '\'' + 
			",strMeasure1 = '" + strMeasure1 + '\'' + 
			",strMeasure18 = '" + strMeasure18 + '\'' + 
			",strMeasure2 = '" + strMeasure2 + '\'' + 
			",strMeasure19 = '" + strMeasure19 + '\'' + 
			",strMeasure16 = '" + strMeasure16 + '\'' + 
			",strMeasure17 = '" + strMeasure17 + '\'' + 
			",strMeasure14 = '" + strMeasure14 + '\'' + 
			",strMeasure15 = '" + strMeasure15 + '\'' + 
			"}";
		}
		public void copyMeal(MealsItem mealsItem) {

			this.strImageSource = mealsItem.getStrImageSource();
			this.strIngredient10 = mealsItem.getStrIngredient10();
			this.strIngredient12 = mealsItem.getStrIngredient12();
			this.strIngredient11 = mealsItem.getStrIngredient11();
			this.strIngredient14 = mealsItem.getStrIngredient14();
			this.strCategory = mealsItem.getStrCategory();
			this.strIngredient13 = mealsItem.getStrIngredient13();
			this.strIngredient16 = mealsItem.getStrIngredient16();
			this.strIngredient15 = mealsItem.getStrIngredient15();
			this.strIngredient18 = mealsItem.getStrIngredient18();
			this.strIngredient17 = mealsItem.getStrIngredient17();
			this.strArea = mealsItem.getStrArea();
			this.strCreativeCommonsConfirmed = mealsItem.getStrCreativeCommonsConfirmed();
			this.strIngredient19 = mealsItem.getStrIngredient19();
			this.strTags = mealsItem.getStrTags();
			this.idMeal = mealsItem.getIdMeal();
			this.strInstructions = mealsItem.getStrInstructions();
			this.strIngredient1 = mealsItem.getStrIngredient1();
			this.strIngredient3 = mealsItem.getStrIngredient3();
			this.strIngredient2 = mealsItem.getStrIngredient2();
			this.strIngredient20 = mealsItem.getStrIngredient20();
			this.strIngredient5 = mealsItem.getStrIngredient5();
			this.strIngredient4 = mealsItem.getStrIngredient4();
			this.strIngredient7 = mealsItem.getStrIngredient7();
			this.strIngredient6 = mealsItem.getStrIngredient6();
			this.strIngredient9 = mealsItem.getStrIngredient9();
			this.strIngredient8 = mealsItem.getStrIngredient8();
			this.strMealThumb = mealsItem.getStrMealThumb();
			this.strMeasure20 = mealsItem.getStrMeasure20();
			this.strYoutube = mealsItem.getStrYoutube();
			this.strMeal = mealsItem.getStrMeal();
			this.strMeasure12 = mealsItem.getStrMeasure12();
			this.strMeasure13 = mealsItem.getStrMeasure13();
			this.strMeasure10 = mealsItem.getStrMeasure10();
			this.strMeasure11 = mealsItem.getStrMeasure11();
			this.dateModified = mealsItem.getDateModified();
			this.strDrinkAlternate = mealsItem.getStrDrinkAlternate();
			this.strSource = mealsItem.getStrSource();
			this.strMeasure9 = mealsItem.getStrMeasure9();
			this.strMeasure7 = mealsItem.getStrMeasure7();
			this.strMeasure8 = mealsItem.getStrMeasure8();
			this.strMeasure5 = mealsItem.getStrMeasure5();
			this.strMeasure6 = mealsItem.getStrMeasure6();
			this.strMeasure3 = mealsItem.getStrMeasure3();
			this.strMeasure4 = mealsItem.getStrMeasure4();
			this.strMeasure1 = mealsItem.getStrMeasure1();
			this.strMeasure18 = mealsItem.getStrMeasure18();
			this.strMeasure2 = mealsItem.getStrMeasure2();
			this.strMeasure19 = mealsItem.getStrMeasure19();
			this.strMeasure16 = mealsItem.getStrMeasure16();
			this.strMeasure17 = mealsItem.getStrMeasure17();
			this.strMeasure14 = mealsItem.getStrMeasure14();
			this.strMeasure15 = mealsItem.getStrMeasure15();
		}
}
