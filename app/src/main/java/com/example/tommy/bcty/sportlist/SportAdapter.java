package com.example.tommy.bcty.sportlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tommy.bcty.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tommy on 2018/6/25.
 */

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.ViewHolder> {

    private List<Sport> mSportList;
    private Map<String,Integer> sportImageMap = new HashMap<>();


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
        putImageItems();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sport_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Sport sport = mSportList.get(position);
        holder.sportImage.setImageResource(sportImageMap.get(sport.getType()));
        holder.txtStadium.setText(sport.getStatium());
        holder.txtPeriod.setText(sport.getTime());
        holder.txtPerson.setText(sport.getStu_num() + "人");
    }

    @Override
    public int getItemCount() {
        return mSportList.size();
    }

    private void putImageItems(){
        sportImageMap.put("羽毛球",R.drawable.tennis);
        sportImageMap.put("篮球",R.drawable.basketball);
        sportImageMap.put("乒乓球",R.drawable.pingpong);
        sportImageMap.put("跑步",R.drawable.running);
        sportImageMap.put("健身",R.drawable.gym);
        sportImageMap.put("足球",R.drawable.soccer);
        sportImageMap.put("排球",R.drawable.volleyball);
    }
}
