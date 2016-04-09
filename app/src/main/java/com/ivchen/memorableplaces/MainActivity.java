package com.ivchen.memorableplaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Places - Arraylist initialized globally and as a static variable
    static ArrayList<String> arrayListPlaces;

    static ArrayAdapter arrayAdapter;

    // Locations - Arraylist to save locations - lat and long
    static ArrayList<LatLng> arrayListLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This is the table to store the memorable places - referenced
        ListView listView = (ListView) findViewById(R.id.listView);

        // Made it static so it is accessible anywhere in the package
        // Arraylist are always used with Listviews
        arrayListPlaces = new ArrayList<String>();

        // Default - add a new place - initial option
        arrayListPlaces.add("Add a new place...");


        // Default - 0,0 because thats the center of the map
        arrayListLocations = new ArrayList<>();
        arrayListLocations.add(new LatLng(0, 0));


        // Array adapter is used to place the items in the arraylist as items in the listview
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayListPlaces);

        // Set up the list view to use the just created adapter
        listView.setAdapter(arrayAdapter);



        // When you tap on the add a new place option from the list view
        // using setOnItemClickListener - gives default code
        // So basically, when you click the "add a new place" --> it jumps to MapsActivity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // New intent created - intent jumps to the Maps Activity
                // put extra named location info which contains the value of the position
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                i.putExtra("locationInfo", position);

                // Don't forget to run the activity
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
