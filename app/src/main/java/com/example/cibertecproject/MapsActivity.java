package com.example.cibertecproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private Marker markerPrincipal;
    private final  static int REQUEST_FINE_LOCATION=100;
    private static final int REQUEST_SETTINGS = 101;
    private Button btnguardaubica;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double latitude = -12.0869;
        double longitude = -77.0491;
        float zoomLevel = 15f;

        // Add a marker in Sydney and move the camera
        LatLng sophianumLatLng = new LatLng(latitude, longitude);
        mMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(sophianumLatLng, zoomLevel));
        markerPrincipal = mMap.addMarker(new MarkerOptions().position(sophianumLatLng));

        setMapLongClick(mMap);
        inicializarcontroles();
       // setPoiClick(mMap);
    }

    void inicializarcontroles()
    {
        btnguardaubica = findViewById(R.id.btnguardaubica);
        btnguardaubica.setOnClickListener(this);
    }

    double latitudmarker, longitudmarker;
    private void setMapLongClick(final GoogleMap googleMap){
            googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                    mMap.clear();
                    String snippet = String.format(
                            Locale.getDefault(),
                            "Lat: %1$.5f, Lng: %2$.5f",
                            latLng.latitude,
                            latLng.longitude
                    );

                    googleMap.addMarker(new MarkerOptions().position(latLng)
                            .title("Mi marker")
                            .snippet(snippet)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    );
                    latitudmarker = latLng.latitude;
                    longitudmarker = latLng.longitude;
                }
            });
    }


    private boolean isPermissionGranted(){
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnguardaubica:
                if(isPermissionGranted())
                {
                    guardarubicacion();
                    break;
                }
                else{
                    ActivityCompat.requestPermissions(MapsActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_FINE_LOCATION);
                }
        }
    }

    void guardarubicacion()
    {
        if (latitudmarker != 0.0 && longitudmarker != 0.0)
        {
            //Log.i("Luis", "onPoiClick: " + latitudmarker);
            //Log.i("Luis", "onPoiClick: " + longitudmarker);
            Intent intent = new Intent(this, EventCreateEditActivity.class);
            intent.putExtra("latitud",latitudmarker );
            intent.putExtra("longitud", longitudmarker);
            startActivity(intent);
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Debes seleccionar la Ubicación", Toast.LENGTH_LONG);
            toast.show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_FINE_LOCATION:
                if(grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    guardarubicacion();
                }
                else if(grantResults[0] == PackageManager.PERMISSION_DENIED)
                {
                    boolean showRationale = ActivityCompat.
                            shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA);
                    if(!showRationale)
                    {
                        //Nos han denegado el permiso
                        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                                .setTitle("Abrir configuraciones")
                                .setMessage("Si desea usar esta funcionalidad, gestione el permiso")
                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package",getPackageName(),null);
                                        intent.setData(uri);
                                        startActivityForResult(intent, REQUEST_SETTINGS);
                                    }
                                })
                                .setNegativeButton("Cancelar",null);
                        builder.show();
                    }
                    else
                    {
                        //Solo nos han rechazado el permiso
                        Toast.makeText(this,"Necesitamos el permiso para acceder a la Ubicación",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}

