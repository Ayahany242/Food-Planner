package com.example.myapplication.homeActivity.searchFragment.view;

public interface onCardClickedListenerSearch {
    public void cardMealListener(String cardItem);
    public void cardCountryListener(String cardItem);
    public interface CardListenerMealDetails {
       // public void goToMealDerails(String contentName,String source);
        public void goToMealDerails(String mealId);
    }
    public interface CardListener{
        public void goToAllMealData(String contentName);
    }

}
