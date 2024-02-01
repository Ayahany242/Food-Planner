package com.example.myapplication.homeActivity.view.homeFragment;

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
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.homeActivity.model.allCountries.Country;
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
        holder.countryTV.setText(countryList.get(position).getStrArea());
        Glide.with(communicator.getContext()).load(countriesFlags[position])
                .apply(new RequestOptions().override(60,60)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)).into(holder.imageView);
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
            imageView = itemView.findViewById(R.id.countryImage);
            countryTV = itemView.findViewById(R.id.countryTV);
            cardItem =itemView.findViewById(R.id.countryCardView);
        }
    }
}
