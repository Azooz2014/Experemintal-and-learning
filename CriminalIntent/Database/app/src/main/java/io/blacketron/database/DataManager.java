package io.blacketron.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.R.attr.version;

/**
 * Created by Blacketron on 10/20/2017.
 */

public class DataManager {

    //This is the actual database.
    private SQLiteDatabase db;

    /* Next we have a public static final String for each raw/table that we need to refer
    * both inside and outside this class */
    public static final String TABLE_RAW_ID = "_id";
    public static final String TABLE_RAW_NAME = "name";
    public static final String TABLE_RAW_AGE = "age";

    /* Next we have private static final string and int for each raw/table that we need to
    * refer inside this class */
    private static final String DB_NAME = "adress_db_book";
    private static final int DB_VERSION = 1;
    private static final String TABLE_N_AND_A = "names_and_adresses";

    public DataManager(Context context){

        //Create an instance of our internal CustomSQLiteOpenHelper class.
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);

        //Get writable database.
        db = helper.getWritableDatabase();
    }

    //Insert a record.
    public void insert(String name, String age){

        //Add all the details to the table.
        String query = "INSERT INTO " + TABLE_N_AND_A + " (" + TABLE_RAW_NAME + ", " + TABLE_RAW_AGE + ") " + "VALUES (" + "'" + name + "'"
                + ", " + "'" + age + "'" + ");";

        Log.i("insert() = ", query);

        db.execSQL(query);
    }

    //Delete a record.
    public void delete(String name) {

        //Delete the record from the table if it's already exist.
        String query = "DELETE FROM " + TABLE_N_AND_A + " WHERE " + TABLE_RAW_NAME + " = '" + name + "';";

        Log.i("delete() = ", query);

        db.execSQL(query);
    }

    //Get all record.
    public Cursor selectAll(){

        Cursor c = db.rawQuery("SELECT *" + " FROM "+ TABLE_N_AND_A ,null);

        return c;
    }

    //Find a specific record.
    public Cursor searchName(String name){

        String query = "SELECT " + TABLE_RAW_ID + ", " + TABLE_RAW_NAME + ", " + TABLE_RAW_AGE + " FROM "
                + TABLE_N_AND_A + " WHERE " + TABLE_RAW_NAME + " = '" + name + "';";

        Log.i("searchName() = ", query);

        Cursor c = db.rawQuery(query, null);

        return c;
    }

    //This class is created when our DataManager is initialized.
    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {

        public CustomSQLiteOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            //Create a table for photos and all their details.
            String newTableQueryString = "CREATE TABLE " + TABLE_N_AND_A + "(" + TABLE_RAW_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    +  TABLE_RAW_NAME + " TEXT NOT NULL, " + TABLE_RAW_AGE + " INT NOT NULL);";

            db.execSQL(newTableQueryString);
        }

        //This method runs when DB_VERSION is incremented.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
