package com.example.myapplication.authentication.view;

import android.content.Context;

public interface MainCommunication {
    public boolean isInputValid(String txt);
    public void showToast(String message);
    public Context getContext();
    public void showLoginFragment();
    public void showSignUpFragment();
}
