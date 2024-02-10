package com.example.myapplication.homeActivity.settingFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;
import com.google.firebase.auth.FirebaseAuth;

public class SettingFragment extends Fragment {
    private AppCompatButton signOutBtn;
    private HomeActivityCommunicator communicator;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof HomeActivityCommunicator )
            communicator = (HomeActivityCommunicator) context;
        else
            throw new ClassCastException(context.toString() + " must implement MainCommunication");
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signOutBtn = view.findViewById(R.id.signOut);
        signOutBtn.setOnClickListener((v)-> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(communicator.getContext(), MainActivity.class));
        });
    }
}