package com.example.myapplication.authentication.presenter;

import android.content.Context;

import com.example.myapplication.authentication.model.UserData;

public interface AuthContract {
    interface View{
       public void userFounded();
        public void onSuccessfully();
        public void onFailure(String error);
    }
    interface Presenter{
        public void foundCurrentUser();
        public void signIn(String email, String password, Context context);
        public void signUp(UserData userData);
    }
}
