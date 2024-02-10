package com.example.myapplication.authentication.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.authentication.presenter.AuthContract;
import com.example.myapplication.authentication.presenter.AuthPresenter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment implements AuthContract.View{
    private MainCommunication mainCommunication;
    private TextInputEditText emailEditTxt;
    private TextInputEditText passwordEditTxt;
    private CardView loginBtn;
    private ProgressBar progressBar;
    private TextView signUpBtn, guestMode;
    private AuthContract.Presenter presenter;
    private static final String TAG = "Login";
    public static boolean isSingedIn = false;

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
                } else{
                    presenter.signIn(email,password, mainCommunication.getContext());
                    Log.i(TAG, "onClick: true");
                }
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: login method to signUp");
                /*NavController navController = NavHostFragment.findNavController(navHost);
                navController.navigate(R.id.signUpFragment);*/
                mainCommunication.navigationBetweenAuth(R.id.signUpFragment);
            }
        });
        guestMode.setOnClickListener((v)->mainCommunication.navOnGuest());
        return view;
    }
    private void initUI(View view){
        emailEditTxt = view.findViewById(R.id.emailLoginEditView);
        passwordEditTxt = view.findViewById(R.id.passwordLoginEditView);
        loginBtn= view.findViewById(R.id.loginBtn);
        progressBar = view.findViewById(R.id.progressBarLogin);
        signUpBtn = view.findViewById(R.id.signUpBtn);
        guestMode = view.findViewById(R.id.guestMode);
    }
    @Override
    public void userFounded() {
       mainCommunication.navOnSuccess();
    }

    @Override
    public void onSuccessfully() {
        isSingedIn = true;
        mainCommunication.navOnSuccess();
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onFailure(String error) {
        progressBar.setVisibility(View.GONE);
        mainCommunication.showToast(error);
    }
}