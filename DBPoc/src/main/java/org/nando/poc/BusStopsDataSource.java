package org.nando.poc;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fernandoMac on 3/08/13.
 */
public class BusStopsDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    private String[] allColumns = {"field1","field2","field3","field4","field5","field6","field7","field8","field9","field10","field11"};

    public BusStopsDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public List<BusStops> getAllBusStops() {
        List<BusStops> stops = new ArrayList<BusStops>();
        Cursor cursor = database.query("STOPS",allColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            BusStops stop = new BusStops();
            stop.setName(cursor.getString(2));
            stops.add(stop);
            cursor.moveToNext();
        }
        cursor.close();
        return stops;
    }


}
