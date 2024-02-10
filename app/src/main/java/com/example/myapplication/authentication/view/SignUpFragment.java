package com.example.myapplication.authentication.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.authentication.model.UserData;
import com.example.myapplication.authentication.presenter.AuthContract;
import com.example.myapplication.authentication.presenter.AuthPresenter;
import com.example.myapplication.homeActivity.view.HomeActivity;
import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpFragment extends Fragment implements AuthContract.View {
    private static final String TAG = "signIn";
    private MainCommunication mainCommunication;
    private TextInputEditText fullNameEditTxt,emailEditTxt,passwordEditTxt,confirmPass;
    private CardView signUpBtn;
    private CircleImageView googleBtn;
    private TextView loginBtn;
    private AuthContract.Presenter presenter;
    private ProgressBar progressBar;

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
        passwordEditTxt = view.findViewById(R.id.passwordSignUpEditView);
        confirmPass = view.findViewById(R.id.confirmPassword);
        signUpBtn = view.findViewById(R.id.signUpBtn);
        googleBtn = view.findViewById(R.id.googleBtn);
        loginBtn = view.findViewById(R.id.signIn);
        progressBar = view.findViewById(R.id.progressBarSignUp);

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
            String confirmPassword = confirmPass.getText().toString();
            progressBar.setVisibility(View.VISIBLE);
            if(!mainCommunication.isInputValid(email))
            {
                mainCommunication.showToast("Please enter your email");
                emailEditTxt.setError("Email is required");
                emailEditTxt.requestFocus();
                progressBar.setVisibility(View.GONE);

            }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                mainCommunication.showToast("Please re-enter your email");
                emailEditTxt.setError("valid email is required");
                emailEditTxt.requestFocus();
                progressBar.setVisibility(View.GONE);
            }
            else if (!mainCommunication.isInputValid(password)) {
                mainCommunication.showToast("Please enter valid password");
                passwordEditTxt.setError("password is required");
                progressBar.setVisibility(View.GONE);
                passwordEditTxt.requestFocus();
            } else if (password.length() < 8 ) {
                mainCommunication.showToast("Please enter strong password");
                passwordEditTxt.setError("password must be more than 8 words");
                progressBar.setVisibility(View.GONE);
                passwordEditTxt.requestFocus();
            } else if (!confirmPassword.equals(password)) {
                mainCommunication.showToast("Password must be matched");
                confirmPass.setError("Password must be matched");
                progressBar.setVisibility(View.GONE);
                confirmPass.requestFocus();
                confirmPass.clearComposingText();
            } else if (!mainCommunication.isInputValid(confirmPassword)) {
                mainCommunication.showToast("Please confirm your password");
                confirmPass.setError("password confirmation is required");
                progressBar.setVisibility(View.GONE);
                confirmPass.requestFocus();
            } else if (!mainCommunication.isInputValid(fullName)) {
                mainCommunication.showToast("Please enter your full name");
                confirmPass.setError("full name is required");
                progressBar.setVisibility(View.GONE);
                confirmPass.requestFocus();
            } else{
                UserData userData = new UserData(fullName,email,password);
                presenter.signUp(userData);
            }
        });
        loginBtn.setOnClickListener((v)->
        {
            mainCommunication.showToast("go to login ");
            mainCommunication.navigationBetweenAuth(R.id.loginFragment);
        });
        return view;
    }

    @Override
    public void userFounded() {
        mainCommunication.navOnSuccess();
    }

    @Override
    public void onSuccessfully() {
        mainCommunication.navOnSuccess();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String error) {
        progressBar.setVisibility(View.GONE);
        mainCommunication.showToast(error);
    }
}