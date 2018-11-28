package com.example.pc_2018.housing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpAll extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_all);

        Button btncustomer = (Button) findViewById(com.example.pc_2018.housing.R.id.button7);
        Button btnowner = (Button) findViewById(com.example.pc_2018.housing.R.id.button6);


        btncustomer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SignUpAll.this,
                        SignUpcustomer.class));

            }
        });

        btnowner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(SignUpAll.this,
                        SignUpowner.class));

            }
        });
    }
}
