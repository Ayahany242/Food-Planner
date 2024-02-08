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
import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.homeActivity.searchFragment.view.onCardClickedListenerSearch;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;

import java.util.List;

public class ResultSearchAdapterRv extends RecyclerView.Adapter<ResultSearchAdapterRv.ViewHolder> {
    private List<MealsItem> mealsDataList;
    private HomeActivityCommunicator communicator;
    private onCardClickedListenerSearch.CardListenerMealDetails clickedListener;

    public ResultSearchAdapterRv(List<MealsItem> mealsDataList, HomeActivityCommunicator communicator, onCardClickedListenerSearch.CardListenerMealDetails clickedListener) {
        this.mealsDataList = mealsDataList;
        this.communicator = communicator;
        this.clickedListener = clickedListener;
    }

    @NonNull
    @Override
    public ResultSearchAdapterRv.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)communicator.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.search_item_row,parent,false);
        return new ResultSearchAdapterRv.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealsItem meal = mealsDataList.get(position);
        holder.areaMealTv.setText(meal.getStrArea());
        holder.titleMealTv.setText(meal.getStrMeal());
        Glide.with(communicator.getContext()).load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(holder.mealImage.getWidth(),holder.mealImage.getHeight())
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)).into(holder.mealImage);
        holder.categoryMealTv.setText(meal.getStrCategory());
        holder.cardView.setOnClickListener((v)->clickedListener.goToMealDerails(meal.getIdMeal()));
    }

    public void setMealsDataList(List<MealsItem> mealsDataList) {
        this.mealsDataList = mealsDataList;
    }

    @Override
    public int getItemCount() {
        return mealsDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mealImage;
        private TextView titleMealTv;
        private TextView areaMealTv;
        private TextView categoryMealTv;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage =itemView.findViewById(R.id.imageMealView);
            titleMealTv = itemView.findViewById(R.id.titleMealTvRow);
            areaMealTv = itemView.findViewById(R.id.areaMeal);
            categoryMealTv = itemView.findViewById(R.id.categoryMealTv);
            cardView = itemView.findViewById(R.id.materialCardView);
        }
    }
}
