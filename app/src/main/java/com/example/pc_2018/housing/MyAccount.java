package com.example.pc_2018.housing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyAccount extends AppCompatActivity {



    EditText ID;

    EditText UserName;
    EditText password;
    EditText email;

    EditText phone;
    EditText FirstName;
    EditText lastName;

    Button update;
    Button delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        Button logout  = (Button) findViewById(R.id.logout);

        UserName = (EditText) findViewById(R.id.editText26);
        password = (EditText) findViewById(R.id.editText27);
        email = (EditText) findViewById(R.id.editText28);

        phone = (EditText) findViewById(R.id.editText29);
        FirstName = (EditText) findViewById(R.id.editText30);
        lastName = (EditText) findViewById(R.id.editText31);
        ID = (EditText) findViewById(R.id.editText32);

        // This is the sharedPrefrences variable to retrieve the userId
        final SharedPreferences pref = getApplicationContext().getSharedPreferences("userLogged", MODE_PRIVATE);



        RequestQueue queue = Volley.newRequestQueue(MyAccount.this);
        String url = Const.myaccount;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("response")){

                                JSONObject user = new JSONObject(jsonObject.getString("user"));

                                UserName.setText(user.getString("username"));
                                email.setText(user.getString("email"));
                                phone.setText(user.getString("phone"));
//                                FirstName.setText(user.getString("usrername"));
                                FirstName.setText(user.getString("firstname"));
                                lastName.setText(user.getString("lastname"));

                            }else{

                                String message = jsonObject.getString("message");
                                Toast.makeText(MyAccount.this,message,Toast.LENGTH_LONG).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(SignUpcustomer.this,response,Toast.LENGTH_LONG).show();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String , String> map = new HashMap<>();
                map.put("id",sharedPref.getString("userid","14"));
                return map;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPref.edit().clear().apply();
                Intent intent = new Intent(MyAccount
                        .this, MainActivity.class);
                startActivity(intent);
            }
        });
        //update= (Button) findViewById(R.id.button20);
        // Passing the sharedPrefrences to use it in to the method
      //  UpdateData(pref);

         delete= (Button) findViewById(R.id.button21);

         delete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {


                 RequestQueue queue = Volley.newRequestQueue(MyAccount.this);
                 String url = Const.deleteuser;

                 // Request a string response from the provided URL.
                 StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                         new Response.Listener<String>() {
                             @Override
                             public void onResponse(String response) {


                                 try {
                                     JSONObject jsonObject = new JSONObject(response);
                                     if (jsonObject.getBoolean("response")){


                                         sharedPref.edit().clear().apply();
                                         Intent intent = new Intent(MyAccount.this, MainActivity.class);
                                         startActivity(intent);

                                     }else{

                                         String message = jsonObject.getString("message");
                                         Toast.makeText(MyAccount.this,message,Toast.LENGTH_LONG).show();

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
                         map.put("id",sharedPref.getString("userid","0"));

                         return map;
                     }
                 };
                 queue.add(stringRequest);


             }
         });


    }


}
