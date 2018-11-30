package com.example.pc_2018.housing;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.RequestQueue;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class loginall extends AppCompatActivity {

    LoginDataBaseAdapter loginDataBaseAdapter;
    LoginDataBaseAdapter2 loginDataBaseAdapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginall);



        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);



        // create a instance of SQLite Database ()
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        // create a instance of SQLite Database ()
        loginDataBaseAdapter2=new LoginDataBaseAdapter2(this);
        loginDataBaseAdapter2=loginDataBaseAdapter2.open();

        // This is SharedPrefrences which we will store the userId in it
        final SharedPreferences.Editor pref = getApplicationContext().getSharedPreferences("userLogged", MODE_PRIVATE).edit();

        Button btncustomer = (Button) findViewById(com.example.pc_2018.housing.R.id.button5);
        Button btnowner = (Button) findViewById(com.example.pc_2018.housing.R.id.button4);


        btncustomer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {




                final Dialog dialog = new Dialog(loginall.this);
                dialog.setContentView(R.layout.activity_login_customer);
                dialog.setTitle("Login customer");


                // get the Refferences of views
                final EditText editTextName=(EditText)dialog.findViewById(com.example.pc_2018.housing.R.id.editTextnameToLoginCustomer);
                final  EditText editTextPassword=(EditText)dialog.findViewById(com.example.pc_2018.housing.R.id.editTextPasswordToLoginCustomer);

                final Button btnSignInNeedy=(Button)dialog.findViewById(com.example.pc_2018.housing.R.id.btnbutton3Customer);

                // Set On ClickListener
                btnSignInNeedy.setOnClickListener(new View.OnClickListener() {


                    //**********

                    public void onClick(View v) {

                        // get The User name and Password
                        final String name=editTextName.getText().toString();
                        final String password=editTextPassword.getText().toString();

                        // fetch the Password form database for respective user name
                        com.android.volley.RequestQueue queue = Volley.newRequestQueue(loginall.this);
                        String url = Const.signin;

                        // Request a string response from the provided URL.
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        //
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            if (jsonObject.getBoolean("response")){



//                                                sharedPref.edit().putString("userid", String.valueOf(jsonObject.getString("userid"))).apply();
//                                                sharedPref.edit().putString("user_active", String.valueOf("yes")).apply();
                                                //Toast.makeText(loginall.this,String.valueOf(jsonObject.getString("user")),Toast.LENGTH_LONG).show();
                                                JSONObject user = new JSONObject(jsonObject.getString("user"));
                                                // Toast.makeText(loginall.this,String.valueOf(user.getString("id")),Toast.LENGTH_LONG).show();
                                                sharedPref.edit().putString("userid",user.getString("id")).apply();
                                                sharedPref.edit().putString("user_active", String.valueOf("yes")).apply();
//                                                Log.i("jsonObject",jsonObject.toString());
//                                                Log.i("user",user.toString());
//
                                                if(user.getString("isOwner").equals("yes")){
                                                    sharedPref.edit().putString("user_type", String.valueOf("owner")).apply();
                                                    Intent intent = new Intent(loginall.this,OwnerPage.class);
                                                    startActivity(intent);
                                                }else{

                                                    sharedPref.edit().putString("user_type", String.valueOf("customer")).apply();
                                                    Intent intent = new Intent(loginall.this,CustomerPage.class);
                                                    startActivity(intent);
                                                }
                                                //    Toast.makeText(loginall.this,String.valueOf(jsonObject.getString("message")),Toast.LENGTH_LONG).show();


                                            }else{

                                                String message = jsonObject.getString("message");
                                                Toast.makeText(loginall.this,message,Toast.LENGTH_LONG).show();

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
                                map.put("username",name);
                                map.put("password",password);
                                return map;
                            }
                        };

                        // Add the request to the RequestQueue.
                        queue.add(stringRequest);


                    }
                });





                dialog.show();

            }
        });

        btnowner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {








                final Dialog dialog = new Dialog(loginall.this);
                dialog.setContentView(R.layout.activity_login_owner);
                dialog.setTitle("Login owner");


                // get the Refferences of views
                final EditText editTextName=(EditText)dialog.findViewById(com.example.pc_2018.housing.R.id.editTextnameToLogin);
                final  EditText editTextPassword=(EditText)dialog.findViewById(com.example.pc_2018.housing.R.id.editTextPasswordToLogin);

                final Button btnSignIn=(Button)dialog.findViewById(com.example.pc_2018.housing.R.id.btnbutton3);

                // Set On ClickListener
                btnSignIn.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        // get The User name and Password
                        final String name=editTextName.getText().toString();
                        final String password=editTextPassword.getText().toString();

                        // fetch the Password form database for respective user name
                        com.android.volley.RequestQueue queue = Volley.newRequestQueue(loginall.this);
                        String url = Const.signin;

                        // Request a string response from the provided URL.
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                     //
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            if (jsonObject.getBoolean("response")){



//                                                sharedPref.edit().putString("userid", String.valueOf(jsonObject.getString("userid"))).apply();
//                                                sharedPref.edit().putString("user_active", String.valueOf("yes")).apply();
                                                //Toast.makeText(loginall.this,String.valueOf(jsonObject.getString("user")),Toast.LENGTH_LONG).show();
                                               JSONObject user = new JSONObject(jsonObject.getString("user"));
                                               // Toast.makeText(loginall.this,String.valueOf(user.getString("id")),Toast.LENGTH_LONG).show();
                                                sharedPref.edit().putString("userid",user.getString("id")).apply();
                                                sharedPref.edit().putString("user_active", String.valueOf("yes")).apply();
//                                                Log.i("jsonObject",jsonObject.toString());
//                                                Log.i("user",user.toString());
//
                                                if(user.getString("isOwner").equals("yes")){
                                                    sharedPref.edit().putString("user_type", String.valueOf("owner")).apply();
                                                    Intent intent = new Intent(loginall.this,OwnerPage.class);
                                                    startActivity(intent);
                                                }else{

                                                    sharedPref.edit().putString("user_type", String.valueOf("customer")).apply();
                                                    Intent intent = new Intent(loginall.this,CustomerPage.class);
                                                    startActivity(intent);
                                                }
                                             //    Toast.makeText(loginall.this,String.valueOf(jsonObject.getString("message")),Toast.LENGTH_LONG).show();


                                            }else{

                                                String message = jsonObject.getString("message");
                                                Toast.makeText(loginall.this,message,Toast.LENGTH_LONG).show();

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
                                map.put("username",name);
                                map.put("password",password);
                                return map;
                            }
                        };

                        // Add the request to the RequestQueue.
                        queue.add(stringRequest);



                    }
                });

                dialog.show();

            }
        });


        //***************






    }
}

