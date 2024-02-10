package com.example.myapplication.homeActivity.searchFragment.searchIngredients.presenter;

import java.util.List;

public interface SearchResultContract {
    interface View<T>{
        public void updateUI(List<T> models);
        public void showError(String error);

    }
    interface Presenter<T>{
        public void search(String name);
        public  void setModelList(List<T> ingredientModels);
    }
}
