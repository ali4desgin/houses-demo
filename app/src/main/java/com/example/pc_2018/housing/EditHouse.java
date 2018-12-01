package com.example.pc_2018.housing;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc_2018.housing.Models.HouseMod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditHouse extends AppCompatActivity {

    EditText  place,number,name,type,price,state;
     String houseID;
    ImageView image;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_house);

        Bundle intent = getIntent().getExtras();
        houseID = intent.getString("houseID");
        place = (EditText) findViewById(R.id.Place);
        price = (EditText) findViewById(R.id.Price);
        type = (EditText) findViewById(R.id.Type);
        name = (EditText) findViewById(R.id.Housename);
        number = (EditText) findViewById(R.id.Housenumber);
        image = (ImageView) findViewById(R.id.Image);
        submit = (Button) findViewById(R.id.Edit);



        loadData();


    }


    private  void  loadData(){

      //  Toast.makeText(EditHouse.this,houseID,Toast.LENGTH_LONG).show();

        RequestQueue queue = Volley.newRequestQueue(EditHouse.this);
        String url = Const.house;

        // Request a string response from the provided URL.

        //  moviesList.add(new HouseMod(1, "ali", "", "", null));


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);


                            if (jsonObject.getBoolean("response")){


                                JSONObject house = new JSONObject(jsonObject.getString("data"));
                                place.setText(house.getString("place"));
                                number.setText(house.getString("housenumber"));
                                type.setText(house.getString("type"));
                                price.setText(house.getString("price"));
                                state.setText(house.getString("status"));

                                final String imageUrl  = house.getString("image");


                                uploadImage(imageUrl);



                            }else{

                                String message = jsonObject.getString("message");
                                Toast.makeText(EditHouse.this,message,Toast.LENGTH_LONG).show();

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
                map.put("id",houseID);
                return map;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

    private  void  uploadImage(String imageUrl){

    }
}
