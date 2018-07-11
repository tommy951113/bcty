package com.example.tommy.bcty.sportlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tommy.bcty.R;

import java.util.List;

/**
 * Created by tommy on 2018/6/25.
 */

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.ViewHolder> {

    private List<Sport> mSportList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView sportImage;
        TextView txtStadium;
        TextView txtPeriod;
        TextView txtPerson;


        public ViewHolder(View itemView) {
            super(itemView);
            sportImage = itemView.findViewById(R.id.sport_image);
            txtStadium = itemView.findViewById(R.id.item_stadium);
            txtPeriod = itemView.findViewById(R.id.item_period);
            txtPerson = itemView.findViewById(R.id.item_person);
        }
    }

    public SportAdapter(List<Sport> sportList) {
        mSportList = sportList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sport_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Sport sport = mSportList.get(position);
        holder.sportImage.setImageResource(sport.getSportImageId());
        holder.txtStadium.setText(sport.getStatium());
        holder.txtPerson.setText(sport.getStu_num());
    }

    @Override
    public int getItemCount() {
        return mSportList.size();
    }
}
