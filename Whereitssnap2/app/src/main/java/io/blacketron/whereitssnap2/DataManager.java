package io.blacketron.whereitssnap2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.R.attr.name;
import static android.R.attr.version;

/**
 * Created by Blacketron on 10/25/2017.
 */

public class DataManager {

    /*This is the actual database*/
    private SQLiteDatabase db;

    /*Public static final string for each row/table that we need to refer both inside and outside the class.*/
    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_TITLE = "image_title";
    public static final String TABLE_ROW_URI = "image_uri";

    /*private static final strings for each row/table that we need to refer to just inside the class*/
    private static final String DB_NAME ="wis_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_PHOTOS = "wis_table_photos";
    private static final String TABLE_TAGS = "wis_table_tags";
    private static final String TABLE_ROW_TAG1 = "tag1";
    private static final String TABLE_ROW_TAG2 = "tag2";
    private static final String TABLE_ROW_TAG3 = "tag3";
    //for the tags table.
    public static final String TABLE_ROW_TAG = "tag";

    public DataManager(Context context){

        /*create an instence of our internal CustomSQLiteOpenHelper class*/
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);

        //get a writable database.
        db = helper.getWritableDatabase();
    }

    //Here all our helper methods.
    public void addPhotos (Photo photo){

        // Add all the details to the photos table.
        String query = "INSERT INTO " + TABLE_PHOTOS + "(" + TABLE_ROW_TITLE + ", " + TABLE_ROW_URI +
                ", " + TABLE_ROW_TAG1 + ", " + TABLE_ROW_TAG2 + ", " + TABLE_ROW_TAG3 + ")" + "VALUES (" +
                "'" + photo.getTitle() + "'" + ", " + "'" + photo.getStorageLocation() + "'" + ", " + "'" +
                photo.getTag1() + "'" + ", " + "'" + photo.getTag2() + "'" + ", " + "'" + photo.getTag3() + "'" + ");";

        Log.i("addPhoto()", query);
        db.execSQL(query);

        //Add any NEW tag to the tags table.

        query = "INSERT INTO " + TABLE_TAGS + "(" + TABLE_ROW_TAG + ") " + "SELECT '" + photo.getTag1() + "' "
                + "WHERE NOT EXISTS ( SELECT 1 FROM " + TABLE_TAGS + " WHERE " + TABLE_ROW_TAG +  " = "
                + "'" + photo.getTag1() + "');";
        db.execSQL(query);

        query = "INSERT INTO " + TABLE_TAGS + "(" + TABLE_ROW_TAG + ") " + "SELECT '" + photo.getTag2() + "' "
                + "WHERE NOT EXISTS ( SELECT 1 FROM " + TABLE_TAGS + " WHERE " + TABLE_ROW_TAG +  " = "
                + "'" + photo.getTag2() + "');";
        db.execSQL(query);

        query = "INSERT INTO " + TABLE_TAGS + "(" + TABLE_ROW_TAG + ") " + "SELECT '" + photo.getTag3() + "' "
                + "WHERE NOT EXISTS ( SELECT 1 FROM " + TABLE_TAGS + " WHERE " + TABLE_ROW_TAG +  " = "
                + "'" + photo.getTag3() + "');";
        db.execSQL(query);
    }//End addPhoto.

    public Cursor getTitles(){

        Cursor c = db.rawQuery("SELECT " + TABLE_ROW_ID + ", " + TABLE_ROW_TITLE + " FROM " + TABLE_PHOTOS
        , null);

        c.moveToFirst();

        return c;
    }

    public Cursor getTitlesWithTags(String tag){

        Cursor c = db.rawQuery("SELECT " + TABLE_ROW_ID + ", " + TABLE_ROW_TITLE + " FROM " + TABLE_PHOTOS
        + " WHERE " + TABLE_ROW_TAG1 + " = '" + tag + "' OR " + TABLE_ROW_TAG2 + " = '" + tag
        + "' OR " + TABLE_ROW_TAG3 + " = '" + tag + "';", null);

        c.moveToFirst();

        return c;
    }

    public Cursor getPhoto(int id){

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_PHOTOS + " WHERE " + TABLE_ROW_ID + " = "
        + id, null);

        c.moveToFirst();

        return c;
    }

    public Cursor getTags(){

        Cursor c = db.rawQuery("SELECT " + TABLE_ROW_ID + ", " + TABLE_ROW_TAG + " FROM "
        + TABLE_TAGS, null);

        c.moveToFirst();

        return c;
    }

    //This class is created when our DataManager is initialized.
    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {

        public CustomSQLiteOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        //This method only runs when the first time the database is created.
        @Override
        public void onCreate(SQLiteDatabase db) {

            //Create a table for photos and all their details.
            String newTableQueryString = "CREATE TABLE " + TABLE_PHOTOS + " (" +  TABLE_ROW_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + TABLE_ROW_TITLE + " TEXT NOT NULL," + TABLE_ROW_URI + " TEXT NOT NULL,"
                    + TABLE_ROW_TAG1 + " TEXT NOT NULL, " + TABLE_ROW_TAG2 + " TEXT NOT NULL,"
                    + TABLE_ROW_TAG3 + " TEXT NOT NULL" + ");";

            db.execSQL(newTableQueryString);

            //Create a separate table for the tags.

            newTableQueryString = "CREATE TABLE " + TABLE_TAGS + " (" + TABLE_ROW_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + TABLE_ROW_TAG + " TEXT NOT NULL" + ");";

            db.execSQL(newTableQueryString);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
