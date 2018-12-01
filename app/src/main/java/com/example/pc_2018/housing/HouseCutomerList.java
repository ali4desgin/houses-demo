package com.example.pc_2018.housing;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc_2018.housing.Adapter.CustomerHouseAdapter;
import com.example.pc_2018.housing.Adapter.OwnerHouseListAdapter;
import com.example.pc_2018.housing.Models.HouseMod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HouseCutomerList extends AppCompatActivity {

    ListView listView;
    private CustomerHouseAdapter customerHouseAdapter ;
    ArrayList<HouseMod> moviesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_cutomer_list);
        listView = (ListView) findViewById(R.id.list_view);

        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);


        moviesList = new ArrayList<>();


        RequestQueue queue = Volley.newRequestQueue(HouseCutomerList.this);
        String url = Const.houses;

        // Request a string response from the provided URL.

        //  moviesList.add(new HouseMod(1, "ali", "", "", null));


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);


                            if (jsonObject.getBoolean("response")){




                                JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));

                                for(int i = 0; i < jsonArray.length() ; i ++){

                                    //JSONObject obj = new JSONObject(jsonArray[i]);



                                    final JSONObject object = jsonArray.getJSONObject(i);

                                    // Log.i("xxxxxxx",object.getString("owner"));
                                    final String imageUrl = object.getString("image");
                                    final int id = object.getInt("id");
                                    JSONObject owner = new JSONObject(object.getString("owner"));
                                    final String ownerName = owner.getString("username");

                                    final String price = object.getString("price");
                                    final String place = object.getString("place");
                                    final String housenumber = object.getString("housenumber");
                                    // moviesList.add(new HouseMod(id, "sss", "", "", re));
                                    final String isPayed = object.getString("isPayed");


                                    RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
                                    ImageRequest imageRequest = new ImageRequest(imageUrl,
                                            new Response.Listener<Bitmap>() {
                                                @Override
                                                public void onResponse(Bitmap response) {

                                                    moviesList.add(new HouseMod(id, ownerName, place, housenumber, response, price,isPayed));
                                                    //  moviesList.notify();
                                                    updateList();
                                                }




                                            }, 0, 0, null, null, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.i("error loading image",error.toString());
                                            Log.i("error loading image",imageUrl);
                                        }
                                    });

                                    mRequestQueue.add(imageRequest);





                                      updateList();
                                }




                            }else{

                                String message = jsonObject.getString("message");
                                Toast.makeText(HouseCutomerList.this,message,Toast.LENGTH_LONG).show();

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
                map.put("userid",sharedPref.getString("userid","0"));
                return map;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);






    }

    private void updateList() {
        customerHouseAdapter = new CustomerHouseAdapter(HouseCutomerList.this,moviesList);
        listView.setAdapter(customerHouseAdapter);
    }
}
