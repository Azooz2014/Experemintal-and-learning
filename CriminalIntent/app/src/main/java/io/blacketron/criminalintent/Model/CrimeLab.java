package io.blacketron.criminalintent.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.blacketron.criminalintent.database.CrimeBaseDbHelper;
import io.blacketron.criminalintent.database.CrimeCursorWrapper;
import io.blacketron.criminalintent.database.CrimeDbSchema;

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private CrimeLab(Context context){

        //Generating 100 dummy crime objects.


        /*for(int i = 0; i < 100; i++){

            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.add(crime);
        }*/

        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseDbHelper(context).getWritableDatabase();

    }

    private static ContentValues getContentValues (Crime crime){

        ContentValues values = new ContentValues();

        values.put(CrimeDbSchema.CrimeTable.Columns.UUID, crime.getId().toString());
        values.put(CrimeDbSchema.CrimeTable.Columns.TITLE, crime.getTitle());
        values.put(CrimeDbSchema.CrimeTable.Columns.DATE, crime.getDate().getTime());
        values.put(CrimeDbSchema.CrimeTable.Columns.SOLVED, crime.isSolved() ? 1 : 0);

        return values;
    }

    public void addCrime (Crime c){

        ContentValues values = getContentValues(c);

        mDatabase.insert(CrimeDbSchema.CrimeTable.NAME, null, values);
    }

    public static CrimeLab get(Context context){

        if(sCrimeLab == null){

            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }

    public void removeCrime (Crime c){

        mDatabase.delete(CrimeDbSchema.CrimeTable.NAME,
                CrimeDbSchema.CrimeTable.Columns.UUID + " = ?",
                new String[]{c.getId().toString()});

    }

    //walk the cursor, and populate Crime list.
    public List<Crime> getCrimes(){

        CrimeCursorWrapper cursor = queryCrimes(null, null);

        List<Crime> crimes = new ArrayList<>();

        try {
            //moving the cursor to the first row in DB table.
            cursor.moveToFirst();

            //Iterating and add crime objects while the cursor in not after the last row in DB table.
            while(!cursor.isAfterLast()){
                crimes.add(cursor.getCrime());

                //Moving the cursor to the next row.
                cursor.moveToNext();
            }
        } finally {

           /*closing query after cursor is done from reading it,
            and release the resource that's holding.*/
            cursor.close();
        }
        return crimes;
    }

    public Crime getCrime(UUID id){

        CrimeCursorWrapper cursor = queryCrimes(CrimeDbSchema.CrimeTable.Columns.UUID + " = ?"
        , new String[]{id.toString()});

        try{

            //If there's no rows in DB table return nothing.
            if(cursor.getCount() == 0){
                return null;
            }

            //Move the cursor to the first row.
            cursor.moveToFirst();

            //Returning crime object that matches the corresponding UUID passed in the query.
            return cursor.getCrime();

        }finally {

            /*closing query after cursor is done from reading it,
            and release the resource that's holding.*/
            cursor.close();
        }
    }

    public void updateCrime (Crime crime){

        String uuidString = crime.getId().toString();

        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeDbSchema.CrimeTable.NAME, values,
                CrimeDbSchema.CrimeTable.Columns.UUID + " = ?", new String[]{uuidString});
    }

    //Querying database table.
    private CrimeCursorWrapper queryCrimes (String where, String[] whereArgs){

        Cursor cursor = mDatabase.query(
                CrimeDbSchema.CrimeTable.NAME,
                null,// when columns is null it selects all the columns.
                where,/*A filter declaring which rows to return, Passing null will return all*/
                whereArgs,/*WhereArgs – You may include ?s in selection, which will be replaced
                by the values from selectionArgs, in order that they appear in the selection.
                The values will be bound as Strings.*/
                null,
                null,
                null
                );

        return new CrimeCursorWrapper(cursor);
    }
}
