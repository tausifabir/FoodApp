package com.example.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodapp.Adapter.StaticRvAdapter;
import com.example.foodapp.Model.StaticRVModel;
import com.example.foodapp.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StaticRvAdapter staticRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.staticRecyclerView);



        ArrayList<StaticRVModel> items = new ArrayList<>();

        items.add(new StaticRVModel(R.drawable.icecream,"Ice Cream"));
        items.add(new StaticRVModel(R.drawable.hamburger,"Burger"));
        items.add(new StaticRVModel(R.drawable.pizza,"Pizza"));
        items.add(new StaticRVModel(R.drawable.frenchfries,"French Fries"));

        staticRvAdapter = new StaticRvAdapter(HomeActivity.this,items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(staticRvAdapter);



    }
}