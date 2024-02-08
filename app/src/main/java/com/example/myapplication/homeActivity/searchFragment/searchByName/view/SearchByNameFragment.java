package com.example.myapplication.homeActivity.searchFragment.searchByName.view;

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
import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.homeActivity.searchFragment.searchByName.presenter.SearchByNameContract;
import com.example.myapplication.homeActivity.searchFragment.searchByName.presenter.SearchResultPresenter;
import com.example.myapplication.homeActivity.searchFragment.view.onCardClickedListenerSearch;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;
import com.example.myapplication.repository.RepositoryImpl;
import com.example.myapplication.repository.localData.MealLocalDataSourceImpl;
import com.example.myapplication.repository.network.RemoteDataSourceImp;

import java.util.ArrayList;
import java.util.List;

public class SearchByNameFragment extends Fragment implements onCardClickedListenerSearch.CardListenerMealDetails, SearchByNameContract.View {
    private ImageButton backButton;
    private static final String TAG = "search";
    private ResultSearchAdapterRv adapterRv;
    private HomeActivityCommunicator mainCommunication;
    SearchByNameContract.Presenter presenter;
    private EditText search;
    private RecyclerView resultRv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_by_name, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter = new SearchResultPresenter(this, RepositoryImpl.getInstance(RemoteDataSourceImp.getInstance(), MealLocalDataSourceImpl.getInstance(mainCommunication.getContext())));
        search.setText("");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        backButton.setOnClickListener((v) ->Navigation.findNavController(v).navigateUp());
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (presenter != null){
                    presenter.search(s.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void initUI(View view){
        backButton = view.findViewById(R.id.backBtnByNameFragment);
        resultRv = view.findViewById(R.id.resultSearchByName);
        search =view.findViewById(R.id.search_bar_name);
        adapterRv = new ResultSearchAdapterRv(new ArrayList<>(),mainCommunication,this );
        GridLayoutManager manager = new GridLayoutManager(mainCommunication.getContext(),2);
        manager.setOrientation(RecyclerView.VERTICAL);
        resultRv.setLayoutManager(manager);
        resultRv.setAdapter(adapterRv);
    }

    @Override
    public void showResult(List<MealsItem> mealsItems) {
        Log.i(TAG, "showResult:showResult(List<MealsItem> mealsItems  "+mealsItems.size());
        adapterRv.setMealsDataList(mealsItems);
        adapterRv.notifyDataSetChanged();
    }

    @Override
    public void showErrorMsg(String error) {
        Log.i(TAG, "showErrorMsg: "+error);
    }

    @Override
    public void goToMealDerails(String mealId) {
//        Log.i(TAG, "cardCountryListener: card item selected "+mea);
        mainCommunication.goToMealDetails(mealId);
    }

    @Override
    public void onStop() {
        super.onStop();

    }
}