package com.example.myapplication.homeActivity.view.homeFragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.homeActivity.model.allCategory.CategoriesItem;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;

import java.util.List;


public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder>{
    private static final String TAG = "TAG";
    List<CategoriesItem> categoriesItems;
    private HomeActivityCommunicator communicator;
    public CategoryRVAdapter(HomeActivityCommunicator communicator,List<CategoriesItem> categoriesItems){
        this.categoriesItems = categoriesItems;
        this.communicator = communicator;
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
    }

    @Override
    public int getItemCount() {
        return categoriesItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView descriptionTv;
        private TextView categoryTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.countryImage);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);
            categoryTv = itemView.findViewById(R.id.countryTV);
        }
    }
}

/*
public class CategoryRVAdapter extends PagerAdapter{
    private static final String TAG = "tag";
    List<CategoriesItem> categoriesItems;
    private HomeActivityCommunicator communicator;
    private ImageView imageView;
    private TextView descriptionTv;
    private TextView categoryTv;
    public CategoryRVAdapter(HomeActivityCommunicator communicator,List<CategoriesItem> categoriesItems){
        this.categoriesItems = categoriesItems;
        this.communicator = communicator;
        Log.i(TAG, "Adapter Constructor Called ");
    }
    public void setCategoriesItems(List<CategoriesItem> items){
        this.categoriesItems = items;
    }
    @Override
    public int getCount() {
        return categoriesItems.size();
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater)communicator.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_category_item,container,false);
        initUI(view);
        setData(categoriesItems.get(position));
        container.addView(view);
        return view;
    }
    private void initUI(View view){
        imageView = view.findViewById(R.id.categoryImage);
        descriptionTv = view.findViewById(R.id.descriptionTv);
        categoryTv = view.findViewById(R.id.categoryTv);
    }
    private void setData(CategoriesItem item){
        Log.i(TAG, "onCreateViewHolder Adapter Called "+categoriesItems.size());
        categoryTv.setText(item.getStrCategory());
        descriptionTv.setText(item.getStrCategoryDescription());
        Glide.with(communicator.getContext()).load(item.getStrCategoryThumb()).into(imageView);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}*/
