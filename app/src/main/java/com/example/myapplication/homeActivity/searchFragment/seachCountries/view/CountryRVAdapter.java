package com.example.myapplication.homeActivity.searchFragment.seachCountries.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.homeActivity.model.allCountries.Country;
import com.example.myapplication.homeActivity.searchFragment.view.onCardClickedListenerSearch;
import com.example.myapplication.homeActivity.view.HomeActivityCommunicator;

import java.util.List;

public class CountryRVAdapter extends RecyclerView.Adapter<CountryRVAdapter.ViewHolder>{
    private static final String TAG = "tag";
    List<Country> countryList;
    String [] countriesFlags;
    private HomeActivityCommunicator communicator;

    public CountryRVAdapter(HomeActivityCommunicator communicator, List<Country>  countryList){
        this.countryList = countryList;
        this.communicator = communicator;
      //  this.clickedListener = clickedListener;
        countriesFlags = communicator.getContext().getResources().getStringArray(R.array.flags);
        Log.i(TAG, "Adapter Constructor Called ");
    }
    public void setCountryList(List<Country> countryList){
        this.countryList = countryList;
    }
    @NonNull
    @Override
    public CountryRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)communicator.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.country_item_card,parent,false);
        Log.i(TAG, "onCreateViewHolder Adapter Called ");
        return new CountryRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i(TAG, "onCreateViewHolder Country Adapter Called "+countryList.size());
        Country country = countryList.get(position);
        holder.countryTV.setText(country.getStrArea());
        Glide.with(communicator.getContext()).load(country.getStrFlag())
                .apply(new RequestOptions().override(holder.imageView.getWidth(),holder.imageView.getHeight())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)).into(holder.imageView);
       holder.cardItem.setOnClickListener((v)-> communicator.goToAllMealData(country.getStrArea(),"country"));
    }
    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView countryTV;
        CardView cardItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.countryImageHome);
            countryTV = itemView.findViewById(R.id.countryTv);
            cardItem =itemView.findViewById(R.id.countryCardView);
        }
    }
}
