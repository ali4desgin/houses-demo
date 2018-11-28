package com.example.pc_2018.housing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendMessage extends AppCompatActivity {

    EditText CustomerName;
    EditText PhoneNumber;
    EditText Location;

    EditText Email;
    EditText PlaceNumberRequest;
    EditText Message;

    Button Send;

    MessageDataBase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        mydb=new MessageDataBase(this);


        CustomerName = (EditText) findViewById(R.id.editText19);
        PhoneNumber = (EditText) findViewById(R.id.editText20);
        Location = (EditText) findViewById(R.id.editText21);

        Email = (EditText) findViewById(R.id.editText22);
        PlaceNumberRequest = (EditText) findViewById(R.id.editText23);
        Message = (EditText) findViewById(R.id.MessageID);




        Send= (Button) findViewById(R.id.button19);

        addData();
    }

    public void addData()
    {

        Send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean isInserted = mydb.insertContact(CustomerName.getText().toString(), PhoneNumber.getText().toString(),

                        Location.getText().toString(),Email.getText().toString(), PlaceNumberRequest.getText().toString(),

                        Message.getText().toString());

                if (isInserted = true) {

                    Toast.makeText(SendMessage.this, "Done", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(SendMessage.this,
                            CustomerPage.class));

                }

                else
                    Toast.makeText(SendMessage.this, "Could Not Add", Toast.LENGTH_LONG).show();
                startActivity(new Intent(SendMessage.this,
                        CustomerPage.class));

            }
        });

    }
}
