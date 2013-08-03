package org.nando.poc;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;


public class MainActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        try {
            dbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unnable to create db");
        }
        dbHelper.openDataBase();
        textView =  (TextView) findViewById(R.id.textView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void fetchData(View view) {
        BusStopsDataSource dataSource = new BusStopsDataSource(this);
        dataSource.open();
        List<BusStops> stops = dataSource.getAllBusStops();
        textView.setText("there are :"+stops.size()+ " eg:"+stops.get(1).getName());


    }
    
}
