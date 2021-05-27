package com.example.complaint_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database_name";
    private static final String TABLE_NAME = "Registered_Users";
    private static final String TABLE_NAME1 = "All_Complaints";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Fullname TEXT, Username TEXT, Email TEXT, Phone INTEGER, Password TEXT)";
        sqLiteDatabase.execSQL(createTable);

        String createTable1 = "CREATE TABLE " + TABLE_NAME1 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Fullname TEXT, Nature TEXT, Details TEXT, Date String, Outcome TEXT)";
        sqLiteDatabase.execSQL(createTable1);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(sqLiteDatabase);
    }

    public boolean addText(String fullname, String username, String email, String phone, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Fullname", fullname);
        contentValues.put("Username", username);
        contentValues.put("Email", email);
        contentValues.put("Phone", phone);
        contentValues.put("Password", password);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean checkemail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Registered_Users WHERE Email=?", new String[]{email});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Registered_Users WHERE Username=?", new String[]{username});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    public Boolean loginAuth(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Registered_Users WHERE Username=? AND Password=?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return result;

    }

    public boolean addComplaint(String fullname, String nature, String details, String date, String outcome) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Fullname", fullname);
        contentValues.put("Nature", nature);
        contentValues.put("Details", details);
        contentValues.put("Date", date);
        contentValues.put("Outcome", outcome);
        long result = sqLiteDatabase.insert(TABLE_NAME1, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

}