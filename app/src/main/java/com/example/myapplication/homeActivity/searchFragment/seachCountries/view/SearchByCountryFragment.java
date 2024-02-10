package com.example.myapplication.homeActivity.searchFragment.seachCountries.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myapplication.R;
import com.example.myapplication.homeActivity.model.allCountries.Country;
import com.example.myapplication.homeActivity.searchFragment.seachCountries.presenter.Presenter;
import com.example.myapplication.homeActivity.searchFragment.searchIngredients.presenter.SearchResultContract;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchByCountryFragment extends Fragment implements SearchResultContract.View<Country> {
    private ImageButton backButton;
    private List<Country> countriesList;
    private CountryRVAdapter countryRVAdapter;
    private HomeActivityCommunicator communicator;
    private SearchResultContract.Presenter<Country> presenter;
    private RecyclerView resultRv;
    private EditText searchTv;
    public SearchByCountryFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof HomeActivityCommunicator) {
            communicator = (HomeActivityCommunicator) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement MainCommunication");
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_by_country, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        if (getArguments() != null) {
            Country[] countries = SearchByCountryFragmentArgs.fromBundle(getArguments()).getCountriesList();
            this.countriesList =  Arrays.asList(countries);
            countryRVAdapter.setCountryList(this.countriesList);
            countryRVAdapter.notifyDataSetChanged();
            presenter.setModelList(this.countriesList);
            // Use the data as needed
            //Log.i(TAG, "onViewCreated: getIngredientts "+ingredientModels.length);
        }
        searchTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.search(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });
    }

    private void initUI(View view){
        countriesList = new ArrayList<>();
        backButton = view.findViewById(R.id.backBtnCountry);
        countryRVAdapter = new CountryRVAdapter(communicator,countriesList);
        presenter = new Presenter(this,countriesList);
        searchTv = view.findViewById(R.id.search_bar_country);
        resultRv = view.findViewById(R.id.resultCountriesSearch);
        GridLayoutManager manager = new GridLayoutManager(communicator.getContext(),2);
        manager.setOrientation(RecyclerView.VERTICAL);
        resultRv.setLayoutManager(manager);
        resultRv.setAdapter(countryRVAdapter);

    }

    @Override
    public void updateUI(List<Country> models) {
        countryRVAdapter.setCountryList(models);
        countryRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        communicator.showToast(error);
    }
}