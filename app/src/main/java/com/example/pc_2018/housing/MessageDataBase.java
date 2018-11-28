package com.example.pc_2018.housing;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import android.content.ContentValues;
import android.database.Cursor;

public class MessageDataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MessageDB.db";
    public static final String CONTACTS_TABLE_NAME = "MessageTB";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_CustomerName= "CustomerName";
    public static final String CONTACTS_COLUMN_PhoneNumber= "PhoneNumber";
    public static final String CONTACTS_COLUMN_Location= "Location";

    public static final String CONTACTS_Email = "Email";

    public static final String CONTACTS_PlaceNumberRequest = "PlaceNumberRequest";
    public static final String CONTACTS_Message = "Message";



    public MessageDataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);

        //SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table MessageTB " +
                        "(id integer primary key autoincrement" +
                        ", CustomerName text,PhoneNumber text,Location text,Email text,PlaceNumberRequest text,Message text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS MessageTB");
        onCreate(db);
    }

    public boolean insertContact(String CustomerName, String PhoneNumber, String Location,String Email, String PlaceNumberRequest, String Message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CustomerName", CustomerName);
        contentValues.put("PhoneNumber", PhoneNumber);
        contentValues.put("Location", Location);

        contentValues.put("Email", Email);
        contentValues.put("PlaceNumberRequest", PlaceNumberRequest);
        contentValues.put("Message", Message);

        long result = db.insert("MessageTB", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from MessageTB", null);
        return res;
    }

    public boolean updatedata(String id,String CustomerName, String PhoneNumber, String Location,String Email, String PlaceNumberRequest, String Message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
         contentValues.put("CustomerName", CustomerName);
        contentValues.put("PhoneNumber", PhoneNumber);
        contentValues.put("Location", Location);

        contentValues.put("Email", Email);
        contentValues.put("PlaceNumberRequest", PlaceNumberRequest);
        contentValues.put("Message", Message);

        db.update("MessageTB", contentValues, "id = ? ", new String[]{(id)});
        return true;

    }

    public Integer deletedata(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("MessageTB", "id = ? ", new String[]{(id)});

    }
}
