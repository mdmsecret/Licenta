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


        // at last we are calling a exec sql 
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewUser(String nume, String prenume, String username, String parola,String email, int status) {

        // on below line we are creating a variable for 
        // our sqlite database and calling writable method 
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a 
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values 
        // along with its key and value pair.
        values.put(NUME_COL, nume);
        values.put(PRENUME_COL, prenume);
        values.put(USERNAME_COL, username);
        values.put(PASSWORD_COL, parola);
        values.put(STATUS_COL,status);
        values.put(EMAIL_COL,email);
        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }
    public ArrayList<Users> readUsers() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorUsers = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<Users> usersArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorUsers.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                usersArrayList.add(new Users(cursorUsers.getString(3),
                        cursorUsers.getString(6),
                        cursorUsers.getInt(0),
                        cursorUsers.getInt(5),
                        cursorUsers.getString(1),
                        cursorUsers.getString(2),
                        cursorUsers.getString(4)));
            } while (cursorUsers.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorUsers.close();
        return usersArrayList;
    }
    public void updateUser(String nume, String prenume, String username, String parola,String email, int status) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NUME_COL, nume);
        values.put(PRENUME_COL, prenume);
        values.put(USERNAME_COL, username);
        values.put(PASSWORD_COL, parola);
        values.put(EMAIL_COL, email);
        values.put(STATUS_COL,status);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "username=?", new String[]{username});
        db.close();
    }
    public void deleteUser(String useName) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "username=?", new String[]{useName});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}