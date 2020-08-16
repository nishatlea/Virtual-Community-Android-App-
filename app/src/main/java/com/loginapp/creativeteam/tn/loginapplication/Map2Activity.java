package com.loginapp.creativeteam.tn.loginapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.parse.ParseUser;

public class Map2Activity extends FragmentActivity implements OnMapReadyCallback {

    Location currentlocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int request_code = 101;

    private GoogleMap mMap;
    private DatabaseReference ref;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    String latx,longx,nm;
    double laty,longy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        // .findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchlastlocation();


    }

    private void fetchlastlocation() {
        if (ActivityCompat.checkSelfPermission(  this , Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED )
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION},request_code);
        }

        Task< Location > task = fusedLocationProviderClient.getLastLocation();

        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location!=null){
                    currentlocation = location ;
                    Toast.makeText(getApplicationContext(), currentlocation.getLatitude() + "" + currentlocation.getLongitude(), Toast.LENGTH_SHORT);
                    SupportMapFragment supportmapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    supportmapFragment.getMapAsync(Map2Activity.this);
                }

            }
        });




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
        //mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng latLng = new LatLng(currentlocation.getLatitude(),currentlocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("I am here.") ;
        googleMap.animateCamera(CameraUpdateFactory.newLatLng (latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng , 15));
        googleMap.addMarker(markerOptions);
        double latmark = currentlocation.getLatitude();
        double longmark = currentlocation.getLongitude();
        ref = FirebaseDatabase.getInstance().getReference("maplocate");


        final ParseUser parseUser1 = new ParseUser().getCurrentUser();
        String name = parseUser1.getString("name");

        //ref.child(parseUser1.getObjectId()).child("name").setValue(name);
        //ref.child(parseUser1.getObjectId()).child("latitude").setValue(latmark);
        ref.child(parseUser1.getObjectId()).child("line").setValue("Offline");
        //DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
      /*  Query mapQuery = ref.orderByChild("name").equalTo("name");

        mapQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("TAG", "database error");
            }
        });


       */

       // reference = FirebaseDatabase.getInstance().getReference("maplocate");

       /* reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //Log.d("tag", "======="+postSnapshot.child("latitude").getValue());
                    //Log.d("tag", "======="+postSnapshot.child("name").getValue());
                    // Log.d("tag", "======="+postSnapshot.child("longitude").getValue());
                    if ( postSnapshot.child("latitude")!= null && postSnapshot.child("longitude")!=null  && postSnapshot.child("name")!=null )
                    {

                        double x = (double) postSnapshot.child("latitude").getValue();
                        double y = (double) postSnapshot.child("longitude").getValue();
                        nm = (String) postSnapshot.child("name").getValue();
                        LatLng latLng2 = new LatLng( x, y);
                        MarkerOptions markerOptions2 = new MarkerOptions().position(latLng2).title(nm);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLng (latLng2));
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng2 , 15));
                        googleMap.addMarker(markerOptions2);


                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("tag", "Failed to read app title value.", error.toException());
            }
        });
*/








    }


   /* public void onRequestPermissionsResult(int request_code , @NotNull String[] permissions , String[] grantResults)
    {
        switch (request_code)
        {
            case REQUEST_CODE :
                 if
        }
    }*/
}
