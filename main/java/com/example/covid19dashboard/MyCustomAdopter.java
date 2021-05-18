package com.example.covid19dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdopter extends ArrayAdapter<CountryModel> {
    private Context context;
    private List<CountryModel> countryModels;
    private List<CountryModel> countryModelsfilterd;


    public MyCustomAdopter(@NonNull Context context, List<CountryModel> countryModels) {
        super(context, R.layout.list_custom_item,countryModels);

        this.context=context;
        this.countryModels=countryModels;
        this.countryModelsfilterd=countryModels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item,null,true);
        TextView countrynamae=view.findViewById(R.id.tvcountry);
        ImageView imageView=view.findViewById(R.id.imageFlag);

        countrynamae.setText(countryModelsfilterd.get(position).getCountry());
        Glide.with(context).load(countryModelsfilterd.get(position).getFlag()).into(imageView);

        return view;
    }

    @Override
    public int getCount() {
        return countryModelsfilterd.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelsfilterd.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults=new FilterResults();
                if (constraint==null||constraint.length()==0)
                {
                    filterResults.count=countryModels.size();
                    filterResults.values=countryModels;
                }else {
                    List<CountryModel> resultmodel=new ArrayList<>();
                    String searchstr=constraint.toString().toLowerCase();

                    for (CountryModel itemmodel:countryModels)
                    {
                        if (itemmodel.getCountry().toLowerCase().contains(searchstr))
                        {
                            resultmodel.add(itemmodel);
                        }
                        filterResults.count=resultmodel.size();
                        filterResults.values=resultmodel;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                countryModelsfilterd=(List<CountryModel>) results.values;
                AffectedCountry.countryModelList=(List<CountryModel>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
