package com.example.pc_2018.housing;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.RequestQueue;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginall extends AppCompatActivity {

    LoginDataBaseAdapter loginDataBaseAdapter;
    LoginDataBaseAdapter2 loginDataBaseAdapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginall);



        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);



        // create a instance of SQLite Database ()
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        // create a instance of SQLite Database ()
        loginDataBaseAdapter2=new LoginDataBaseAdapter2(this);
        loginDataBaseAdapter2=loginDataBaseAdapter2.open();

        // This is SharedPrefrences which we will store the userId in it
        final SharedPreferences.Editor pref = getApplicationContext().getSharedPreferences("userLogged", MODE_PRIVATE).edit();

        Button btncustomer = (Button) findViewById(com.example.pc_2018.housing.R.id.button5);
        Button btnowner = (Button) findViewById(com.example.pc_2018.housing.R.id.button4);


        btncustomer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {




                final Dialog dialog = new Dialog(loginall.this);
                dialog.setContentView(R.layout.activity_login_customer);
                dialog.setTitle("Login customer");


                // get the Refferences of views
                final EditText editTextName=(EditText)dialog.findViewById(com.example.pc_2018.housing.R.id.editTextnameToLoginCustomer);
                final  EditText editTextPassword=(EditText)dialog.findViewById(com.example.pc_2018.housing.R.id.editTextPasswordToLoginCustomer);

                final Button btnSignInNeedy=(Button)dialog.findViewById(com.example.pc_2018.housing.R.id.btnbutton3Customer);

                // Set On ClickListener
                btnSignInNeedy.setOnClickListener(new View.OnClickListener() {


                    //**********

                    public void onClick(View v) {

                        final String nameCustomer=editTextName.getText().toString();
                        final String passwordNeedy=editTextPassword.getText().toString();


                    }
                });





                dialog.show();

            }
        });

        btnowner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                final Dialog dialog = new Dialog(loginall.this);
                dialog.setContentView(R.layout.activity_login_owner);
                dialog.setTitle("Login owner");


                // get the Refferences of views
                final EditText editTextName=(EditText)dialog.findViewById(com.example.pc_2018.housing.R.id.editTextnameToLogin);
                final  EditText editTextPassword=(EditText)dialog.findViewById(com.example.pc_2018.housing.R.id.editTextPasswordToLogin);

                final Button btnSignIn=(Button)dialog.findViewById(com.example.pc_2018.housing.R.id.btnbutton3);

                // Set On ClickListener
                btnSignIn.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        // get The User name and Password
                        String name=editTextName.getText().toString();
                        String password=editTextPassword.getText().toString();

                        // fetch the Password form database for respective user name
                        String storedPassword=loginDataBaseAdapter2.getSinlgeEntry(name);

                        // check if the Stored password matches with  Password entered by user
                        if(password.equals(storedPassword))
                        {
                            Toast.makeText(loginall.this, " Login Successfull", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            startActivity(new Intent(loginall.this,
                                    OwnerPage.class));
                        }
                        else
                        {
                            Toast.makeText(loginall.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                dialog.show();

            }
        });


        //***************






    }
}

