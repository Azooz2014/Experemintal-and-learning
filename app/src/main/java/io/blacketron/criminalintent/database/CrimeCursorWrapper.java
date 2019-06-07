package io.blacketron.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import io.blacketron.criminalintent.Model.Crime;

public class CrimeCursorWrapper extends CursorWrapper {

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    //pulling crime from cursor.
    public Crime getCrime (){

        String uuidString = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Columns.UUID));
        String title = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Columns.TITLE));
        long date = getLong(getColumnIndex(CrimeDbSchema.CrimeTable.Columns.DATE));
        int isSolved = getInt(getColumnIndex(CrimeDbSchema.CrimeTable.Columns.SOLVED));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);

        return crime;
    }
}
