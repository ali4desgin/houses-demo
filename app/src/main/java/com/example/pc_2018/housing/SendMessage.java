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

public class SendMessage extends AppCompatActivity {

    EditText CustomerName;
    EditText PhoneNumber;
    EditText Location;

    EditText Email;
    EditText PlaceNumberRequest;
    EditText Message;

    Button Send;

    MessageDataBase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        mydb=new MessageDataBase(this);


        CustomerName = (EditText) findViewById(R.id.editText19);
        PhoneNumber = (EditText) findViewById(R.id.editText20);
        Location = (EditText) findViewById(R.id.editText21);

        Email = (EditText) findViewById(R.id.editText22);
        PlaceNumberRequest = (EditText) findViewById(R.id.editText23);
        Message = (EditText) findViewById(R.id.MessageID);




        Intent intent = getIntent();
        final String houseID = intent.getStringExtra("id");
        Send= (Button) findViewById(R.id.button19);

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Toast.makeText(SendMessage.this,"essage",Toast.LENGTH_LONG).show();
                RequestQueue queue = Volley.newRequestQueue(SendMessage.this);
                String url = Const.sendmessage;

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if (jsonObject.getBoolean("response")){



                                        Intent intent = new Intent(SendMessage.this,CustomerPage.class);
                                        startActivity(intent);

                                    }else{

                                        String message = jsonObject.getString("message");
                                        Toast.makeText(SendMessage.this,message,Toast.LENGTH_LONG).show();

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
                        map.put("ownerID",String.valueOf(sharedPref.getString("userid","0")));
                        map.put("customerID",String.valueOf(sharedPref.getString("userid","0")));
                        map.put("houseID",houseID);
                        map.put("message",Message.getText().toString());
                        return map;
                    }
                };

                // Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });


        //addData();
    }

    public void addData()
    {

        Send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean isInserted = mydb.insertContact(CustomerName.getText().toString(), PhoneNumber.getText().toString(),

                        Location.getText().toString(),Email.getText().toString(), PlaceNumberRequest.getText().toString(),

                        Message.getText().toString());

                if (isInserted = true) {

                    Toast.makeText(SendMessage.this, "Done", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(SendMessage.this,
                            CustomerPage.class));

                }

                else
                    Toast.makeText(SendMessage.this, "Could Not Add", Toast.LENGTH_LONG).show();
                startActivity(new Intent(SendMessage.this,
                        CustomerPage.class));

            }
        });

    }
}
