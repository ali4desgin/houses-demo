package com.example.pc_2018.housing.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc_2018.housing.EditHouse;
import com.example.pc_2018.housing.Models.HouseMod;
import com.example.pc_2018.housing.R;
import com.example.pc_2018.housing.SendMessage;

import java.util.ArrayList;
import java.util.List;

public class CustomerHouseAdapter extends ArrayAdapter<HouseMod> {

    private Context mContext;
    private List<HouseMod> moviesList = new ArrayList<>();

    public CustomerHouseAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<HouseMod> list) {
        super(context, 0 , list);
        mContext = context;
        moviesList = list;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.customer_house_item,parent,false);

        final HouseMod currentHouse = moviesList.get(position);

           ImageView image = (ImageView)listItem.findViewById(R.id.imageView_poster);
           image.setImageBitmap(currentHouse.getImage());
//        image.setImageResource(currentHouse.getmImageDrawable());
//
        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentHouse.getHpuseNumber());

        Button requestBtn = (Button) listItem.findViewById(R.id.requestBtn);
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SendMessage.class);
                intent.putExtra("id",currentHouse.getId());
                mContext.startActivity(intent);
                //Toast.makeText(mContext,String.valueOf(position),Toast.LENGTH_LONG).show();
            }
        });

          TextView release = (TextView) listItem.findViewById(R.id.textView_release);
         release.setText(currentHouse.getHpuseNumber());

        TextView priceLbl = (TextView) listItem.findViewById(R.id.priceLbl);
        priceLbl.setText(currentHouse.getPrice() + "$");


        TextView userLbl = (TextView) listItem.findViewById(R.id.userLbl);
        userLbl.setText(currentHouse.getOwnername());

        return listItem;
    }
}
