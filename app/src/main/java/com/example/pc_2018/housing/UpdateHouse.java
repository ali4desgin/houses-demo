package com.example.pc_2018.housing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

public class UpdateHouse extends AppCompatActivity {

    HousingDataBase mydb;


    EditText ID;

    EditText Place;
    EditText HouseName;
    EditText HouseNumber;

    EditText Type;
    EditText Price;
    EditText State;

    Button update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_house);

        mydb=new HousingDataBase(this);


        Place = (EditText) findViewById(R.id.editText13);
        HouseName = (EditText) findViewById(R.id.editText14);
        HouseNumber = (EditText) findViewById(R.id.editText15);

        Type = (EditText) findViewById(R.id.editText16);
        Price = (EditText) findViewById(R.id.editText17);
        State = (EditText) findViewById(R.id.editText18);
        ID = (EditText) findViewById(R.id.editText25);




        update= (Button) findViewById(R.id.button18);

        UpdateData();
    }

    public void UpdateData()
    {
        update.setOnClickListener(

                new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {
                        boolean isupdate = mydb.updatedata(ID.getText().toString(),Place.getText().toString(), HouseName.getText().toString(),

                                HouseNumber.getText().toString(),Type.getText().toString(), Price.getText().toString(),

                                State.getText().toString());
                        if (isupdate == true) {

                            Toast.makeText(UpdateHouse.this, "Done", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(UpdateHouse.this,
                                    OwnerPage.class));
                        }

                        else
                            Toast.makeText(UpdateHouse.this, "Could Not Edit", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(UpdateHouse.this,
                                OwnerPage.class));


                    }

                }

        );

    }
}
