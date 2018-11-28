package com.example.pc_2018.housing;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import android.content.ContentValues;
import android.database.Cursor;

public class HousingDataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HousinggDB.db";
    public static final String CONTACTS_TABLE_NAME = "HousingTTB";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_Place= "Place";
    public static final String CONTACTS_COLUMN_HouseName= "HouseName";
    public static final String CONTACTS_COLUMN_HouseNumber= "HouseNumber";

    public static final String CONTACTS_Type = "Type";

    public static final String CONTACTS_Price = "Price";
    public static final String CONTACTS_State = "State";



    public HousingDataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);

        //SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table HousingTTB " +
                        "(id integer primary key autoincrement, Place text,HouseName text,HouseNumber text,Type text,Price text, State text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS HousingTTB");
        onCreate(db);
    }

    public boolean insertContact(String Place, String HouseName, String HouseNumber,String Type, String Price, String State) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Place", Place);
        contentValues.put("HouseName", HouseName);
        contentValues.put("HouseNumber", HouseNumber);

        contentValues.put("Type", Type);
        contentValues.put("Price", Price);
        contentValues.put("State", State);

        long result = db.insert("HousingTTB", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from HousingTTB", null);
        return res;
    }

    public boolean updatedata(String id,String Place, String HouseName, String HouseNumber,String Type, String Price, String State) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Place", Place);
        contentValues.put("HouseName", HouseName);
        contentValues.put("HouseNumber", HouseNumber);

        contentValues.put("Type", Type);
        contentValues.put("Price", Price);
        contentValues.put("State", State);

        db.update("HousingTTB", contentValues, "id = ? ", new String[]{(id)});
        return true;

    }

    public Integer deletedata(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("HousingTTB", "id = ? ", new String[]{(id)});

    }
}
