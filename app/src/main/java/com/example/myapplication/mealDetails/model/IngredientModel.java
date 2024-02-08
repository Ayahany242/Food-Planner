package com.example.myapplication.mealDetails.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class IngredientModel implements Parcelable {
    String name;
    String image;

    public IngredientModel(String name, String image) {
        this.name = name;
        this.image = image;
    }

    protected IngredientModel(Parcel in) {
        name = in.readString();
        image = in.readString();
    }

    public static final Creator<IngredientModel> CREATOR = new Creator<IngredientModel>() {
        @Override
        public IngredientModel createFromParcel(Parcel in) {
            return new IngredientModel(in);
        }

        @Override
        public IngredientModel[] newArray(int size) {
            return new IngredientModel[size];
        }
    };

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void srtImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image);
    }
}
