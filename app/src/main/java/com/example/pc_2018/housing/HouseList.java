package com.example.pc_2018.housing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pc_2018.housing.Adapter.OwnerHouseListAdapter;
import com.example.pc_2018.housing.Models.HouseMod;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.pc_2018.housing.R.layout.owner_house_item;

public class HouseList extends AppCompatActivity {

    ListView listView;
    private OwnerHouseListAdapter ownerHouseListAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_list);
        listView = (ListView) findViewById(R.id.list_view);


        listView = (ListView) findViewById(R.id.list_view);
        ArrayList<HouseMod> moviesList = new ArrayList<>();
        moviesList.add(new HouseMod(R.drawable.b2, "After Earth" , "2013"));
        moviesList.add(new HouseMod(R.drawable.b2, "Baby Driver" , "2017"));
        moviesList.add(new HouseMod(R.drawable.b4, "Deadpool" , "2016"));
        moviesList.add(new HouseMod(R.drawable.b5, "Divergent" , "2014"));
        moviesList.add(new HouseMod(R.drawable.b1, "Fight Club" , "1999"));
        moviesList.add(new HouseMod(R.drawable.b3, "Jaws" , "1975"));

        ownerHouseListAdapter = new OwnerHouseListAdapter(this,moviesList);
        listView.setAdapter(ownerHouseListAdapter);




        String[] planets = new String[] {};




    }
}
