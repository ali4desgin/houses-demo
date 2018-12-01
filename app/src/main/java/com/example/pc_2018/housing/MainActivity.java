package com.example.pc_2018.housing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {
    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);


        String userid = String.valueOf(sharedPref.getString("userid","0")) ;


        String user_active = String.valueOf(sharedPref.getString("user_active","no")) ;
        String user_type = String.valueOf(sharedPref.getString("user_type","")) ;


        if(!userid.equals("0") && user_active.equals("yes")){

//            Intent intent3 = new Intent(MainActivity.this,OwnerPage.class);
//            startActivity(intent3);
            Intent intent;
            if(user_type.equals("owner")){
                intent = new Intent(MainActivity.this,OwnerPage.class);
                startActivity(intent);
            }else if(user_type.equals("customer")){
                intent = new Intent(MainActivity.this,CustomerPage.class);
                startActivity(intent);
            }

        }



        Button btnloginall = (Button) findViewById(com.example.pc_2018.housing.R.id.button);
        Button btnregisterall = (Button) findViewById(com.example.pc_2018.housing.R.id.button2);
        btnShow = (Button) findViewById(com.example.pc_2018.housing.R.id.button3);


        btnloginall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        loginall.class));

            }
        });

        btnregisterall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,
                        SignUpAll.class));

            }
        });

        viewall();


    }

    //************************
    public void viewall()
    {

        btnShow.setOnClickListener(

                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(MainActivity.this,
                                HouseCutomerList.class);
                        intent.putExtra("visitor","1");
                        startActivity(intent);


                    }


                }
        );   }





}
