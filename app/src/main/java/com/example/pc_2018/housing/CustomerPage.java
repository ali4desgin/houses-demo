package com.example.pc_2018.housing;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class CustomerPage extends AppCompatActivity {

    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);


        Button btnSendMessage = (Button) findViewById(R.id.button13);
        Button btnMyAccount = (Button) findViewById(R.id.button14);
        Button  btnHome = (Button) findViewById(R.id.button15);

        //button22
         btnShow = (Button) findViewById(R.id.button22);


        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CustomerPage.this,
                        SendMessage.class));

            }
        });

        btnMyAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(CustomerPage.this,
                        MyAccount.class));

            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(CustomerPage.this,
                        CustomerPage.class));

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

                        Intent intent = new Intent(CustomerPage.this, HouseCutomerList.class);
                        startActivity(intent);

                    }


                }
        );   }

//method view

    //**********
}
