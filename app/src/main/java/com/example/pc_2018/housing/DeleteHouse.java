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


public class DeleteHouse extends AppCompatActivity {

    HousingDataBase mydb;


    EditText ID;
    Button delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_house);

        mydb=new HousingDataBase(this);


        ID = (EditText) findViewById(R.id.editText12);
        delete= (Button) findViewById(R.id.button17);

        deletedata();
    }

    public void deletedata()

    {

        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        Integer deleteRows = mydb.deletedata(ID.getText().toString());
                        if (deleteRows > 0) {

                            Toast.makeText(DeleteHouse.this, "Topic Deleted  ", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(DeleteHouse.this,
                                    OwnerPage.class));

                        }
                        else
                            Toast.makeText(DeleteHouse.this, "Could Not Delete", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(DeleteHouse.this,
                                OwnerPage.class));


                    }

                }
        );
    }
}
