package com.example.tommy.bcty.sportlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tommy.bcty.R;
import com.example.tommy.bcty.util.WebUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SportListActivity extends AppCompatActivity {

    private List<Sport> sportList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_list);
        initSports();

    }

    private void initSports() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String response = WebUtil.get("select.php");
                    Gson gson = new Gson();
                    sportList = gson.fromJson(response,new TypeToken<List<Sport>>(){}.getType());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_sport);
                            recyclerView.addItemDecoration(new DividerItemDecoration(SportListActivity.this,DividerItemDecoration.VERTICAL));
                            LinearLayoutManager layoutManger = new LinearLayoutManager(SportListActivity.this);
                            recyclerView.setLayoutManager(layoutManger);
                            SportAdapter adapter = new SportAdapter(sportList);
                            recyclerView.setAdapter(adapter);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
