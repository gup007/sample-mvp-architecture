package com.massiveinfinity.delivery.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.massiveinfinity.delivery.data.Delivery;
import com.massiveinfinity.delivery.data.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SurvivoR on 8/25/2017.
 * {@link DBHelper}
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Delivery.db";
    private static final String DELIVERY_TABLE_NAME = "deliveries";
    private static final String DELIVERY_COLUMN_ID = "id";
    private static final String DELIVERY_COLUMN_DESCRIPTION = "description";
    private static final String DELIVERY_COLUMN_IMAGE_URL = "image_url";
    private static final String DELIVERY_COLUMN_LAT = "lat";
    private static final String DELIVERY_COLUMN_LNG = "lng";
    private static final String DELIVERY_COLUMN_ADDRESS = "address";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + DELIVERY_TABLE_NAME + "("
                + DELIVERY_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DELIVERY_COLUMN_DESCRIPTION + " TEXT,"
                + DELIVERY_COLUMN_IMAGE_URL + " TEXT,"
                + DELIVERY_COLUMN_LAT + " REAL,"
                + DELIVERY_COLUMN_LNG + " REAL,"
                + DELIVERY_COLUMN_ADDRESS + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DELIVERY_TABLE_NAME);
        onCreate(db);
    }

    public void insertDelivery(Delivery delivery) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DELIVERY_COLUMN_DESCRIPTION, delivery.getDescription());
        values.put(DELIVERY_COLUMN_IMAGE_URL, delivery.getImageUrl());
        values.put(DELIVERY_COLUMN_LAT, delivery.getLocation().getLatitude());
        values.put(DELIVERY_COLUMN_LNG, delivery.getLocation().getLongitude());
        values.put(DELIVERY_COLUMN_ADDRESS, delivery.getLocation().getAddress());
        db.insert(DELIVERY_TABLE_NAME, null, values);
        db.close();
    }

    public Delivery getDelivery(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DELIVERY_TABLE_NAME + " where id=" + id + "", null);
        if (cursor == null) {
            return null;
        }
        cursor.moveToFirst();
        Delivery delivery = new Delivery();
//        delivery.setId(cursor.getInt(cursor.getColumnIndex(DELIVERY_COLUMN_ID)));
        delivery.setDescription(cursor.getString(cursor.getColumnIndex(DELIVERY_COLUMN_DESCRIPTION)));
        delivery.setImageUrl(cursor.getString(cursor.getColumnIndex(DELIVERY_COLUMN_ADDRESS)));
        Location location = new Location();
        location.setLatitude(cursor.getDouble(cursor.getColumnIndex(DELIVERY_COLUMN_LAT)));
        location.setLongitude(cursor.getDouble(cursor.getColumnIndex(DELIVERY_COLUMN_LNG)));
        location.setAddress(cursor.getString(cursor.getColumnIndex(DELIVERY_COLUMN_ADDRESS)));
        delivery.setLocation(location);
        // return contact
        return delivery;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, DELIVERY_TABLE_NAME);
    }

    public void updateDelivery(Delivery delivery) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DELIVERY_COLUMN_DESCRIPTION, delivery.getDescription());
        values.put(DELIVERY_COLUMN_IMAGE_URL, delivery.getImageUrl());
        values.put(DELIVERY_COLUMN_LAT, delivery.getLocation().getLatitude());
        values.put(DELIVERY_COLUMN_LNG, delivery.getLocation().getLongitude());
        values.put(DELIVERY_COLUMN_ADDRESS, delivery.getLocation().getAddress());
//        db.update(DELIVERY_TABLE_NAME, values, DELIVERY_COLUMN_ID + " = ?",
//                new String[]{Integer.toString(contact.getId())});
    }

    public Integer deleteDelivery(Delivery delivery) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DELIVERY_TABLE_NAME, DELIVERY_COLUMN_ID + " = ?",
                new String[]{Integer.toString(1)});
    }

    public List<Delivery> getAllDelivery() {
        List<Delivery> deliveries = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + DELIVERY_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Delivery delivery = new Delivery();
                delivery.setDescription(cursor.getString(cursor.getColumnIndex(DELIVERY_COLUMN_DESCRIPTION)));
                delivery.setImageUrl(cursor.getString(cursor.getColumnIndex(DELIVERY_COLUMN_IMAGE_URL)));
                Location location = new Location();
                location.setLatitude(cursor.getDouble(cursor.getColumnIndex(DELIVERY_COLUMN_LAT)));
                location.setLongitude(cursor.getDouble(cursor.getColumnIndex(DELIVERY_COLUMN_LNG)));
                location.setAddress(cursor.getString(cursor.getColumnIndex(DELIVERY_COLUMN_ADDRESS)));
                delivery.setLocation(location);
                deliveries.add(delivery);
            } while (cursor.moveToNext());
        }
        return deliveries;
    }
}
