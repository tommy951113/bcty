package com.example.tommy.bcty;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tommy on 2018/6/25.
 */

public class SportAdapter extends Adapter {

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView sportImage;
        TextView txtStadium;
        TextView txtPeriod;
        TextView txtPerson;


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
