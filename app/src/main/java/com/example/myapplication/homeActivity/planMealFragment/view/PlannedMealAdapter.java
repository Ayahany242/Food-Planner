package com.example.myapplication.homeActivity.planMealFragment.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.homeActivity.planMealFragment.model.MealsPlan;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;

import java.util.List;

public class PlannedMealAdapter extends RecyclerView.Adapter<PlannedMealAdapter.ViewHolder> {
    private List<MealsPlan> mealsPlansList;
    private static final String TAG = "TAG";
    private HomeActivityCommunicator communicator;
    private DeleteBtnListener listener;
    public PlannedMealAdapter(HomeActivityCommunicator communicator,List<MealsPlan> mealsPlansList,DeleteBtnListener listener){
        this.communicator = communicator;
        this.mealsPlansList = mealsPlansList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public PlannedMealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)communicator.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.planned_meal_item,parent,false);
        Log.i(TAG, "onCreateViewHolder Adapter Called ");
        return new PlannedMealAdapter.ViewHolder(view);
    }

    public void setMealsPlansList(List<MealsPlan> mealsPlans) {
        this.mealsPlansList = mealsPlans;
    }
    @Override
    public void onBindViewHolder(@NonNull PlannedMealAdapter.ViewHolder holder, int position) {
        MealsPlan item = mealsPlansList.get(position);
        Log.i(TAG, "onCreateViewHolder Adapter Called "+mealsPlansList.size());
        holder.categoryTv.setText(item.getStrCategory());
        holder.areaTv.setText(item.getStrArea());
        holder.titleTv.setText(item.getStrMeal());
        Glide.with(communicator.getContext()).load(item.getStrMealThumb())
                .apply(new RequestOptions().override(holder.imageView.getWidth(),holder.imageView.getHeight()))
                .into(holder.imageView);
        holder.removeBtn.setOnClickListener((v)->{
            listener.deletePlannedMeal(item);
        });
        holder.cardView.setOnClickListener((v)->communicator.goToMealDetails(item.getIdMeal()));
    }

    @Override
    public int getItemCount() {
        return mealsPlansList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView imageView;
        private TextView titleTv;
        private TextView areaTv;
        private TextView categoryTv;
        private ImageButton removeBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.plannedMealsCardView);
            imageView =itemView.findViewById(R.id.mealPlanImage);
            titleTv = itemView.findViewById(R.id.mealPlanTitleTv);
            areaTv = itemView.findViewById(R.id.mealPlanAreaTv);
            categoryTv = itemView.findViewById(R.id.categoryMealPlan);
            removeBtn = itemView.findViewById(R.id.deleteMealPlanBtn);
        }
    }
}
