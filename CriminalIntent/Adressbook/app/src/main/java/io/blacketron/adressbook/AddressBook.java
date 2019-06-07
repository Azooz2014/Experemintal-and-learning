package io.blacketron.adressbook;

import java.util.ArrayList;

/**
 * Created by Blacketron on 9/16/2017.
 */

//Singleton class.

public class AddressBook {
    private static final AddressBook ourInstance = new AddressBook();

    private ArrayList <NameAndAddress> mNamesAndAddresses;

    public static AddressBook getInstance() {
        return ourInstance;
    }

    private AddressBook() {

        mNamesAndAddresses = new ArrayList<>();

        //Hardcoded dummy data.

        //Create a new entry.
        NameAndAddress tempEntry = new NameAndAddress("B Obama ", "The white house ", "Washington ", "DC1");

        //Add it to the array list.
        mNamesAndAddresses.add(tempEntry);

        //Create a new entry.
        tempEntry = new NameAndAddress("E Windsor ", "Buckingham ", "London ", "SW1A 1AA");

        //Add it to the array list.
        mNamesAndAddresses.add(tempEntry);

        //Create a new entry.
        tempEntry = new NameAndAddress("V Putin ", "The Kermlin ", "Moscow ", "MS1");

        //Add it to the array list.
        mNamesAndAddresses.add(tempEntry);
    }

    public ArrayList<NameAndAddress> getBook() {
        return mNamesAndAddresses;
    }
}
