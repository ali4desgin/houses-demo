package com.example.pc_2018.housing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyAccount extends AppCompatActivity {

    LoginDataBaseAdapter mydb;


    EditText ID;

    EditText UserName;
    EditText password;
    EditText email;

    EditText phone;
    EditText FirstName;
    EditText lastName;

    Button update;
    Button delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        mydb=new LoginDataBaseAdapter(this);
        mydb=mydb.open();

        UserName = (EditText) findViewById(R.id.editText26);
        password = (EditText) findViewById(R.id.editText27);
        email = (EditText) findViewById(R.id.editText28);

        phone = (EditText) findViewById(R.id.editText29);
        FirstName = (EditText) findViewById(R.id.editText30);
        lastName = (EditText) findViewById(R.id.editText31);
        ID = (EditText) findViewById(R.id.editText32);

        // This is the sharedPrefrences variable to retrieve the userId
        final SharedPreferences pref = getApplicationContext().getSharedPreferences("userLogged", MODE_PRIVATE);



        update= (Button) findViewById(R.id.button20);
        // Passing the sharedPrefrences to use it in to the method
        UpdateData(pref);

        delete= (Button) findViewById(R.id.button21);
        // Passing the sharedPrefrences to use it in to the method
        deletedata(pref);
    }

    public void UpdateData(final SharedPreferences pref)
    {
        update.setOnClickListener(

                new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {
                        // We retrieve the userId from SharedPrefrences in the first argument
                        boolean updatedata = mydb.updatedata(pref.getInt("userID", -1),UserName.getText().toString(), password.getText().toString(),

                                email.getText().toString(),phone.getText().toString(), FirstName.getText().toString(),

                                lastName.getText().toString());
                        if (updatedata == true) {

                            Toast.makeText(MyAccount.this, "Done", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MyAccount.this,
                                    CustomerPage.class));
                        }

                        else
                            Toast.makeText(MyAccount.this, "Could Not Edit", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MyAccount.this,
                                CustomerPage.class));


                    }

                }

        );

    }


    //delete:

    public void deletedata(final SharedPreferences pref)

    {

        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        // We retrieve the userId from SharedPrefrences in the first argument
                        Integer deleteRows = mydb.deletedata(pref.getInt("userID", -1));
                        if (deleteRows > 0) {

                            Toast.makeText(MyAccount.this, "Topic Deleted  ", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MyAccount.this,
                                    OwnerPage.class));

                        }
                        else
                            Toast.makeText(MyAccount.this, "Could Not Delete", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MyAccount.this,
                                OwnerPage.class));


                    }

                }
        );
    }


}
