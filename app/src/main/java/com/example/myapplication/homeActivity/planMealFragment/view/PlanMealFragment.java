package com.example.myapplication.homeActivity.planMealFragment.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.HorizontalItemDecoration;
import com.example.myapplication.R;
import com.example.myapplication.homeActivity.favoriteMealsFragment.view.FavoriteMealsRVAdapter;
import com.example.myapplication.homeActivity.planMealFragment.model.MealsPlan;
import com.example.myapplication.homeActivity.planMealFragment.presenter.PlannedMealPresenter;
import com.example.myapplication.homeActivity.planMealFragment.presenter.PlannedMealsContract;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;
import com.example.myapplication.repository.RepositoryImpl;
import com.example.myapplication.repository.localData.MealLocalDataSourceImpl;
import com.example.myapplication.repository.network.RemoteDataSourceImp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PlanMealFragment extends Fragment implements PlannedMealsContract.View, DeleteBtnListener {
    private LinearLayoutManager manager;
    private RecyclerView recyclerView;
    private HomeActivityCommunicator communicator;
    private PlannedMealAdapter adapter;
    private CalendarView calendarView;
    private String dateWithDayOfWeek;
    private PlannedMealsContract.Presenter presenter;
    private String dayOfWeek,selectedDate;
    private TextView dateTv;
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
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plan_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new PlannedMealPresenter(this, RepositoryImpl.getInstance(RemoteDataSourceImp.getInstance(), MealLocalDataSourceImpl.getInstance(communicator.getContext())));
        initUI(view);
        dateWithDayOfWeek =  dayOfWeek  + " (" + selectedDate + ")";
        presenter.getAllPlannedMeal(dateWithDayOfWeek);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                calenderListener(year,month,dayOfMonth);

                //call meals on this day
                //display it
            }
        });
    }
    private void initUI(View view){
        calendarView = view.findViewById(R.id.calendarView);
        recyclerView = view.findViewById(R.id.plannedMealRv);
        dateTv = view.findViewById(R.id.dateTv);
        adapter = new PlannedMealAdapter(communicator,new ArrayList<>(),this);
        manager = new LinearLayoutManager(communicator.getContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.horizontal_spacing);
        recyclerView.addItemDecoration(new HorizontalItemDecoration(spacingInPixels));
        recyclerView.setAdapter(adapter);

    }
    private void calenderListener( int year, int month, int dayOfMonth){
        Calendar selectedCalendar = Calendar.getInstance();
        selectedCalendar.set(Calendar.YEAR, year);
        selectedCalendar.set(Calendar.MONTH, month);
        selectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        // Get the selected date in a desired format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        selectedDate = sdf.format(selectedCalendar.getTime());
        // Get the day of the week from the selected date
        SimpleDateFormat sdfDayOfWeek = new SimpleDateFormat("EEEE", Locale.getDefault());
        dayOfWeek = sdfDayOfWeek.format(selectedCalendar.getTime());
        dateWithDayOfWeek = dayOfWeek  + " (" + selectedDate + ")";
        presenter.getAllPlannedMeal(dateWithDayOfWeek);
        dateTv.setText(dayOfWeek + selectedDate);
    }

    @Override
    public void updatedUI(List<MealsPlan> mealsPlans) {
        adapter.setMealsPlansList(mealsPlans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showOnError(String error) {
        communicator.showToast(error);
    }
    @Override
    public void deletePlannedMeal(MealsPlan mealsPlan) {
        presenter.deletePlannedMeal(mealsPlan);
    }
}