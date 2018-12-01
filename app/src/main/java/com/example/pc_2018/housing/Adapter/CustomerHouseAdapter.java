package com.example.pc_2018.housing.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentHouse.getPlace());

        Button requestBtn = (Button) listItem.findViewById(R.id.requestBtn);


         final SharedPreferences sharedPref = mContext.getSharedPreferences(
                mContext.getString(R.string.preference_file_key), Context.MODE_PRIVATE);



        if(!sharedPref.getString("userid","0").equals("0") && currentHouse.getIsPayed().equals("0")){
            requestBtn.setVisibility(View.VISIBLE);
        }

        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mContext,SendMessage.class);
                intent.putExtra("houseID",String.valueOf(currentHouse.getId()));
                mContext.startActivity(intent);
            }
        });


        TextView textView9 = (TextView) listItem.findViewById(R.id.textView9);

        if(currentHouse.getIsPayed().equals("1")){
            textView9.setText("Already Payed");
            requestBtn.setVisibility(View.INVISIBLE);
        }
          TextView release = (TextView) listItem.findViewById(R.id.textView_release);
         release.setText(currentHouse.getHpuseNumber());

        TextView priceLbl = (TextView) listItem.findViewById(R.id.priceLbl);
        priceLbl.setText(currentHouse.getPrice() + "$");


        TextView userLbl = (TextView) listItem.findViewById(R.id.userLbl);
        userLbl.setText(currentHouse.getOwnername());

        return listItem;
    }
}
