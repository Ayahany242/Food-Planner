package com.example.myapplication.homeActivity.allMealsFragment.view;

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
import android.widget.TextView;
import com.example.myapplication.HorizontalItemDecoration;
import com.example.myapplication.R;
import com.example.myapplication.homeActivity.allMealsFragment.presenter.AllMealContract;
import com.example.myapplication.homeActivity.allMealsFragment.presenter.AllMealPresenter;
import com.example.myapplication.homeActivity.searchFragment.searchByName.view.ResultMealDataAdapter;
import com.example.myapplication.homeActivity.searchFragment.view.onCardClickedListenerSearch;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;
import com.example.myapplication.homeActivity.allMealsFragment.model.MealsData;
import com.example.myapplication.repository.RepositoryImpl;
import com.example.myapplication.repository.localData.MealLocalDataSourceImpl;
import com.example.myapplication.repository.network.RemoteDataSourceImp;

import java.util.ArrayList;
import java.util.List;

public class AllMealsFragment extends Fragment implements AllMealContract.View, onCardClickedListenerSearch.CardListenerMealDetails {
    private static final String TAG= "tag";
    private TextView containerTV;
    private HomeActivityCommunicator activityCommunicator;
    private String content, source;
    private AllMealContract.Presenter presenter;
    private RecyclerView allMealsRecycleView;
    private GridLayoutManager manager;
    private ResultMealDataAdapter adapter;
    private ImageButton backBtn;
    private EditText searchBar;
    private List<MealsData> mealsDataList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof HomeActivityCommunicator) {
            activityCommunicator = (HomeActivityCommunicator) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement MainCommunication");
        }
    }
    private void initUI(View view){
        containerTV = view.findViewById(R.id.containerTv);
        containerTV.setText(content);
        mealsDataList = new ArrayList<>();
        allMealsRecycleView = view.findViewById(R.id.allMealsRecycleView);
        manager = new GridLayoutManager(activityCommunicator.getContext(),2);
        adapter = new ResultMealDataAdapter(activityCommunicator,mealsDataList,this);
        manager.setOrientation(RecyclerView.VERTICAL);
        allMealsRecycleView.setLayoutManager(manager);
        // Specify the spacing in pixels
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.horizontal_spacing);
        // Apply the custom item decoration to the RecyclerView
        allMealsRecycleView.addItemDecoration(new HorizontalItemDecoration(spacingInPixels));
        allMealsRecycleView.setAdapter(adapter);
        backBtn = view.findViewById(R.id.backBtnAllMeals);
        searchBar = view.findViewById(R.id.search_bar_AllMeal);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Retrieve data from arguments
        content = getArguments().getString("contentName", "");
        source = getArguments().getString("source","category");
        // Now 'data' contains the passed data
        Log.i(TAG, "onCreateView: AllMealsFragment " + content);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        containerTV = view.findViewById(R.id.containerTv);
        containerTV.setText(content);
        backBtn.setOnClickListener((v)-> Navigation.findNavController(v).navigateUp());
        searchBar.addTextChangedListener(new TextWatcher() {
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

    @Override
    public void onStart() {
        super.onStart();
        presenter = new AllMealPresenter(this, RepositoryImpl.getInstance(RemoteDataSourceImp.getInstance(), MealLocalDataSourceImpl.getInstance(activityCommunicator.getContext())),mealsDataList);
        switch (source) {
            case "category":
                presenter.getAllMealCategory(content);
                break;
            case "ingredient":
                presenter.getAllMealMealsIngredients(content);
                break;
            case "country":
                presenter.getAllMealCountry(content);
                break;
            default:

        }
    }
    @Override
    public void showErrorMsg(String error) {
        activityCommunicator.showToast(error);
    }
    @Override
    public void showAllMeal(List<MealsData> mealsDataList) {
        this.mealsDataList = mealsDataList;
        presenter.setModelList(mealsDataList);
        adapter.setMealsDataList(mealsDataList);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void goToMealDerails(String mealId) {
        activityCommunicator.goToMealDetails(mealId);
    }

    @Override
    public void updateUI(List<MealsData> models) {
        adapter.setMealsDataList(models);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        activityCommunicator.showToast(error);
    }
}