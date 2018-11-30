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


public class SignUpowner extends AppCompatActivity {

    EditText editTextUserName,editTextPassword,editTextConfirmPassword,editTextemail,editTextphone,editTextFirstName,editTextlastName;
    Button btnCreateAccount;
    LoginDataBaseAdapter2 loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upowner);

        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

      //  AndroidNetworking.initialize(getApplicationContext());

        // get Instance  of Database Adapter
        loginDataBaseAdapter=new LoginDataBaseAdapter2(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        // Get Refferences of Views
        editTextUserName=(EditText)findViewById(com.example.pc_2018.housing.R.id.editTextUserName);
        editTextPassword=(EditText)findViewById(com.example.pc_2018.housing.R.id.editTextPassword);
        editTextConfirmPassword=(EditText)findViewById(com.example.pc_2018.housing.R.id.editTextConfirmPassword);


        editTextemail=(EditText)findViewById(com.example.pc_2018.housing.R.id.eeditText5);
        editTextphone=(EditText)findViewById(com.example.pc_2018.housing.R.id.editText7);
        editTextFirstName=(EditText)findViewById(com.example.pc_2018.housing.R.id.editText8);
        editTextlastName=(EditText)findViewById(com.example.pc_2018.housing.R.id.editText2);


        btnCreateAccount=(Button)findViewById(com.example.pc_2018.housing.R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                final String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();

                final String email = editTextemail.getText().toString();
                final String phone = editTextphone.getText().toString();
                final String FirstName = editTextFirstName.getText().toString();
                final String lastName = editTextlastName.getText().toString();



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
                        Toast.makeText(getApplicationContext(), "Valid Email Address.", Toast.LENGTH_SHORT).show();





                        RequestQueue queue = Volley.newRequestQueue(SignUpowner.this);
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
                                                sharedPref.edit().putString("user_type", String.valueOf("owner")).apply();;


                                              //  Toast.makeText(SignUpowner.this,String.valueOf(jsonObject.getString("userid")),Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(SignUpowner.this,OwnerPage.class);
                                                startActivity(intent);

                                            }else{

                                                String message = jsonObject.getString("message");
                                                Toast.makeText(SignUpowner.this,message,Toast.LENGTH_LONG).show();

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
                                map.put("email",email);
                                map.put("username",userName);
                                map.put("phone",phone);
                                map.put("firstname",FirstName);
                                map.put("lastname",lastName);
                                map.put("isOwner", "yes");
                                return map;
                            }
                        };

                        // Add the request to the RequestQueue.
                        queue.add(stringRequest);


                        loginDataBaseAdapter.insertEntry(userName, password,email,phone,FirstName,lastName);
                        Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SignUpowner.this,OwnerPage.class));

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
        //Pattern.compile("(.+?)"+"@"+"(.+?)"+"."+"(.+?)").matcher(email).matches()
        // only gmail , hotmail , yahoo , outlook , mail
        // only this providers has permissions to create account as owner in the app
        return true;
    }

    //*************** end email validation code**************
}

