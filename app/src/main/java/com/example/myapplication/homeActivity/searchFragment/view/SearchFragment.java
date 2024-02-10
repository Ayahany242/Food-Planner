package com.example.myapplication.homeActivity.searchFragment.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.homeActivity.model.allCountries.Country;
import com.example.myapplication.homeActivity.searchFragment.model.IngredientsData;
import com.example.myapplication.homeActivity.searchFragment.presenter.SearchContract;
import com.example.myapplication.homeActivity.searchFragment.presenter.SearchPresenter;
import com.example.myapplication.homeActivity.searchFragment.seachCountries.view.CountryRVAdapter;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;
import com.example.myapplication.mealDetails.model.IngredientModel;
import com.example.myapplication.homeActivity.searchFragment.searchIngredients.view.IngredientAdapterRV;
import com.example.myapplication.repository.RepositoryImpl;
import com.example.myapplication.repository.localData.MealLocalDataSourceImpl;
import com.example.myapplication.repository.network.RemoteDataSourceImp;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchContract.View ,onCardClickedListenerSearch{
    private static final String TAG = "search";
    private static final String  Ingredient_URL = "https://www.themealdb.com/images/ingredients/";
    private List<Country> countriesList;
    private HomeActivityCommunicator mainCommunication;
    private RecyclerView allIngredientsRV , allCountriesRV;
    private SearchContract.Presenter presenter;
    private LinearLayoutManager manager1,manager2;
    private List<IngredientModel> ingredientModels;
    private IngredientAdapterRV ingredientAdapterRV;
    private CountryRVAdapter countryRVAdapter;
    private AppCompatButton searchBtn;
    private TextView viewAllIngredientsTv, viewAllCountriesTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
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
        if (presenter == null) {
            presenter = new SearchPresenter(this, RepositoryImpl.getInstance(RemoteDataSourceImp.getInstance()
                    , MealLocalDataSourceImpl.getInstance(mainCommunication.getContext())));
        }
        presenter.getAllIngredients();
        presenter.getAllCountries();
    }

    private void initUI(View view){
        ingredientModels =new ArrayList<>();
        //searchBtn = view.findViewById(R.id.search_bar);
        allIngredientsRV = view.findViewById(R.id.ingredientRV);
        manager1 = new LinearLayoutManager(mainCommunication.getContext());
        manager1.setOrientation(RecyclerView.HORIZONTAL);
        allIngredientsRV.setLayoutManager(manager1);
        ingredientAdapterRV = new IngredientAdapterRV(mainCommunication,ingredientModels);
        allIngredientsRV.setAdapter(ingredientAdapterRV);
        allCountriesRV = view.findViewById(R.id.countryRV);
        manager2 = new LinearLayoutManager(mainCommunication.getContext());
        manager2.setOrientation(RecyclerView.HORIZONTAL);
        allCountriesRV.setLayoutManager(manager2);
        countryRVAdapter = new CountryRVAdapter(mainCommunication,new ArrayList<>());
        allCountriesRV.setAdapter(countryRVAdapter);
        searchBtn = view.findViewById(R.id.search_bar);
        viewAllCountriesTv = view.findViewById(R.id.viewAllCountries);
        viewAllIngredientsTv = view.findViewById(R.id.viewAllIngredients);
       // homeSearchData = view.findViewById(R.id.homeSearchData);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        searchBtn.setOnClickListener((v)-> mainCommunication.goToSearchResult(R.id.searchByNameFragment));
        viewAllIngredientsTv.setOnClickListener((v)-> {
            SearchFragmentDirections.ActionSearchFragmentToSearchByIngredientsFragment action = SearchFragmentDirections.actionSearchFragmentToSearchByIngredientsFragment(ingredientModels.toArray(new  IngredientModel[ingredientModels.size()]));
           // mainCommunication.goToSearchResult(R.id.searchByIngredientsFragment);
            Navigation.findNavController(view).navigate(action);
        });
        viewAllCountriesTv.setOnClickListener((v)-> {
            //mainCommunication.goToSearchResult(R.id.searchByCountryFragment);
            SearchFragmentDirections.ActionSearchFragmentToSearchByCountryFragment action = SearchFragmentDirections.actionSearchFragmentToSearchByCountryFragment(countriesList.toArray(new Country[countriesList.size()]));
            Navigation.findNavController(view).navigate(action);
        });
      /*  */
    }

    @Override
    public void showErrorMsg(String error) {
        Log.i(TAG, "showErrorMsg: SearchFragment");
    }

    @Override
    public void showAllIngredients(List<IngredientsData> ingredientsItems) {
        setAllIngredientsListImage(ingredientsItems);
        ingredientAdapterRV.setIngredients(ingredientModels);
        ingredientAdapterRV.notifyDataSetChanged();
        Log.i(TAG, "showAllIngredients: "+ingredientsItems.get(0));
        Log.i(TAG, "showAllIngredients: SearchFragment "+ingredientsItems.size());
    }

    @Override
    public void showAllCountries(List<Country> countriesItems) {
        countriesList = countriesItems;
        setFlags();
       // mainCommunication.showToast(countriesItems.get(1).getStrArea());
        countryRVAdapter.setCountryList(countriesItems);
        countryRVAdapter.notifyDataSetChanged();
        Log.i(TAG, "showAllCountries: SearchFragment "+countriesItems.size());
    }
    private void setFlags(){
        String[]  countriesFlags = mainCommunication.getContext().getResources().getStringArray(R.array.flags);
        for (int i = 0 ; i<countriesList.size();i++){
            countriesList.get(i).setStrFlag(countriesFlags[i]);
        }
    }

   /* */

    private void setAllIngredientsListImage(List<IngredientsData> list){
        for (IngredientsData item:
             list) {
            ingredientModels.add(new IngredientModel(item.getStrIngredient(),Ingredient_URL+item.getStrIngredient()+"-Small.png"));
        }
        Log.i(TAG, "setAllIngredientsListImage: ingredients with image "+ingredientModels.size());
    }

    @Override
    public void cardMealListener(String cardItem) {
        Log.i(TAG, "cardCountryListener: card item selected "+cardItem);
        mainCommunication.goToMealDetails(cardItem);
    }

    @Override
    public void cardCountryListener(String cardItem) {

    }
}