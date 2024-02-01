package com.example.myapplication.authentication.presenter;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AuthPresenter implements AuthContract.Presenter{
    private AuthContract.View view;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private String userID;
    private final String TAG = "tag";

    public AuthPresenter(AuthContract.View view){
        this.view = view;
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }
    @Override
    public void foundCurrentUser() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            view.userFounded();
        }
    }
    @Override
    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task ->{
            if (task.isSuccessful()) {
                view.onSuccessfully();
            } else {
                view.onFailure(task.getException().getMessage());
            }
        });
    }
    @Override
    public void signUp(String email, String password, String fullName) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task ->{
            if (task.isSuccessful()){
                connectToDatabase(email,password,fullName);
            }else {
                view.onFailure(task.getException().getMessage());
            }
        });
    }
    public void connectToDatabase(String email, String password, String fName){
        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = firestore.collection("users").document(userID);
        Map<String,Object> user = new HashMap<>();
        user.put("fName",fName);
        user.put("email",email);
        user.put("password",password);
        documentReference.set(user).addOnSuccessListener(command -> {
            Log.i(TAG, "connectToDatabase: success "+userID);
            view.onSuccessfully();
        }).addOnFailureListener(command -> {
            view.onFailure(command.getMessage());
            Log.i(TAG, "addOnFailureListener: failed "+command.getMessage());
        });
    }
}
