package com.example.myapplication.authentication.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.authentication.model.UserData;
import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.repository.realTime.RealTimeImplementation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AuthPresenter implements AuthContract.Presenter{
    private AuthContract.View view;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firestore;
    private String userID;
    private RealTimeImplementation realTimeDB;
    private FirebaseDatabase database;
    private DatabaseReference favReference;
    private DatabaseReference planReference;
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
    public void signIn(String email, String password, Context context) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task ->{
            if (task.isSuccessful()) {
                realTimeDB = new RealTimeImplementation();
                realTimeDB.getAllFavMeals(context);
                realTimeDB.getAllPlanedMeals(context);
                view.onSuccessfully();
            } else {
                view.onFailure(task.getException().getMessage());
            }
        });
    }
    @Override
    public void signUp(UserData userData) {
        mAuth.createUserWithEmailAndPassword(userData.getEmail(), userData.getPassword()).addOnCompleteListener(task ->{
            if (task.isSuccessful()){
                //connectToDatabase(email,password,fullName);
                firebaseUser = mAuth.getCurrentUser();
              //  UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName()
                enterUserDataIntoRealTimeDB(userData);
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
    private void enterUserDataIntoRealTimeDB(UserData userData){
        userID = mAuth.getCurrentUser().getUid();
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered users");
        referenceProfile.child(userID).setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    view.onSuccessfully();

                    Log.i(TAG, "connectToDatabase: success "+userID);
                }else{
                    view.onFailure("User Register failed. Please try again");
                }
            }
        });

    }

    private void addMeal(MealsItem item)
    {

    }
}
