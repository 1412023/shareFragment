package demo.unity.ndan.mapdemo;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;

import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL;


public class FullscreenActivity extends FragmentActivity implements OnMapReadyCallback, SelectionFragment.MapContact {
    private GoogleMap mMap;
    private int mMapType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SelectionFragment selection = new SelectionFragment();
        fragmentTransaction.add(R.id.container, (Fragment) selection);

        Bundle argumentBundle = new Bundle();
        UIArgument argument = new UIArgument("Hybrid","Normal");
        argumentBundle.putSerializable("text", argument);
        selection.setArguments(argumentBundle);

        fragmentTransaction.commit();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(mMapType);
        LatLng vietnam = new LatLng(-43, 151);
        mMap.addMarker(new MarkerOptions().position(vietnam).title("Marker in Việt Nam"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vietnam));
    }

    @Override
    public void setMyMapType(String type) {
        if (type == "hybrid") {
            mMapType = MAP_TYPE_HYBRID;
        } else {
            mMapType = MAP_TYPE_NORMAL;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        SupportMapFragment mapFragMent = new SupportMapFragment();
        mapFragMent.getMapAsync(this);

        transaction.replace(R.id.container,mapFragMent, null);
        transaction.addToBackStack("demo.unity.ndan.mapdemo.selection");
        transaction.commit();

    }
}
