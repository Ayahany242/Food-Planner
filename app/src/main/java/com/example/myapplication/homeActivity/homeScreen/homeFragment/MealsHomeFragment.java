package com.example.myapplication.homeActivity.homeScreen.homeFragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.HorizontalItemDecoration;
import com.example.myapplication.R;
import com.example.myapplication.homeActivity.model.allCategory.CategoriesItem;
import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.homeActivity.homeScreen.presenter.HomeContract;
import com.example.myapplication.homeActivity.homeScreen.presenter.HomePresenter;
import com.example.myapplication.homeActivity.searchFragment.seachCountries.view.CountryRVAdapter;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;
import com.example.myapplication.repository.RepositoryImpl;
import com.example.myapplication.repository.localData.MealLocalDataSourceImpl;
import com.example.myapplication.repository.network.RemoteDataSourceImp;

import java.util.ArrayList;
import java.util.List;

public class MealsHomeFragment extends Fragment implements HomeContract.View, onCardClickedListener{
    private static final String TAG ="TAG";
    private HomeContract.Presenter presenterView;
    private HomeActivityCommunicator mainCommunication;
    private CardView cardRandomView;
    private ImageView imageView;
    private TextView titleTv;
    private TextView descriptionTv;
    private TextView categoryTv;
    private RecyclerView categoryRecycleView;
    private LinearLayoutManager managerCategory;
    private CategoryRVAdapter categoryRVAdapter;
    private ImageButton favoriteBtn;
    private MealsItem randomMeal;
    private boolean isFavorite = false;
    private AppCompatButton searchBtn;
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
        presenterView = new HomePresenter(this, RepositoryImpl.getInstance(RemoteDataSourceImp.getInstance(), MealLocalDataSourceImpl.getInstance(mainCommunication.getContext())),mainCommunication.getContext());
        Log.i(TAG, "onStart: MealsHomeFragment ");
        presenterView.getRandomMeal();
        presenterView.getAllCategory();
    }
    private void initUI(View view){
        cardRandomView = view.findViewById(R.id.randomCardView);
        searchBtn = view.findViewById(R.id.search_btn_fragment);
        imageView = view.findViewById(R.id.randomImage);
        titleTv = view.findViewById(R.id.titleMealTvRow);
        descriptionTv = view.findViewById(R.id.mealAreaTv);
        categoryTv = view.findViewById(R.id.categoryMeal);
        categoryRecycleView = view.findViewById(R.id.catigoryRecycleView);
        favoriteBtn = view.findViewById(R.id.favoriteRandomMealBtn);
        //categoryRecycleView
        managerCategory = new LinearLayoutManager(mainCommunication.getContext());
        managerCategory.setOrientation(RecyclerView.HORIZONTAL);
        categoryRecycleView.setLayoutManager(managerCategory);
        categoryRVAdapter = new CategoryRVAdapter(mainCommunication,new ArrayList<>(),this);
        categoryRecycleView.setAdapter(categoryRVAdapter);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.horizontal_spacing);
        categoryRecycleView.addItemDecoration(new HorizontalItemDecoration(spacingInPixels));
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
                   if (isFavorite) {
                       presenterView.deleteFavoriteMeal(randomMeal);
                       favoriteBtn.setImageResource(R.drawable.favorite_ic);
                       Log.i(TAG, "onClick: deleteMealFromFavorite isFavorite ");
                   } else {
                       presenterView.addFavoriteMeal(randomMeal);
                       Log.i(TAG, "onClick: addMealToFavorite isFavorite " + isFavorite + " randomMeal.getIdMeal() ");
                       favoriteBtn.setImageResource(R.drawable.favorite_fill_ic);
                   }
                   isFavorite = !isFavorite;
                   Log.i(TAG, "after clicked onClick: isFavorite " + isFavorite);
               }
           });
        cardRandomView.setOnClickListener((v)-> mainCommunication.goToMealDetails(randomMeal.getIdMeal()));
        searchBtn.setOnClickListener((v)->{
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_mealsHomeFragment_to_searchFragment);

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
      //  mainCommunication.showToast(meal.getStrCategory());
    }

    @Override
    public void showAllCategory(List<CategoriesItem> categoriesItems) {
        Log.i(TAG, "showAllCategory: MealsHomeFragment "+categoriesItems.get(0).getStrCategoryDescription());
      //  mainCommunication.showToast(categoriesItems.get(1).getStrCategoryDescription());
        categoryRVAdapter.setCategoriesItems(categoriesItems);
        categoryRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void addMealToFavorite(MealsItem item) {
        presenterView.addFavoriteMeal(item);
    }

    @Override
    public void deleteMealFromFavorite(MealsItem item) {
        presenterView.deleteFavoriteMeal(item);
    }

    private void setRandomMeal(MealsItem meal){
        randomMeal = meal;
        presenterView.isMealExists(meal.getIdMeal());
        presenterView.isMealExists(meal.getIdMeal());
        Glide.with(mainCommunication.getContext()).load(meal.getStrMealThumb()+"/preview").into(imageView);
        titleTv.setText(meal.getStrMeal());
        categoryTv.setText(meal.getStrCategory());
        descriptionTv.setText(meal.getStrArea());
    }
    @Override
    public void cardCategoryListener(String categoryItem) {
        mainCommunication.goToAllMealData(categoryItem,"category");
    }
    @Override
    public void notifyMealExistence(boolean exists) {
        isFavorite = exists;
        if (exists) {
            favoriteBtn.setImageResource(R.drawable.favorite_fill_ic);
        } else {
            favoriteBtn.setImageResource(R.drawable.favorite_ic);
        }
    }
}