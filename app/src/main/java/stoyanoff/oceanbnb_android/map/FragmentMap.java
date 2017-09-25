package stoyanoff.oceanbnb_android.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import stoyanoff.oceanbnb_android.R;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by L on 24/09/2017.
 */

public class FragmentMap extends Fragment implements MapContract.View{

    private static final int PERMISSION_LOCATION_REQUEST_CODE = 13;

    private MapView storesMapView;
    private GoogleMap storesGoogleMap;
    private LocationManager locationManager;
    private Location lastLocation;

    public static FragmentMap newInstance(){
        return new FragmentMap();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map,container,false);

        storesMapView = (MapView) view.findViewById(R.id.fragment_map_mapview);
        storesMapView.onCreate(savedInstanceState);
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        storesMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                storesGoogleMap = googleMap;
                MapsInitializer.initialize(getActivity());

                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    storesGoogleMap.setMyLocationEnabled(true);
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

//                updateMap("");
//
//                storesGoogleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
//                    @Override
//                    public View getInfoWindow(Marker marker) {
//                        View customInfoWindowView = ((LayoutInflater)getActivity()
//                                .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
//                                .inflate(R.layout.info_window_layout, null);
//                        return customInfoWindowView;
//                    }
//
//                    @Override
//                    public View getInfoContents(Marker marker) {
//                        return null;
//                    }
//                });
//                storesGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//                    @Override
//                    public boolean onMarkerClick(Marker marker) {
//                        selectedMarker = marker;
//                        slidingUpPanelLayout.setPanelHeight(panelSmallHeight);
//                        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                        imm.hideSoftInputFromWindow(storesMapView.getWindowToken(), 0);
//                        try {
//                            storeDetailsFragment.updateStoreInfo((Store) marker.getTag(),lastLocation);
//                        }catch (NullPointerException ex){
//
//                        }
//                        marker.setIcon(BitmapDescriptorFactory
//                                .fromBitmap(getMarkerBitmapFromView(((Store)marker.getTag()).getActiveOffersCount(),true)));
//                        return false;
//                    }
//                });
//                storesGoogleMap.setOnInfoWindowCloseListener(new GoogleMap.OnInfoWindowCloseListener() {
//                    @Override
//                    public void onInfoWindowClose(Marker marker) {
//                        selectedMarker = null;
//                        slidingUpPanelLayout.setPanelHeight(0);
//                        marker.setIcon(BitmapDescriptorFactory
//                                .fromBitmap(getMarkerBitmapFromView(((Store)marker.getTag()).getActiveOffersCount(),false)));
//                    }
//                });
            }
        });
        return view;
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
                    storesGoogleMap.setMyLocationEnabled(true);
                    lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(lastLocation == null){
                        lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
                //    updateMap("");
                }
            }
        }
    }

    @Override
    public void setPresenter(MapContract.Presenter presenter) {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onStart() {
        super.onStart();
        storesMapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        storesMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        storesMapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        storesMapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        storesMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        storesMapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        try{
            storesMapView.onSaveInstanceState(outState);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
