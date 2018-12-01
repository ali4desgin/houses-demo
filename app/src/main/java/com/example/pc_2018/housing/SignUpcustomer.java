package com.example.pc_2018.housing;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.regex.Pattern;

public class SignUpcustomer extends AppCompatActivity {

    EditText editTextUserName,editTextPassword,editTextConfirmPassword,editTextemail,editTextphone,editTextFirstName,editTextlastName;
    Button btnCreateAccount;
     SharedPreferences sharedPref;

     String userName ;
    String password ;
    String confirmPassword ;

     String email ;
     String phone;
     String FirstName;
    String lastName ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upcustomer);
        sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        // Get Refferences of Views
        editTextUserName=(EditText)findViewById(com.example.pc_2018.housing.R.id.editTextUserName2);
        editTextPassword=(EditText)findViewById(com.example.pc_2018.housing.R.id.editTextPassword2);
        editTextConfirmPassword=(EditText)findViewById(com.example.pc_2018.housing.R.id.editTextConfirmPassword2);


        editTextemail=(EditText)findViewById(com.example.pc_2018.housing.R.id.eeditText52);
        editTextphone=(EditText)findViewById(com.example.pc_2018.housing.R.id.editText72);
        editTextFirstName=(EditText)findViewById(com.example.pc_2018.housing.R.id.editText82);
        editTextlastName=(EditText)findViewById(com.example.pc_2018.housing.R.id.editText);


        btnCreateAccount=(Button)findViewById(com.example.pc_2018.housing.R.id.buttonCreateAccount2);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub




                  userName = editTextUserName.getText().toString();
                 password = editTextPassword.getText().toString();
                 confirmPassword = editTextConfirmPassword.getText().toString();

                  email = editTextemail.getText().toString();
                  phone = editTextphone.getText().toString();
                  FirstName = editTextFirstName.getText().toString();
                  lastName = editTextlastName.getText().toString();



                // check if any of the fields are vaccant
                if (userName.equals("") || password.equals("") || confirmPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }
                // check if both password matches
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    // Save the Data in Database
                    //************* email @ condtions
                    if(isValidEmaillId(email.trim())){
                       // Toast.makeText(getApplicationContext(), "Valid Email Address.", Toast.LENGTH_SHORT).show();

                        //userName, password,email,phone,FirstName,lastName







                        createUser();



//
//                        RequestQueue queue = Volley.newRequestQueue(SignUpcustomer.this);
//                        String url = Const.signup;
//
//                        // Request a string response from the provided URL.
//                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                                new Response.Listener<String>() {
//                                    @Override
//                                    public void onResponse(String response) {
//
//
//
//                                        try {
//                                            JSONObject jsonObject = new JSONObject(response);
//                                            if (jsonObject.getBoolean("response")){
//
//                                                sharedPref.edit().putString("userid", String.valueOf(jsonObject.get("userid"))).apply();
//                                                sharedPref.edit().putString("user_active", String.valueOf("yes")).apply();
//                                                sharedPref.edit().putString("user_type", String.valueOf("customer")).apply();
//
//
//                                                Intent intent = new Intent(SignUpcustomer.this,CustomerPage.class);
//                                                startActivity(intent);
//
//                                            }else{
//
//                                                String message = jsonObject.getString("message");
//                                                Toast.makeText(SignUpcustomer.this,message,Toast.LENGTH_LONG).show();
//
//                                            }
//
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }
//                                        //Toast.makeText(SignUpcustomer.this,response,Toast.LENGTH_LONG).show();
//
//
//
//
//
//                                    }
//                                }, new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//
//
//                            }
//                        }){
//                            @Override
//                            protected Map<String, String> getParams() throws AuthFailureError {
//
//                                Map<String , String> map = new HashMap<>();
//                                map.put("email",email);
//                                map.put("username",userName);
//                                map.put("phone",phone);
//                                map.put("placenumber","");
//                                map.put("firstname",FirstName);
//                                map.put("lastname",lastName);
//                                map.put("isOwner", "no");
//                                return map;
//                            }
//                        };
//
//                        // Add the request to the RequestQueue.
//                        queue.add(stringRequest);
//



                    }else{
                        Toast.makeText(getApplicationContext(), "InValid Email Address.", Toast.LENGTH_SHORT).show();
                    }
                    //************



                }
            }
        });





    }



    //************** email validation code****************



    private boolean isValidEmaillId(String email){

        return Pattern.compile("(.+?)"+"@"+"(.+?)"+"."+"com").matcher(email).matches();
    }


    private  void createUser(){


        RequestQueue queue = Volley.newRequestQueue(SignUpcustomer.this);
        String url = Const.signup;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("response")){

                                sharedPref.edit().putString("userid", String.valueOf(jsonObject.getString("userid"))).apply();
                                sharedPref.edit().putString("user_active", String.valueOf("yes")).apply();;
                                sharedPref.edit().putString("user_type", String.valueOf("customer")).apply();;

                                Intent intent = new Intent(SignUpcustomer.this,CustomerPage.class);
                                startActivity(intent);

                            }else{

                                String message = jsonObject.getString("message");
                                Toast.makeText(SignUpcustomer.this,message,Toast.LENGTH_LONG).show();

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
                map.put("email",email);
                map.put("username",userName);
                map.put("password",password);
                map.put("phone",phone);
                map.put("firstname",FirstName);
                map.put("lastname",lastName);
                //  map.put("placenumber",lastName);
                map.put("isOwner", "no");
                return map;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }
    //*************** end email validation code**************
}