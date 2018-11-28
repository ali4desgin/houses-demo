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



public class AddHouse extends AppCompatActivity {

    HousingDataBase mydb;


    // EditText id;

    EditText Place;
    EditText HouseName;
    EditText HouseNumber;

    EditText Type;
    EditText Price;
    EditText State;

    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);



        mydb=new HousingDataBase(this);



        Place = (EditText) findViewById(R.id.editText3);
        HouseName = (EditText) findViewById(R.id.editText5);
        HouseNumber = (EditText) findViewById(R.id.editText6);

        Type = (EditText) findViewById(R.id.editText9);
        Price = (EditText) findViewById(R.id.editText10);
        State = (EditText) findViewById(R.id.editText11);




        add= (Button) findViewById(R.id.button12);

        addData();
    }

    public void addData()
    {

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean isInserted = mydb.insertContact(Place.getText().toString(), HouseName.getText().toString(),

                        HouseNumber.getText().toString(),Type.getText().toString(), Price.getText().toString(),

                        State.getText().toString());

                if (isInserted = true) {

                    Toast.makeText(AddHouse.this, "Done", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(AddHouse.this,
                            OwnerPage.class));

                }

                else
                    Toast.makeText(AddHouse.this, "Could Not Add", Toast.LENGTH_LONG).show();
                startActivity(new Intent(AddHouse.this,
                        OwnerPage.class));

            }
        });

    }
}
