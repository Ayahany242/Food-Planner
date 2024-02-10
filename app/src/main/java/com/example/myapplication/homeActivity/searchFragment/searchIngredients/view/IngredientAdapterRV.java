package com.example.myapplication.homeActivity.searchFragment.searchIngredients.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.homeActivity.searchFragment.view.onCardClickedListenerSearch;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;
import com.example.myapplication.mealDetails.model.IngredientModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class IngredientAdapterRV extends  RecyclerView.Adapter<IngredientAdapterRV.ViewHolder> {
    private static final String TAG ="tag";
    private List<IngredientModel> ingredients;
    private onCardClickedListenerSearch.CardListener cardListener;
    private HomeActivityCommunicator communicator;
    public IngredientAdapterRV(HomeActivityCommunicator communicator, List<IngredientModel> ingredients){
        this.ingredients =ingredients;
        this.communicator = communicator;
       // this.cardListener = cardListener;
        Log.i(TAG, "IngredientAdapterRV: ingredients size "+ingredients.size());
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)communicator.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.ingredient_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IngredientModel model = ingredients.get(position);
        Glide.with(communicator.getContext())
                .load(model.getImage()).apply(new RequestOptions()
                        .override(holder.ingredientImage.getWidth(),holder.ingredientImage.getHeight()))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.ingredientImage);
        holder.ingredientTv.setText(model.getName());
        holder.cardView.setOnClickListener(v -> communicator.goToAllMealData(model.getName(),"ingredient"));
    }

    public void setIngredients(List<IngredientModel> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView ingredientTv;
        CircleImageView ingredientImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView =itemView.findViewById(R.id.ingredientCardView);
            ingredientImage = itemView.findViewById(R.id.ingredientImage);
            ingredientTv = itemView.findViewById(R.id.ingredientTv);
        }
    }
}
