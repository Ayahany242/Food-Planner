package com.example.myapplication.homeActivity.favoriteMealsFragment.view;

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
import com.example.myapplication.R;
import com.example.myapplication.homeActivity.model.mealData.MealsItem;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;

import java.util.List;

public class FavoriteMealsRVAdapter extends RecyclerView.Adapter<FavoriteMealsRVAdapter.ViewHolder>{
    private static final String TAG = "TAG";
    private List<MealsItem> mealsItems;
    private HomeActivityCommunicator communicator;
    private FavoriteBtnListener listener;
    public FavoriteMealsRVAdapter(HomeActivityCommunicator communicator, List<MealsItem> mealsItems,FavoriteBtnListener listener ){
        this.communicator = communicator;
        this.mealsItems = mealsItems;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)communicator.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.meal_item_row,parent,false);
        Log.i(TAG, "onCreateViewHolder Adapter Called ");
        return new FavoriteMealsRVAdapter.ViewHolder(view);
    }

    public void setMealsItems(List<MealsItem> mealsItems) {
        this.mealsItems = mealsItems;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealsItem item = mealsItems.get(position);
        Log.i(TAG, "onCreateViewHolder Adapter Called "+mealsItems.size());
        holder.categoryTv.setText(item.getStrCategory());
        holder.areaTv.setText(item.getStrArea());
        holder.titleTv.setText(item.getStrMeal());
        Glide.with(communicator.getContext()).load(item.getStrMealThumb()).into(holder.imageView);
        holder.favoriteBtn.setOnClickListener((v)->{
            listener.onFavoriteBtnListener(item);
        });
        holder.cardView.setOnClickListener((v)->communicator.goToMealDetails(item.getIdMeal()));
    }

    @Override
    public int getItemCount() {
        return mealsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titleTv;
        private TextView categoryTv;
        private TextView areaTv;
        private ImageButton favoriteBtn;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.favoriteMealsCardView);
            imageView = itemView.findViewById(R.id.mealImage);
            titleTv = itemView.findViewById(R.id.mealTitleTv);
            favoriteBtn = itemView.findViewById(R.id.favouriteMealBtn);
            categoryTv = itemView.findViewById(R.id.categoryMeal);
            areaTv = itemView.findViewById(R.id.mealAreaTv);
        }
    }
}
