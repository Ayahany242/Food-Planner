package com.example.myapplication.homeActivity.view.homeFragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.HorizontalItemDecoration;
import com.example.myapplication.R;
import com.example.myapplication.homeActivity.model.allCategory.CategoriesItem;
import com.example.myapplication.homeActivity.model.allCountries.Country;
import com.example.myapplication.homeActivity.model.randomModel.MealsItem;
import com.example.myapplication.homeActivity.presenter.CategoryContract;
import com.example.myapplication.homeActivity.presenter.CategoryPresenter;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;
import com.example.myapplication.repository.RepositoryImpl;
import com.example.myapplication.repository.localData.MealLocalDataSourceImpl;
import com.example.myapplication.repository.network.RemoteDataSourceImp;

import java.util.ArrayList;
import java.util.List;

public class MealsHomeFragment extends Fragment implements CategoryContract.View{
    private static final String TAG ="TAG";
    private CategoryContract.Presenter presenterView;
    private HomeActivityCommunicator mainCommunication;
    private CardView cardRandomView;
    private ImageView imageView;
    private TextView titleTv;
    private TextView descriptionTv;
    private TextView categoryTv;
    private RecyclerView categoryRecycleView;
    private LinearLayoutManager managerCategory;
    private LinearLayoutManager managerCountry;
    private CategoryRVAdapter categoryRVAdapter;
    private CountryRVAdapter countryRVAdapter;
    private RecyclerView countryRecycleView;
    private AppCompatImageButton favoriteBtn;
    private MealsItem randomMeal;
    private boolean isFavorite = false;
    //private ViewPager categoryViewPager;
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
        presenterView = new CategoryPresenter(this, RepositoryImpl.getInstance(RemoteDataSourceImp.getInstance(), MealLocalDataSourceImpl.getInstance(mainCommunication.getContext())));
        Log.i(TAG, "onStart: MealsHomeFragment ");
        presenterView.getRandomMeal();
        presenterView.getAllCategory();
        presenterView.getAllCountries();
    }
    private void initUI(View view){
        cardRandomView = view.findViewById(R.id.countryCardView);
        imageView = view.findViewById(R.id.randomImage);
        titleTv = view.findViewById(R.id.title);
        descriptionTv = view.findViewById(R.id.descriptionTv);
        categoryTv = view.findViewById(R.id.countryTV);
        categoryRecycleView = view.findViewById(R.id.catigoryRecycleView);
        countryRecycleView = view.findViewById(R.id.countryRecycleView);
        favoriteBtn = view.findViewById(R.id.favouriteBtn);
        //categoryRecycleView
        managerCategory = new LinearLayoutManager(mainCommunication.getContext());
        managerCategory.setOrientation(RecyclerView.HORIZONTAL);
        categoryRecycleView.setLayoutManager(managerCategory);
        categoryRVAdapter = new CategoryRVAdapter(mainCommunication,new ArrayList<>());
        categoryRecycleView.setAdapter(categoryRVAdapter);
        // Specify the spacing in pixels
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.horizontal_spacing);
        // Apply the custom item decoration to the RecyclerView
        categoryRecycleView.addItemDecoration(new HorizontalItemDecoration(spacingInPixels));
        //countryRecycleView
        managerCountry = new LinearLayoutManager(mainCommunication.getContext());
        managerCountry.setOrientation(RecyclerView.HORIZONTAL);
        countryRecycleView.setLayoutManager(managerCountry);
        countryRVAdapter = new CountryRVAdapter(mainCommunication,new ArrayList<>());
        countryRecycleView.setAdapter(countryRVAdapter);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meals_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavorite){
                    deleteMealFromFavorite(randomMeal);
                    favoriteBtn.setBackgroundResource(R.drawable.favorite_ic);
                    Log.i(TAG, "onClick: deleteMealFromFavorite isFavorite "+ isMealFavorite(randomMeal.getIdMeal()));
                }else {
                    addMealToFavorite(randomMeal);
                    Log.i(TAG, "onClick: addMealToFavorite isFavorite "+ isFavorite +" randomMeal.getIdMeal() "+randomMeal.getIdMeal());
                    favoriteBtn.setBackgroundResource(R.drawable.favorite_fill_ic);

                }
                isFavorite = !isFavorite;
                Log.i(TAG, "after clicked onClick: isFavorite "+ isFavorite);
            }
        });
    }

    @Override
    public void showErrorMsg(String error) {
        Log.i(TAG, "showErrorMsg: ");
        mainCommunication.showToast(error);
    }

    @Override
    public void showRandomMeal(MealsItem meal) {
        Log.i(TAG, "showRandomMeal: MealsHomeFragment "+meal.getStrCategory());
        setRandomMeal(meal);
        mainCommunication.showToast(meal.getStrCategory());
    }

    @Override
    public void showAllCategory(List<CategoriesItem> categoriesItems) {
        Log.i(TAG, "showAllCategory: MealsHomeFragment "+categoriesItems.get(0).getStrCategoryDescription());
        mainCommunication.showToast(categoriesItems.get(1).getStrCategoryDescription());
        categoryRVAdapter.setCategoriesItems(categoriesItems);
        categoryRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAllCountries(List<Country> countriesItems) {
        Log.i(TAG, "showAllCountries: showAllCountries "+countriesItems.get(0).getStrArea());
        mainCommunication.showToast(countriesItems.get(1).getStrArea());
        countryRVAdapter.setCountryList(countriesItems);
        countryRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void addMealToFavorite(MealsItem item) {
        presenterView.addFavoriteMeal(item);
    }

    @Override
    public void deleteMealFromFavorite(MealsItem item) {
        presenterView.deleteFavoriteMeal(item);
    }

    @Override
    public boolean isMealFavorite(String idMeal) {
        Log.i(TAG, "isMealFavorite: ");
        return presenterView.isMealExists(idMeal);
    }

    private void setRandomMeal(MealsItem meal){
        randomMeal = meal;
        if(isMealFavorite(meal.getIdMeal())){
            favoriteBtn.setBackgroundResource(R.drawable.favorite_fill_ic);
            isFavorite = true;
        }else {
            favoriteBtn.setBackgroundResource(R.drawable.favorite_ic);
            isFavorite = false;
        }
       // Log.i(TAG, "setRandomMeal: 0"+meal.getStrMealThumb()+"/preview");
        Glide.with(mainCommunication.getContext()).load(meal.getStrMealThumb()+"/preview").into(imageView);
        titleTv.setText(meal.getStrMeal());
        categoryTv.setText(meal.getStrCategory());
        descriptionTv.setText(meal.getStrArea());
    }
}