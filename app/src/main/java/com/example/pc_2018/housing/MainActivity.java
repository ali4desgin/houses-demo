package com.example.pc_2018.housing;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    HousingDataBase mydb;
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



        mydb = new HousingDataBase(this);
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
//                        Cursor res = mydb.getData();
//                        if (res.getCount() == 0) {
//                            //show message
//                            ShowMessage("Not Available", "No Data Found");
//
//                            return;
//
//                        }
//                        StringBuffer buffer = new StringBuffer();
//                        while (res.moveToNext()) {
//                            buffer.append("ID: " + res.getString(0) + "\n");
//                            buffer.append("Place: " + res.getString(1) + "\n");
//                            buffer.append("OwnerName: " + res.getString(2) + "\n");
//                            buffer.append("HouseNumber: " + res.getString(3) + "\n");
//                            buffer.append("Type :" + res.getString(4) + "\n");
//                            buffer.append("price: " + res.getString(5) + "\n");
//                            buffer.append("State: " + res.getString(6) + "\n\n");
//
//                        }
//
//                        // show all data
//
//                        ShowMessage("Houses Table ", buffer.toString());

                    }


                }
        );   }

//method view

    public void ShowMessage(String title,String message )

    {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.show();
    }

    //**********
}
