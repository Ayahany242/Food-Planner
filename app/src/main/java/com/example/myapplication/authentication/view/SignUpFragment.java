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
import android.widget.ProgressBar;

import com.example.myapplication.R;
import com.example.myapplication.authentication.presenter.AuthContract;
import com.example.myapplication.authentication.presenter.AuthPresenter;
import com.example.myapplication.homeActivity.view.HomeActivity;

public class SignUpFragment extends Fragment implements AuthContract.View {
    private static final String TAG = "signIn";
    private MainCommunication mainCommunication;
    private EditText fullNameEditTxt;
    private EditText emailEditTxt;
    private EditText passwordEditTxt;
    private Button signUpBtn;
    private Button googleBtn;
    private Button loginBtn;
    private ProgressBar progressBar;
    private AuthContract.Presenter presenter;

    public SignUpFragment() {
        // Required empty public constructor
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
    private void initUI(View view){
        fullNameEditTxt = view.findViewById(R.id.fullNameEditView);
        emailEditTxt = view.findViewById(R.id.emailEditView);
        passwordEditTxt = view.findViewById(R.id.passwordEditView);
        signUpBtn = view.findViewById(R.id.signUpBtn);
        googleBtn = view.findViewById(R.id.ggogleBtn);
        loginBtn = view.findViewById(R.id.loginBtn);
        progressBar = view.findViewById(R.id.progressBar);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onStart() {
        super.onStart();
        presenter = new AuthPresenter(this);
        presenter.foundCurrentUser();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_sign_up, container, false);
        initUI(view);
        signUpBtn.setOnClickListener((v)->{
            String email = emailEditTxt.getText().toString();
            String password = passwordEditTxt.getText().toString();
            String fullName = fullNameEditTxt.getText().toString();

            if (mainCommunication.isInputValid(email) && mainCommunication.isInputValid(password)&& mainCommunication.isInputValid(fullName)){
                progressBar.setVisibility(View.VISIBLE);
                Log.i(TAG, "onCreateView: filles is not empty ");
                presenter.signUp(email,password,fullName);
            }else{
                mainCommunication.showToast("Enter the data ");
            }
        });
        loginBtn.setOnClickListener((v)->
        {
            mainCommunication.showToast("go to login ");
            mainCommunication.showLoginFragment();
        });
        return view;
    }

    @Override
    public void userFounded() {
        startActivity(new Intent(mainCommunication.getContext(), HomeActivity.class));
    }

    @Override
    public void onSuccessfully() {
        startActivity(new Intent(mainCommunication.getContext(), HomeActivity.class));
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String error) {
        mainCommunication.showToast(error);
    }
}