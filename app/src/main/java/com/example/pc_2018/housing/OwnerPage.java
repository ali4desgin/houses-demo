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

public class OwnerPage extends AppCompatActivity {

    Button showMessage;
    Button showHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_page);
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        //Button MessageInbox = (Button) findViewById(com.example.pc_2018.housing.R.id.button8);
        Button AddHouse = (Button) findViewById(com.example.pc_2018.housing.R.id.button9);

        Button Logout = (Button) findViewById(R.id.Logout);
//        Button updateHouse = (Button) findViewById(com.example.pc_2018.housing.R.id.button10);
//        Button deleteHouse = (Button) findViewById(com.example.pc_2018.housing.R.id.button11);

        showHouse = (Button) findViewById(com.example.pc_2018.housing.R.id.button16);

        showMessage = (Button) findViewById(com.example.pc_2018.housing.R.id.button8);




//        deleteHouse.setVisibility(View.INVISIBLE);
//        updateHouse.setVisibility(View.INVISIBLE);

        viewall();
        ViewMessage();




        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sharedPref.edit().clear().apply();
                Intent intent = new Intent(OwnerPage.this, MainActivity.class);
                startActivity(intent);

            }
        });


        AddHouse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(OwnerPage.this,
                        AddHouse.class));

            }
        });


    }


    public void viewall()
    {

        showHouse.setOnClickListener(

                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent(OwnerPage.this,HouseList.class);
                        startActivity(intent);
                    }
//
//
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
//                            buffer.append("HouseName: " + res.getString(2) + "\n");
//                            buffer.append("HouseNumber: " + res.getString(3) + "\n");
//                            buffer.append("Type :" + res.getString(4) + "\n");
//                            buffer.append("price: " + res.getString(5) + "\n");
//                            buffer.append("state: " + res.getString(6) + "\n\n");
//
//                        }
//
//                        // show all data
//
//                        ShowMessage("Houses Table ", buffer.toString());
//
//                    }
//

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


    public void ViewMessage()
    {

        showMessage.setOnClickListener(

                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(OwnerPage.this,Messages.class);
                        startActivity(intent);


                    }


                }
        );   }


    //***********

    //method view

    public void ShowMessage2(String title,String message )

    {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.show();
    }

    //**********
}
