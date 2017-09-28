package stoyanoff.oceanbnb_android.cruisedetails;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import stoyanoff.oceanbnb_android.R;
import stoyanoff.oceanbnb_android.data.models.Cruise;
import stoyanoff.oceanbnb_android.data.models.Location;
import stoyanoff.oceanbnb_android.rollcall.ActivityRollCall;
import stoyanoff.oceanbnb_android.shipdetails.ActivityShipDetails;
import stoyanoff.oceanbnb_android.util.Constants;
import stoyanoff.oceanbnb_android.util.Injection;

import static android.content.Context.LOCATION_SERVICE;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by L on 24/09/2017.
 */

public class FragmentCruiseDetails extends Fragment implements CruiseDetailsContract.View{

    private static final int PERMISSION_LOCATION_REQUEST_CODE = 13;

    private CruiseDetailsContract.Presenter presenter;
    private MapView cruiseMapView;
    private GoogleMap cruiseGoogleMap;
    private LocationManager locationManager;
    private android.location.Location lastLocation;


    public static FragmentCruiseDetails newInstance(){
        return new FragmentCruiseDetails();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CruiseDetailsPresenter(Injection.provideAppRepository(getContext()),this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cruise_details,container,false);
        final Cruise cruise =(Cruise) getActivity().getIntent().getSerializableExtra(Constants.CRUISE_EXTRA);

        TextView cruiseNameTextView = (TextView) view.findViewById(R.id.fragment_cruise_details_cruise_name_text_view);
        cruiseNameTextView.setText(cruise.getCruiseName());
        View rollCallLayout = view.findViewById(R.id.fragment_cruise_details_roll_call_layout);
        View shipDetailsLayout = view.findViewById(R.id.fragment_cruise_details_ship_details_layout);
        rollCallLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRollCall(cruise);
            }
        });
        shipDetailsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShipDetails(cruise.getShipId());
            }
        });

        cruiseMapView = (MapView) view.findViewById(R.id.fragment_cruise_details_mapview);
        cruiseMapView.onCreate(savedInstanceState);
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        cruiseMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                cruiseGoogleMap = googleMap;
                MapsInitializer.initialize(getActivity());
                presenter.getCruiseDetails(cruise.getCruiseId());
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    cruiseGoogleMap.setMyLocationEnabled(true);
                    lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(lastLocation == null){
                        lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }

                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        getActivity().requestPermissions(
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                PERMISSION_LOCATION_REQUEST_CODE);
                    }
                }
                cruiseGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        try {
                            if ((marker.getTag()) != null) {
                                marker.setTitle(((Location)marker.getTag()).getLocationName());
                                marker.showInfoWindow();
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                });
            }
        });

        return view;
    }

    private void showMarkers(List<stoyanoff.oceanbnb_android.data.models.Location> locations){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        Marker marker;
        for (Location location : locations) {
                marker = cruiseGoogleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(location.getLatitude(), location.getLongitude())));
                marker.setTag(location);
                builder.include(marker.getPosition());

        }
        try {
            LatLngBounds bounds = builder.build();
            cruiseGoogleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
        } catch (IllegalStateException ex) {
            Log.d("mapfragment", "illegal state");
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_LOCATION_REQUEST_CODE) {

            if (permissions.length == 1 &&
                    permissions[0].equals( Manifest.permission.ACCESS_FINE_LOCATION) &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    cruiseGoogleMap.setMyLocationEnabled(true);
                    lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(lastLocation == null){
                        lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
                }
            }
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        cruiseMapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        cruiseMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        cruiseMapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        cruiseMapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cruiseMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        cruiseMapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        try{
            cruiseMapView.onSaveInstanceState(outState);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void setPresenter(CruiseDetailsContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showCruiseDetails(Cruise cruise) {
        showMarkers(cruise.getLocationList());
    }

    @Override
    public void showNoDataAvailable() {
        Toast.makeText(getContext(), R.string.fragment_cruise_details_no_data_toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRollCall(Cruise cruise) {
        Intent rollCallIntent = new Intent(getContext(), ActivityRollCall.class);
        rollCallIntent.putExtra(Constants.CRUISE_EXTRA,cruise);
        startActivity(rollCallIntent);
    }

    @Override
    public void showShipDetails(int shipId) {
        Intent shipIntent = new Intent(getContext(), ActivityShipDetails.class);
        shipIntent.putExtra(Constants.SHIP_ID_EXTRA,shipId);
        startActivity(shipIntent);
    }
}
