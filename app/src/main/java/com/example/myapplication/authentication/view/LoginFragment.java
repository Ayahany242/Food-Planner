package com.example.myapplication.authentication.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.authentication.presenter.AuthContract;
import com.example.myapplication.authentication.presenter.AuthPresenter;
import com.example.myapplication.homeActivity.view.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment implements AuthContract.View{
    private MainCommunication mainCommunication;
    private EditText emailEditTxt;
    private EditText passwordEditTxt;
    private Button loginBtn;
    private Button signUpBtn;
    private AuthContract.Presenter presenter;
    private static final String TAG = "Login";

    FirebaseAuth mAuth;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter = new AuthPresenter(this);
        presenter.foundCurrentUser();
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainCommunication) {
            mainCommunication = (MainCommunication) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement MainCommunication");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initUI(view);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditTxt.getText().toString();
                String password = passwordEditTxt.getText().toString();
                if(mainCommunication.isInputValid(email)&& mainCommunication.isInputValid(password)) {
                    presenter.signIn(email,password);
                }
                else{
                    mainCommunication.showToast("LogIn Button enter the data");
                    Log.i(TAG, "onClick: failed true");
                }
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: login method to signUp");
                mainCommunication.showSignUpFragment();
            }
        });
        return view;
    }
    private void initUI(View view){
        emailEditTxt = view.findViewById(R.id.emailLoginEditView);
        passwordEditTxt = view.findViewById(R.id.passwordLoginEditView);
        loginBtn= view.findViewById(R.id.loginBtn);
        signUpBtn = view.findViewById(R.id.signUpBtn);
    }
    @Override
    public void userFounded() {
        startActivity(new Intent(mainCommunication.getContext(), HomeActivity.class));
    }

    @Override
    public void onSuccessfully() {
        startActivity(new Intent(mainCommunication.getContext(), HomeActivity.class));
    }

    @Override
    public void onFailure(String error) {
        mainCommunication.showToast(error);
    }
}