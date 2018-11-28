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

    HousingDataBase mydb;
    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);

        mydb = new HousingDataBase(this);

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


                        Cursor res = mydb.getData();
                        if (res.getCount() == 0) {
                            //show message
                            ShowMessage("Not Available", "No Data Found");

                            return;

                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID: " + res.getString(0) + "\n");
                            buffer.append("Place: " + res.getString(1) + "\n");
                            buffer.append("HouseName: " + res.getString(2) + "\n");
                            buffer.append("HouseNumber: " + res.getString(3) + "\n");
                            buffer.append("Type :" + res.getString(4) + "\n");
                            buffer.append("price: " + res.getString(5) + "\n");
                            buffer.append("State: " + res.getString(6) + "\n\n");

                        }

                        // show all data

                        ShowMessage("Houses Table ", buffer.toString());

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
