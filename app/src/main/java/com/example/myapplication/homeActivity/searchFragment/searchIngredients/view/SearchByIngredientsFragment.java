package com.example.myapplication.homeActivity.searchFragment.searchIngredients.view;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myapplication.R;
import com.example.myapplication.homeActivity.searchFragment.searchIngredients.presenter.Presenter;
import com.example.myapplication.homeActivity.searchFragment.searchIngredients.presenter.SearchResultContract;
import com.example.myapplication.homeActivity.searchFragment.view.onCardClickedListenerSearch;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;
import com.example.myapplication.mealDetails.model.IngredientModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchByIngredientsFragment extends Fragment implements SearchResultContract.View<IngredientModel>, onCardClickedListenerSearch.CardListener {
    private static final String TAG = "search";
    private HomeActivityCommunicator mainCommunication;
    private ImageButton backButton;
    private IngredientAdapterRV ingredientAdapterRV;
    private RecyclerView resultRv;
    private EditText search;
    private List<IngredientModel> ingredientModels;
    private SearchResultContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_by_ingredients, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof HomeActivityCommunicator)
            mainCommunication = (HomeActivityCommunicator) context;
        else {
            throw new ClassCastException(context.toString() + " must implement MainCommunication");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        if (getArguments() != null) {
            IngredientModel[] ingredientModels = SearchByIngredientsFragmentArgs.fromBundle(getArguments()).getIngredientsList();
            this.ingredientModels =  Arrays.asList(ingredientModels);
            ingredientAdapterRV.setIngredients(this.ingredientModels);
            ingredientAdapterRV.notifyDataSetChanged();
            presenter.setModelList(this.ingredientModels);
            // Use the data as needed
            Log.i(TAG, "onViewCreated: getIngredientts "+ingredientModels.length);
        }
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });
        search.addTextChangedListener(new TextWatcher() {
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
    }

    private void initUI(View view){
        ingredientModels = new ArrayList<>();
        backButton = view.findViewById(R.id.backBtnIngredient);
        resultRv = view.findViewById(R.id.resultIngredientSearch);
        presenter = new Presenter(this,ingredientModels);
        search =view.findViewById(R.id.search_bar_ingredient);
        ingredientAdapterRV = new IngredientAdapterRV(mainCommunication, ingredientModels);
        GridLayoutManager manager = new GridLayoutManager(mainCommunication.getContext(),3);
        manager.setOrientation(RecyclerView.VERTICAL);
        resultRv.setLayoutManager(manager);
        resultRv.setAdapter(ingredientAdapterRV);
    }

    @Override
    public void updateUI(List<IngredientModel> models) {
        ingredientAdapterRV.setIngredients(models);
        ingredientAdapterRV.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        mainCommunication.showToast(error);
    }

    @Override
    public void goToAllMealData(String contentName) {
        mainCommunication.goToAllMealData(contentName,"ingredient");
    }
}