package com.example.myapplication.mealDetails.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.HorizontalItemDecoration;
import com.example.myapplication.R;
import com.example.myapplication.homeActivity.planMealFragment.model.MealsPlan;
import com.example.myapplication.homeActivity.searchFragment.searchIngredients.view.IngredientAdapterRV;
import com.example.myapplication.homeActivity.view.HomeActivity;
import com.example.myapplication.mealDetails.model.IngredientModel;
import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.mealDetails.presenter.MealDetailContract;
import com.example.myapplication.mealDetails.presenter.MealDetailPresenter;
import com.example.myapplication.repository.RepositoryImpl;
import com.example.myapplication.repository.localData.MealLocalDataSourceImpl;
import com.example.myapplication.repository.network.RemoteDataSourceImp;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MealsDetailsActivity extends AppCompatActivity implements MealDetailContract.View{
    private static final String TAG ="details";
    private static final String  Ingredient_URL = "https://www.themealdb.com/images/ingredients/";
    private ImageView mealImage;
    private CoordinatorLayout layout;
    private ImageButton favoriteBtn, calenderBtn;
    private TextView mealName,categoryName,areaName,steps;
    private YouTubePlayerView youTubePlayerView;
    private RecyclerView recyclerView;
    private MealDetailContract.Presenter presenter;
    private IngredientAdapterMealDetails adapter;
    private LinearLayoutManager manager;
    private List<IngredientModel> ingredientsList;
    private String videoId;
    private MealsItem meal;
    private boolean isFavorite;
    private ImageButton backBtn;
    private String dateWithDayOfWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_data);

        Intent intent = getIntent();
        String mealId = intent.getStringExtra(HomeActivity.SELECTED_MEAL);
        Log.i(TAG, "onCreate: MealsDataActivity "+mealId);
        presenter = new MealDetailPresenter(this, RepositoryImpl.getInstance(RemoteDataSourceImp.getInstance(), MealLocalDataSourceImpl.getInstance(this)),this);
        Log.i("TAG", "onStart: meal id "+mealId);
        initUI();
        presenter.getMealDetails(mealId);
        presenter.isMealExists(mealId);
        favoriteBtn.setOnClickListener((v)-> {
            if (isFavorite){
                presenter.deleteFavoriteMeal(meal);
                favoriteBtn.setImageResource(R.drawable.favorite_ic);
                Log.i(TAG, "onClick: deleteMealFromFavorite isFavorite ");
            }else {
                presenter.addFavoriteMeal(meal);
                Log.i(TAG, "onClick: addMealToFavorite isFavorite "+ isFavorite +" randomMeal.getIdMeal() ");
                favoriteBtn.setImageResource(R.drawable.favorite_fill_ic);
            }
            isFavorite = !isFavorite;
            Log.i(TAG, "after clicked onClick: isFavorite "+ isFavorite);
        });
        backBtn.setOnClickListener((v)-> startActivity(new Intent(this, HomeActivity.class)));
        calenderBtn.setOnClickListener((v)-> {
            showCalender();
            Log.i(TAG, "showCalender: "+dateWithDayOfWeek);
        });
    }
    private void initUI(){
        layout = findViewById(R.id.mealDetailsLayout);
        mealImage =findViewById(R.id.mealImageDetail);
        mealName = findViewById(R.id.mealName);
        favoriteBtn = findViewById(R.id.favBtnIcon);
        calenderBtn = findViewById(R.id.calenderBtn);
        categoryName = findViewById(R.id.mealCategoryDetails);
        steps = findViewById(R.id.stepsTv);
        areaName =findViewById(R.id.mealAreaDetails);
        recyclerView = findViewById(R.id.ingredientsMealDetail);
        youTubePlayerView =findViewById(R.id.videoView);
        ingredientsList  = new ArrayList<>();
        adapter = new IngredientAdapterMealDetails(this,ingredientsList);
        //categoryRecycleView
        manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.horizontal_spacing);
        recyclerView.addItemDecoration(new HorizontalItemDecoration(spacingInPixels));
        recyclerView.setAdapter(adapter);
        backBtn = findViewById(R.id.backBtnMealDetails);


    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void showMeal(MealsItem meal) {
        this.meal = meal;
        Glide.with(getApplication()).load(meal.getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(mealImage);
        mealName.setText(meal.getStrMeal());
        categoryName.setText(meal.getStrCategory());
        steps.setText(meal.getStrInstructions());
        areaName.setText(meal.getStrArea());
        setVideoId(meal.getStrYoutube());
        Log.i(TAG, "showMeal: videooooooooo"+meal.getStrYoutube());
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                youTubePlayer.loadVideo(videoId, 0);
            }
            /*@Override
            public void onStateChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerState state) {
                super.onStateChange(youTubePlayer, state);
            }*/
        });
        setIngredeintList(meal);
        Log.i(TAG, "showMeal: ingredients list "+ingredientsList.size());
        adapter.setIngredients(ingredientsList);
        adapter.notifyDataSetChanged();
    }
    private void setIngredeintList(MealsItem model) {
        if(model.getStrIngredient1()!="")
            ingredientsList .add(new IngredientModel(model.getStrIngredient1(),Ingredient_URL+model.getStrIngredient1()+"-Small.png"));
        if(!model.getStrIngredient2().equals(""))
            ingredientsList.add(new IngredientModel(model.getStrIngredient2(),Ingredient_URL+model.getStrIngredient2()+"-Small.png"));
        if(!model.getStrIngredient3().equals(""))
            ingredientsList.add(new IngredientModel(model.getStrIngredient3(),Ingredient_URL+model.getStrIngredient3()+"-Small.png"));
        if(!model.getStrIngredient4().equals(""))
            ingredientsList.add(new IngredientModel(model.getStrIngredient4(),Ingredient_URL+model.getStrIngredient4()+"-Small.png"));
        if(!model.getStrIngredient5().equals(""))
            ingredientsList.add(new IngredientModel(model.getStrIngredient5(),Ingredient_URL+model.getStrIngredient5()+"-Small.png"));
        if(!model.getStrIngredient6().equals(""))
            ingredientsList.add(new IngredientModel(model.getStrIngredient6(),Ingredient_URL+model.getStrIngredient6()+"-Small.png"));
        if(!model.getStrIngredient7().equals(""))
            ingredientsList.add(new IngredientModel(model.getStrIngredient7(),Ingredient_URL+model.getStrIngredient7()+"-Small.png"));
        if(!model.getStrIngredient8().equals(""))
            ingredientsList.add(new IngredientModel(model.getStrIngredient8(),Ingredient_URL+model.getStrIngredient8()+"-Small.png"));
        if(!model.getStrIngredient9().equals(""))
            ingredientsList.add(new IngredientModel(model.getStrIngredient9(),Ingredient_URL+model.getStrIngredient9()+"-Small.png"));
        if(!model.getStrIngredient10().equals(""))
            ingredientsList.add(new IngredientModel(model.getStrIngredient10(),Ingredient_URL+model.getStrIngredient10()+"-Small.png"));
        if(!model.getStrIngredient11().equals(""))
            ingredientsList.add(new IngredientModel(model.getStrIngredient11(),Ingredient_URL+model.getStrIngredient11()+"-Small.png"));
        if(!model.getStrIngredient12().equals(""))
            ingredientsList.add(new IngredientModel(model.getStrIngredient12(),Ingredient_URL+model.getStrIngredient12()+"-Small.png"));
    }
    private void setVideoId(String youtubeUrl){
        Log.i(TAG, "setVideoId: "+youtubeUrl);
        if (!youtubeUrl.isEmpty()){
            String[] id = youtubeUrl.split("=");
            videoId = id[1];
            Log.i(TAG, "setVideoId: videoId "+videoId);
        }else{
            videoId = " ";
        }
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

    @Override
    public void addToPlannedMeal(MealsPlan mealsPlan) {

    }

    private void showCalender(){
        Calendar calendar = Calendar.getInstance();
        long today = calendar.getTimeInMillis();
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        long startOfWeek = calendar.getTimeInMillis();
        // Set the end date of the week
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        long endOfWeek = calendar.getTimeInMillis();
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setCalendarConstraints(new CalendarConstraints.Builder()
                        .setValidator(DateValidatorPointForward.now()) // Restrict to future dates only
                        .setStart(today) // Start from today
                        .setEnd(endOfWeek)
                        .build())
                .build();
        datePicker.addOnPositiveButtonClickListener(selection -> {
            MealsPlan mealsPlan = new MealsPlan();
            mealsPlan.copyMeal(meal);
            // Convert milliseconds to a Date object
            Date selectedDate = new Date(selection);
            // Format the selected date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String formattedDate = sdf.format(selectedDate);
            // Get the day of the week
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
            String dayOfWeek = dayFormat.format(selectedDate);
            dateWithDayOfWeek = dayOfWeek  + " (" + formattedDate + ")";
            mealsPlan.setDateWithDayOfWeek(dateWithDayOfWeek);
            presenter.addToPlannedMeal(mealsPlan);
            Log.i(TAG, "showCalender: "+dateWithDayOfWeek);
        });
        datePicker.show(getSupportFragmentManager(),"tag");
    }

}