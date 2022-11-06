package com.example.darkmode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
       {

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private Location location;
    private LocationManager mlocationManager;
    private LocationRequest locationRequest;
  private android.location.LocationListener locationListener;
    private long MIN_TIMEL=1000;
    private long MIN_DIST=5;
    private  LocationManager locationManager;
    private LatLng latLng;
    private boolean isPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
      ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.SEND_SMS},PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}
        ,PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.INTERNET},PackageManager.PERMISSION_GRANTED);


//        googleApiClient=new GoogleApiClient.Builder(this)
//
//                .addConnectionCallbacks(this).addConnectionCallbacks(this)
//                .addApi(LocationServices.API).build();
//        mlocationManager=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//        checkLocation();
    }

//
//    private boolean checkLocation() {
//        if (!isLocationEnabled()){
//            showAlert();
//        }
//        return isLocationEnabled();
//    }

//    private void showAlert() {
//        final AlertDialog.Builder dialog =new AlertDialog.Builder(this);
//        dialog.setTitle("Enable Location")
//                .setMessage("Your Location Setting is set to 'Off ,\nPlease Enable Location to use this app")
//                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                        startActivity(intent);
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//        dialog.show();
//    }
//
//    private boolean isLocationEnabled() {
//        locationManager =(LocationManager)getSystemService(Context.LOCATION_SERVICE);
//        return  locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||
//                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//    }

//    private boolean requestSinglePermisssion() {
//        Dexter.withActivity(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
//                .withListener(new PermissionListener() {
//                    @Override
//                    public void onPermissionGranted(PermissionGrantedResponse response) {
//                        isPermission=true;
//                    }
//
//                    @Override
//                    public void onPermissionDenied(PermissionDeniedResponse response) {
//if (response.isPermanentlyDenied()){
//    isPermission=false;
//}
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
//
//                    }
//                }).check();
//        return isPermission;
//    }

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
        LatLng sydney=new LatLng(-34,151);
          mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in  Sydney"));
          mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

      locationListener = new android.location.LocationListener() {
          @Override
          public void onLocationChanged(@NonNull Location location) {
              try {
                  latLng=new LatLng(location.getLatitude(),location.getLongitude());
                  mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in "+latLng));
                  mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                  String phone="99999";
                  String mLatitude=String.valueOf(location.getLatitude());
                  String mLongitude=String.valueOf(location.getLongitude());
                  String message ="Latitude "+mLatitude +" Longitude "+mLongitude;
                  SmsManager smsManager =SmsManager.getDefault();
                  smsManager.sendTextMessage(phone,null,message,null,null);
              }catch (Exception e){
                  e.printStackTrace();
              }
          }
      };
      locationManager =(LocationManager) getSystemService(LOCATION_SERVICE);
      try {
          locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,MIN_TIMEL,MIN_DIST, locationListener);
          locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIMEL,MIN_DIST, locationListener);
      }catch (SecurityException e){
          e.printStackTrace();
      }
    }

//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
//        ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
//            return;
//        }
//        startUpdates();
//        location =LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
//        if (location==null){
//            startUpdates();
//        }else {
//            Toast.makeText(this,"Location not detected",Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void startUpdates() {
//        locationRequest = LocationRequest.create()
//                .setInterval(UPDATE_INTERVAL)
//                .setFastestInterval(FASTEST_INTERVAL);
//        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
//            return;
//        }
//       LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,locationRequest,this);
//    }
//
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//
//    @Override
//    public void onLocationChanged(@NonNull Location location) {
//
//    }
}