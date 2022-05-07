package com.example.licenta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DBEventHandler extends SQLiteOpenHelper {


    private static final String DB_NAME = "events";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "events";

    private static final String ID_COL = "id";

    private static final String TITLE_COL = "title";

    private static final  String OWNERS_COL= "owners";

    private static final  String DATE_COL= "date";

    private static final  String TIME_COL= "time";

    private static final  String STATUS_COL= "status";

    private static final  String LOCATION_COL= "location";

    private static final  String PARTICIPANTS_COL= "participants";

    private static final  String TOKEN_COL= "token";

    private static final  String DESCRIPTION_COL= "description";

    public DBEventHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE_COL + " TEXT,"
                + OWNERS_COL + " TEXT,"
                + DATE_COL + " TEXT,"
                + TIME_COL + " TEXT,"
                + STATUS_COL + " TEXT,"
                + LOCATION_COL + " TEXT,"
                + TOKEN_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + PARTICIPANTS_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewEvent(String title, String description, String date, String hour,String owner,Integer maxPers,String place,ArrayList<String> participantsArray) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();
        //Instert:
        //ArrayList<String> participantsArray=new ArrayList<String>();
        //...Add value to inputArray
        Gson gson = new Gson();

        String participants= gson.toJson(participantsArray);


        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();
        String token=new String(owner+title);
        values.put(TITLE_COL, title);
        values.put(OWNERS_COL, owner);
        values.put(DATE_COL,date);
        values.put(TIME_COL,hour);
        values.put(STATUS_COL,maxPers);
        values.put(LOCATION_COL,place);
        values.put(DESCRIPTION_COL,description);
        values.put(PARTICIPANTS_COL,participants);
        values.put(TOKEN_COL,token);


        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }
    public ArrayList<Event> readEvent() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorEvent = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<Event> eventArrayList = new ArrayList<>();
        ArrayList<String> participantsArray=new ArrayList<String>();

        //convert string to arrayList
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();

        //

        //

        // moving our cursor to first position.
        if (cursorEvent.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                participantsArray=gson.fromJson(cursorEvent.getString(9), type);
              eventArrayList.add(new Event(cursorEvent.getString(1),
                      cursorEvent.getString(2),
                      cursorEvent.getString(3),
                      cursorEvent.getString(4),
                      cursorEvent.getString(6),
                      cursorEvent.getString(8),
                      cursorEvent.getString(7),
                      participantsArray,
                cursorEvent.getInt(5)));
            } while (cursorEvent.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorEvent.close();
        return eventArrayList;
    }
    public void deleteEvent(String owner) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "username=?", new String[]{owner});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}