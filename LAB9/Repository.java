package com.example.lab9_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Repository extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "lab9";

    public Repository(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE IF NOT EXISTS locations ("
                +            "Id INTEGER PRIMARY KEY, "
                +            "Name TEXT,"
                +            "Longitude REAL, "
                +            "Latitude REAL); ";

        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS locations");
        onCreate(sqLiteDatabase);
    }

    public void addLocation(Location loc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Name", loc.getName());
        values.put("Longitude", loc.getLongitude());
        values.put("Latitude", loc.getLatitude());

        db.insert("locations", null, values);
        db.close();
    }

    public List<Location> getLocations() {
        List<Location> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String sqlSelect = "SELECT * FROM locations";
        Cursor cursor = db.rawQuery(sqlSelect, null);

        if (cursor.moveToFirst()) {
            do {
                Location location = getLocationFromCursor(cursor);
                list.add(location);
            } while (cursor.moveToNext());
        }

        db.close();
        return list;
    }

    public Location getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("locations"
                , new String[] {"Id", "Name", "Longitude", "Latitude"}
                , "Id=?"
                , new String[] { String.valueOf(id) }
                , null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        return getLocationFromCursor(cursor);
    }

    public Location getByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("locations"
                , new String[] {"Id", "Name", "Longitude", "Latitude"}
                , "Name=?"
                , new String[] { name }
                , null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();
        else
            return null;

        return getLocationFromCursor(cursor);
    }

    private Location getLocationFromCursor(Cursor cursor) {
        Location location = new Location();
        location.setId(Integer.parseInt(cursor.getString(0)));
        location.setName(cursor.getString(1));
        location.setLongitude(Float.parseFloat(cursor.getString(2)));
        location.setLatitude(Float.parseFloat(cursor.getString(3)));
        return location;
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM locations";
        db.execSQL(sql);
        db.close();
    }
}
