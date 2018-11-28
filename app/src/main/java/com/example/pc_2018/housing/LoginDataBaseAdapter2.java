package com.example.pc_2018.housing;

// Owner

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter2 {


    static final String DATABASE_NAME = "OwnerLoginDB.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+"LOGINOwnerTB"+
            "( " +"ID"+" integer primary key autoincrement,"+ "UserName  text,password text,email text,phone text,FirstName text,lastName text); ";
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private com.example.pc_2018.housing.DataBaseHelper2 dbHelper;
    public  LoginDataBaseAdapter2(Context _context)
    {
        context = _context;
        dbHelper = new com.example.pc_2018.housing.DataBaseHelper2(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  LoginDataBaseAdapter2 open() throws SQLException
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
        db.insert("LOGINOwnerTB", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public String getSinlgeEntry(String username)
    {
        Cursor cursor=db.query("LOGINOwnerTB", null, " UserName=?", new String[]{username}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("password"));
        cursor.close();
        return password;
    }

}

