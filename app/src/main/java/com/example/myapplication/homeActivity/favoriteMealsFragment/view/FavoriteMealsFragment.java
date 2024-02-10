package com.example.myapplication.homeActivity.favoriteMealsFragment.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.HorizontalItemDecoration;
import com.example.myapplication.R;
import com.example.myapplication.homeActivity.favoriteMealsFragment.presenter.FavoriteMealsContract;
import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;
import com.example.myapplication.homeActivity.favoriteMealsFragment.presenter.FavoriteMealsPresenterImpl;
import com.example.myapplication.repository.RepositoryImpl;
import com.example.myapplication.repository.localData.MealLocalDataSourceImpl;
import com.example.myapplication.repository.network.RemoteDataSourceImp;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavoriteMealsFragment extends Fragment implements FavoriteMealsContract.View ,FavoriteBtnListener{
    private static final String TAG = "TAG";
    private RecyclerView recyclerView;
    //private GridLayoutManager manager;
    private CircleImageView imageView;
    private TextView userName;
    private LinearLayoutManager manager;
    private FavoriteMealsRVAdapter adapter;
    private HomeActivityCommunicator mainCommunication;
    private FavoriteMealsContract.Presenter presenterView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof HomeActivityCommunicator) {
            mainCommunication = (HomeActivityCommunicator) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement MainCommunication");
        }
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private void initUI(View view){
        recyclerView = view.findViewById(R.id.allFavoriteMealsRV);
        adapter = new FavoriteMealsRVAdapter(mainCommunication,new ArrayList<>(),this);
        //manager = new GridLayoutManager(mainCommunication.getContext(),2,GridLayoutManager.VERTICAL,false);
        manager = new LinearLayoutManager(mainCommunication.getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.horizontal_spacing);
        recyclerView.addItemDecoration(new HorizontalItemDecoration(spacingInPixels));
        recyclerView.setAdapter(adapter);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        presenterView = new FavoriteMealsPresenterImpl(this,RepositoryImpl.getInstance(RemoteDataSourceImp.getInstance(),MealLocalDataSourceImpl.getInstance(mainCommunication.getContext())),mainCommunication.getContext());
        Log.i(TAG, "onViewCreated: MealsHomeFragment ___________________________");
        presenterView.getAllFavoriteMeals();
    }

    @Override
    public void showErrorMsg(String error) {
        Log.i(TAG, "showErrorMsg: ");
        mainCommunication.showToast(error);
    }
    @Override
    public void deleteMealFromFavorite(MealsItem item) {
        presenterView.deleteFavoriteMeal(item);
    }
    @Override
    public void showAllFavoriteMeals(List<MealsItem> meals) {
        adapter.setMealsItems(meals);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void notifyMealExistence(boolean exists) {

    }

    @Override
    public void onFavoriteBtnListener(MealsItem item) {
        deleteMealFromFavorite(item);
    }
}