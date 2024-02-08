package com.example.myapplication.homeActivity.allMealsFragment.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;
import com.example.myapplication.homeActivity.allMealsFragment.model.MealsData;

import java.util.List;


public class AllMealAdapter extends RecyclerView.Adapter<AllMealAdapter.ViewHolder> {
    private static final String TAG = "TAG";
    private List<MealsData> mealsDataList;
    private HomeActivityCommunicator communicator;
    private OnClickedListener listener;
    private boolean isFavorite = false;
    public AllMealAdapter(HomeActivityCommunicator communicator, List<MealsData> mealsDataList, OnClickedListener listener){
        this.communicator = communicator;
        this.mealsDataList = mealsDataList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)communicator.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.meal_data_items_row,parent,false);
        Log.i(TAG, "onCreateViewHolder Adapter Called ");
        return new AllMealAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealsData meal = mealsDataList.get(position);
        //isMealFavourite(meal.getIdMeal());
        /*if (isFavorite) {
            holder.favoriteBtn.setImageResource(R.drawable.favorite_fill_ic);
        } else {
            holder.favoriteBtn.setImageResource(R.drawable.favorite_ic);
        }*/
        holder.mealNameTv.setText(meal.getStrMeal());
        Glide.with(communicator.getContext()).load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(holder.imageView.getWidth(),holder.imageView.getHeight())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)).into(holder.imageView);
        holder.cardView.setOnClickListener((v)-> listener.sendSelectedCard(v,meal.getIdMeal()));
    }
    public void setMealsDataList(List<MealsData> mealsDataList){
        this.mealsDataList = mealsDataList;
    }
    @Override
    public int getItemCount() {
        return mealsDataList.size();
    }
    private void isMealFavourite(String mealId){
        listener.isInFavorite(mealId);
    }
    public void setIsFavourite(boolean isExist){
        isFavorite = isExist;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private TextView mealNameTv;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.materialCardView);
            mealNameTv = itemView.findViewById(R.id.mealTitleMealData);
            imageView = itemView.findViewById(R.id.mealImg);
        }
    }

}
