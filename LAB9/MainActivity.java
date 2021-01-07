package com.example.lab9_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Repository repo;

    private Spinner spinner;
    private Button btnAdd, btnDelete, btnMove;
    private EditText longitudeInput, latitudeInput, nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repo = new Repository(this);
        //seed();

        this.spinner = findViewById(R.id.spinner);
        updateSpinnerValues();

        longitudeInput = findViewById(R.id.longitudeInput);
        latitudeInput = findViewById(R.id.latitudeInput);
        nameInput = findViewById(R.id.nameInput);

        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnMove = findViewById(R.id.btnMove);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLocationBtnClick();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repo.deleteAll();
                updateMap();
                updateSpinnerValues();
            }
        });

        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToSelected();
            }
        });

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        updateMap();
    }

    private void updateSpinnerValues() {
        List<Location> locations = repo.getLocations();

        List<String> items = new ArrayList<>();
        for(Location l : locations) {
            items.add(l.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        this.spinner.setAdapter(adapter);
    }

    private void updateMap() {
        mMap.clear();

        List<Location> locations = repo.getLocations();
        for (Location l : locations) {
            LatLng latLng = new LatLng(l.getLatitude(), l.getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title("Marker in " + l.getName()));
        }
    }

    private void addLocationBtnClick() {
        Location loc = new Location();
        loc.setName(nameInput.getText().toString());
        loc.setLatitude(Float.parseFloat(latitudeInput.getText().toString()));
        loc.setLongitude(Float.parseFloat(longitudeInput.getText().toString()));

        repo.addLocation(loc);
        updateSpinnerValues();
        updateMap();
        moveToLocation(loc);

        nameInput.getText().clear();
        latitudeInput.getText().clear();
        longitudeInput.getText().clear();
    }

    private void moveToSelected() {
        Object item = spinner.getSelectedItem();
        if (item != null) {
            String name = item.toString();
            Location l = repo.getByName(name);

            if(l != null)
                moveToLocation(l);
            else
                Toast.makeText(this, "Item by name : \"" + name + "\" not found", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "No item selected", Toast.LENGTH_LONG).show();
    }

    private void moveToLocation(Location l) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(l.getLatitude(), l.getLongitude()), 8.0f));
    }

    private void seed() {
        //Plunge
        //55.91139
        //21.84417

        //Vilnius
        //54.68916
        //25.2798

        //Kaunas
        //54.9
        //23.9
    }
}