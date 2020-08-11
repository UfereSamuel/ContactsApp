package com.ighub.sqliteclass.dbfiles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contactsManager";
    public static final String TABLE_NAME = "contacts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_FIRSTNAME + " TEXT, "
                + COLUMN_LASTNAME + " TEXT, "
                + COLUMN_PHONE + " TEXT, "
                + COLUMN_EMAIL + " TEXT " + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);

    }

    public long addContact(ContactModel model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, model.getId());
        values.put(COLUMN_FIRSTNAME, model.getFname());
        values.put(COLUMN_LASTNAME, model.getLname());;
        values.put(COLUMN_EMAIL, model.getEmail());
        values.put(COLUMN_PHONE, model.getPhone());

        long result = db.insert(TABLE_NAME, null, values);

        db.close();
        return result;
    }

    public ArrayList<ContactModel> listContact() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<ContactModel> storeContacts = new ArrayList<>();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
                String fname = cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME));
                String lname = cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME));
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
                ContactModel model = new ContactModel(Integer.parseInt(id), fname, lname, email, phone);
                storeContacts.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeContacts;
    }
}
