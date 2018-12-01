package com.example.pc_2018.housing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.pc_2018.housing.Adapter.VolleyMultiPart;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;



public class AddHouse extends AppCompatActivity {



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
    SharedPreferences sharedPref;

    RelativeLayout Loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);


        Loading = (RelativeLayout)findViewById(R.id.LoadingPro);
        sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);




        Place = (EditText) findViewById(R.id.Place);
        HouseName = (EditText) findViewById(R.id.Housename);
        HouseNumber = (EditText) findViewById(R.id.Housenumber);

        Type = (EditText) findViewById(R.id.Type);
        Price = (EditText) findViewById(R.id.Price);
        State = (EditText) findViewById(R.id.editText11);

        imageView2 = (ImageView) findViewById(R.id.imageView2);
        activity_add_house = (RelativeLayout) findViewById(R.id.activity_add_house);

        add= (Button) findViewById(R.id.Edit);
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
                RequestQueue queue = Volley.newRequestQueue(AddHouse.this);
                String url = Const.addhouse;


                Loading.setVisibility(View.VISIBLE);
                if(bitmap!=null){
                    uploadBitmap(bitmap);
                }else{
                    Loading.setVisibility(View.INVISIBLE);
                    Toast.makeText(AddHouse.this,"Plase add image",Toast.LENGTH_LONG).show();
                }



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



    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }



    private void uploadBitmap(final Bitmap bitmap) {

        //getting the tag from the edittext

        //our custom volley request
        VolleyMultiPart volleyMultipartRequest = new VolleyMultiPart(Request.Method.POST, Const.addhouse,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            Loading.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddHouse.this,OwnerPage.class);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Loading.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String userid = String.valueOf(sharedPref.getString("userid","0")) ;


                String image = getStringImage(bitmap);
                Map<String , String> map = new HashMap<>();
                map.put("house_number", HouseName.getText().toString());
                map.put("place", Place.getText().toString());
                map.put("ownerID",userid);
                map.put("price",Price.getText().toString());
                map.put("type",Type.getText().toString());
                map.put("state", State.getText().toString());
                map.put("image", image);

                return map;
            }

            /*
             * Here we are passing image by renaming it with a unique name
             * */
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("image", new DataPart(imagename + ".jpg", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        //adding the request to volley
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }


    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

}
