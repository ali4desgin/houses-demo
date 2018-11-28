package com.example.pc_2018.housing;

//register customer account

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import android.content.ContentValues;
import android.database.Cursor;

import android.database.SQLException;

import static android.content.Context.MODE_PRIVATE;


public class LoginDataBaseAdapter {



    static final String DATABASE_NAME = "customerloginDB.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+"LOGINCustomerTB"+
            "( " +"ID"+" integer primary key autoincrement,"+ "UserName  text,password text,email text,phone text,FirstName text,lastName text); ";
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private com.example.pc_2018.housing.DataBaseHelper dbHelper;
    public  LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new com.example.pc_2018.housing.DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String UserName,String password,String email,String phone,String FirstName,String lastName)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("UserName", UserName);
        newValues.put("password",password);

        newValues.put("email",email);
        newValues.put("phone",phone);
        newValues.put("FirstName",FirstName);
        newValues.put("lastName",lastName);


        // Insert the row into your table
        db.insert("LOGINCustomerTB", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }


    //********************************************

    public boolean updatedata(int ID,String UserName, String password, String email,String phone, String FirstName, String lastName) {

      //  SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserName", UserName);
        contentValues.put("password", password);
        contentValues.put("email", email);

        contentValues.put("phone", phone);
        contentValues.put("FirstName", FirstName);
        contentValues.put("lastName", lastName);

        db.update("LOGINCustomerTB", contentValues, "ID = " + ID + " ", null);
        return true;

    }

    public Integer deletedata(int id) {

        //SQLiteDatabase db = this.getWritableDatabase();

        return db.delete("LOGINCustomerTB", "ID = " + id + " ", null);

    }



    //*******************************************

    int id; // This is the id which we want to retrieve
    public String getSinlgeEntryNeedy(String UserName)
    {
        Cursor cursor=db.query("LOGINCustomerTB", null, " UserName=?", new String[]{UserName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("password"));
        id = cursor.getInt(cursor.getColumnIndex("ID"));
        cursor.close();
        return password;
    }
    // Return the UserId which we took from the method above
    public int getId()
    {
        return id;
    }




}



