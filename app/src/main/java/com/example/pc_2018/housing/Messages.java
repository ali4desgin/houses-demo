package com.example.pc_2018.housing;

import android.content.Context;
import android.content.Intent;
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
import com.example.pc_2018.housing.Adapter.MessageAdapter;
import com.example.pc_2018.housing.Adapter.OwnerHouseListAdapter;
import com.example.pc_2018.housing.Models.HouseMod;
import com.example.pc_2018.housing.Models.MessageMod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Messages extends AppCompatActivity {


    ListView listView;
    private MessageAdapter messageAdapter ;
    ArrayList<MessageMod> messagesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        listView = (ListView) findViewById(R.id.list_view);
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        listView = (ListView) findViewById(R.id.list_view);
         messagesList = new ArrayList<>();


        RequestQueue queue = Volley.newRequestQueue(Messages.this);
        String url = Const.messages;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);


                            if (jsonObject.getBoolean("response")){




                                JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));

                                for(int i = 0; i < jsonArray.length() ; i++){

                                    final JSONObject object = jsonArray.getJSONObject(i);

                                    JSONObject jsonObject1 = new JSONObject(object.getString("house"));

                                    messagesList.add(new MessageMod(object.getInt("id"),
                                            jsonObject.getString("message") , jsonObject1.getString("place"), jsonObject1.getString("housenumber"),jsonObject1.getString("isPayed"), jsonObject1.getString("id")));


                                    update();




                                }




                            }else{

                                String message = jsonObject.getString("message");
                                Toast.makeText(Messages.this,message,Toast.LENGTH_LONG).show();

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
                map.put("ownerID",sharedPref.getString("userid","0"));
                return map;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

//        messageAdapter = new MessageAdapter(Messages.this, messagesList);
//
//        listView.setAdapter(messageAdapter);


    }




    private  void update(){

        messageAdapter = new MessageAdapter(Messages.this, messagesList);

        listView.setAdapter(messageAdapter);

    }
}
