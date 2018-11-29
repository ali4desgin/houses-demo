package com.example.pc_2018.housing;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OwnerPage extends AppCompatActivity {

    HousingDataBase mydb;
    MessageDataBase mydb2;
    Button showMessage;
    Button showHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_page);

        //Button MessageInbox = (Button) findViewById(com.example.pc_2018.housing.R.id.button8);
        Button AddHouse = (Button) findViewById(com.example.pc_2018.housing.R.id.button9);

//        Button updateHouse = (Button) findViewById(com.example.pc_2018.housing.R.id.button10);
//        Button deleteHouse = (Button) findViewById(com.example.pc_2018.housing.R.id.button11);

        showHouse = (Button) findViewById(com.example.pc_2018.housing.R.id.button16);

        showMessage = (Button) findViewById(com.example.pc_2018.housing.R.id.button8);

        mydb=new HousingDataBase(this);
        mydb2=new MessageDataBase(this);



//        deleteHouse.setVisibility(View.INVISIBLE);
//        updateHouse.setVisibility(View.INVISIBLE);

        viewall();
        ViewMessage();





        AddHouse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(OwnerPage.this,
                        AddHouse.class));

            }
        });

//        updateHouse.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                startActivity(new Intent(OwnerPage.this,
//                        UpdateHouse.class));
//
//            }
//        });
//
//        deleteHouse.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                startActivity(new Intent(OwnerPage.this,
//                        DeleteHouse.class));
//
//            }
//        });
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

//
//                        Cursor res = mydb2.getData();
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
//                            buffer.append("CustomerName: " + res.getString(1) + "\n");
//                            buffer.append("PhoneNumber: " + res.getString(2) + "\n");
//                            buffer.append("Location: " + res.getString(3) + "\n");
//                            buffer.append("Email :" + res.getString(4) + "\n");
//                            buffer.append("PlaceNumberRequest: " + res.getString(5) + "\n");
//                            buffer.append("Message: " + res.getString(6) + "\n\n");
//
//                        }
//
//                        // show all data
//
//                        ShowMessage2("Message INBOX ", buffer.toString());

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
