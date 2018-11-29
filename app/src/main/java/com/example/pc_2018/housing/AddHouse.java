package com.example.pc_2018.housing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;


public class AddHouse extends AppCompatActivity {

    HousingDataBase mydb;


    // EditText id;

    EditText Place;
    EditText HouseName;
    EditText HouseNumber;
    private Bitmap bitmap;
    EditText Type;
    EditText Price;
    EditText State;
    ImageView imageView2;

    RelativeLayout activity_add_house;
    Button add,uploadBtn;

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

        imageView2 = (ImageView) findViewById(R.id.imageView2);
        activity_add_house = (RelativeLayout) findViewById(R.id.activity_add_house);

        add= (Button) findViewById(R.id.button12);
        uploadBtn= (Button) findViewById(R.id.uploadBtn);

        addData();



        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        InputStream stream = null;
        if (requestCode == 0 && resultCode == Activity.RESULT_OK)
            try {
                // recyle unused bitmaps
                if (bitmap != null) {
                    bitmap.recycle();
                }
                stream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(stream);
               // activity_add_house.setBackground();
                imageView2.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    }
}
