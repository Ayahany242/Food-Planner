package com.example.myapplication.homeActivity.homeScreen.homeFragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.homeActivity.model.allCategory.CategoriesItem;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;

import java.util.List;


public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder>{
    private static final String TAG = "TAG";
    private List<CategoriesItem> categoriesItems;
    private HomeActivityCommunicator communicator;
    private onCardClickedListener clickedListener;

    public CategoryRVAdapter(HomeActivityCommunicator communicator,List<CategoriesItem> categoriesItems,onCardClickedListener clickedListener){
        this.categoriesItems = categoriesItems;
        this.communicator = communicator;
        this.clickedListener = clickedListener;
        Log.i(TAG, "Adapter Constructor Called ");
    }
    public void setCategoriesItems(List<CategoriesItem> items){
        this.categoriesItems = items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)communicator.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_category_item,parent,false);
        Log.i(TAG, "onCreateViewHolder Adapter Called ");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoriesItem item = categoriesItems.get(position);
        Log.i(TAG, "onCreateViewHolder Adapter Called "+categoriesItems.size());
        holder.categoryTv.setText(item.getStrCategory());
        holder.descriptionTv.setText(item.getStrCategoryDescription());
        Glide.with(communicator.getContext()).load(item.getStrCategoryThumb()).into(holder.imageView);
        holder.cardView.setOnClickListener((v)->clickedListener.cardCategoryListener(item.getStrCategory()));
    }

    @Override
    public int getItemCount() {
        return categoriesItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView descriptionTv;
        private CardView cardView;
        private TextView categoryTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.countryImage);
            descriptionTv = itemView.findViewById(R.id.mealAreaTv);
            categoryTv = itemView.findViewById(R.id.categoryMeal);
            cardView = itemView.findViewById(R.id.categoryCardView);
        }
    }
}
