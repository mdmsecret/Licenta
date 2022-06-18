package com.example.licenta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {


    private static final String DB_NAME = "users2";


    private static final int DB_VERSION = 1;


    private static final String TABLE_NAME = "useri";


    private static final String ID_COL = "id";


    private static final String NUME_COL = "nume";


    private static final String PRENUME_COL = "prenume";


    private static final String USERNAME_COL = "username";


    private static final String PASSWORD_COL = "password";

    private static final String EMAIL_COL = "email";

    private static final String STATUS_COL = "status";


    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PRENUME_COL + " TEXT,"
                + NUME_COL + " TEXT,"
                + USERNAME_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + STATUS_COL + " TEXT,"
                + PASSWORD_COL + " TEXT)";


        db.execSQL(query);
    }

    public void addNewUser(String nume, String prenume, String username, String parola, String email, int status) {

       SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NUME_COL, nume);
        values.put(PRENUME_COL, prenume);
        values.put(USERNAME_COL, username);
        values.put(PASSWORD_COL, parola);
        values.put(STATUS_COL, status);
        values.put(EMAIL_COL, email);
        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public ArrayList<Users> readUsers() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorUsers = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<Users> usersArrayList = new ArrayList<>();

        if (cursorUsers.moveToFirst()) {
            do {
                usersArrayList.add(new Users(cursorUsers.getString(3),
                        cursorUsers.getString(6),
                        cursorUsers.getInt(0),
                        cursorUsers.getInt(5),
                        cursorUsers.getString(1),
                        cursorUsers.getString(2),
                        cursorUsers.getString(4)));
            } while (cursorUsers.moveToNext());

        }
        cursorUsers.close();
        return usersArrayList;
    }

    public void updateUser(String nume, String prenume, String username, String parola, String email, int status) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NUME_COL, nume);
        values.put(PRENUME_COL, prenume);
        values.put(USERNAME_COL, username);
        values.put(PASSWORD_COL, parola);
        values.put(EMAIL_COL, email);
        values.put(STATUS_COL, status);

        db.update(TABLE_NAME, values, "username=?", new String[]{username});
        db.close();
    }

    public void deleteUser(String useName) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "username=?", new String[]{useName});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}