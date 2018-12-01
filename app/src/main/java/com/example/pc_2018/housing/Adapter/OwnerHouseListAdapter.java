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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc_2018.housing.Const;
import com.example.pc_2018.housing.EditHouse;
import com.example.pc_2018.housing.Models.HouseMod;
import com.example.pc_2018.housing.OwnerPage;
import com.example.pc_2018.housing.R;
import com.example.pc_2018.housing.SendMessage;
import com.example.pc_2018.housing.SignUpowner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OwnerHouseListAdapter  extends ArrayAdapter<HouseMod> {

    private Context mContext;
    private List<HouseMod> moviesList = new ArrayList<>();

    public OwnerHouseListAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<HouseMod> list) {
        super(context, 0 , list);
        mContext = context;
        moviesList = list;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.owner_house_item,parent,false);

        final HouseMod currentMovie = moviesList.get(position);


        TextView priceLbl = (TextView) listItem.findViewById(R.id.priceLbl);
        priceLbl.setText(currentMovie.getPrice() + "$");


        TextView userLbl = (TextView) listItem.findViewById(R.id.userLbl);
        userLbl.setText(currentMovie.getOwnername());



        ImageView image = (ImageView)listItem.findViewById(R.id.imageView_poster);
        image.setImageBitmap(currentMovie.getImage());
//        image.setImageResource(currentMovie.getmImageDrawable());
//
//        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
//        name.setText(currentMovie.getmName());


        Button deletButton = (Button) listItem.findViewById(R.id.deleteBtn);
        Button editButton = (Button) listItem.findViewById(R.id.requestBtn);


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,EditHouse.class);
                intent.putExtra("houseID",String.valueOf(currentMovie.getId()));
                mContext.startActivity(intent);
               // Toast.makeText(mContext,String.valueOf(position),Toast.LENGTH_LONG).show();
            }
        });


        deletButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue queue = Volley.newRequestQueue(mContext);
                String url = Const.delete;

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if (jsonObject.getBoolean("response")){



                                        Intent intent = new Intent(mContext,OwnerPage.class);
                                        mContext.startActivity(intent);

                                    }else{

                                        String message = jsonObject.getString("message");
                                        Toast.makeText(mContext,message,Toast.LENGTH_LONG).show();

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String , String> map = new HashMap<>();
                        map.put("id",String.valueOf(currentMovie.getId()));

                        return map;
                    }
                };

                // Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });
        TextView release = (TextView) listItem.findViewById(R.id.textView_release);
        release.setText(currentMovie.getHpuseNumber());
        return listItem;
    }
}
