package com.example.myapplication.repository.network;

import retrofit2.Call;

public interface RemoteDataSource {

    public void makeNetworkCallForRandomMeal(NetworkCallback callback);
    public void makeNetworkCallForAllCategory(NetworkCallback callback);
    public void makeNetworkCallForAllCountries(NetworkCallback callback);
}
