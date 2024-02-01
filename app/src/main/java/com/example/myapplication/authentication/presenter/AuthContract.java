package com.example.myapplication.authentication.presenter;

public interface AuthContract {
    interface View{
       public void userFounded();
        public void onSuccessfully();
        public void onFailure(String error);
    }
    interface Presenter{
        public void foundCurrentUser();
        public void signIn(String email, String password);
        public void signUp(String email, String password, String fullName);
    }
}
