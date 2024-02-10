package com.example.myapplication.homeActivity.searchFragment.searchByName.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.homeActivity.allMealsFragment.model.MealsData;
import com.example.myapplication.homeActivity.searchFragment.view.onCardClickedListenerSearch;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;

import java.util.List;

public class ResultMealDataAdapter extends RecyclerView.Adapter<ResultMealDataAdapter.ViewHolder> {
    private List<MealsData> mealsDataList;
    private HomeActivityCommunicator communicator;
    private onCardClickedListenerSearch.CardListenerMealDetails clickedListener;

    public ResultMealDataAdapter(HomeActivityCommunicator communicator,List<MealsData> mealsDataList,  onCardClickedListenerSearch.CardListenerMealDetails clickedListener) {
        this.mealsDataList = mealsDataList;
        this.communicator = communicator;
        this.clickedListener = clickedListener;
    }

    @NonNull
    @Override
    public ResultMealDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)communicator.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.meal_data_items_row,parent,false);
        return new ResultMealDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultMealDataAdapter.ViewHolder holder, int position) {
        MealsData meal = mealsDataList.get(position);
        holder.titleMealTv.setText(meal.getStrMeal());
        Glide.with(communicator.getContext()).load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(holder.mealImage.getWidth(),holder.mealImage.getHeight())
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)).into(holder.mealImage);
        holder.cardView.setOnClickListener((v)->clickedListener.goToMealDerails(meal.getIdMeal()));
    }

    public void setMealsDataList(List<MealsData> mealsDataList) {
        this.mealsDataList = mealsDataList;
    }

    @Override
    public int getItemCount() {
        return mealsDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mealImage;
        private TextView titleMealTv;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage =itemView.findViewById(R.id.mealImg);
            titleMealTv = itemView.findViewById(R.id.mealTitleMealData);
            cardView = itemView.findViewById(R.id.materialCardView);
        }
    }
}
