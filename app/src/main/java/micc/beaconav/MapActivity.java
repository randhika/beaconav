package micc.beaconav;

import micc.beaconav.map.Map;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class MapActivity extends FragmentActivity
{
    private Map map; // Might be null if Google Play services APK is not available.


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        map = new Map( this.getGMapFromXML() );
    }


    private GoogleMap getGMapFromXML()
    {
        // Try to obtain the map from the SupportMapFragment.
        return ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
    }


    @Override
    protected void onResume(){
        super.onResume();
    }

    public void eventManager()
    {

    }






    private  boolean fakeProximity = false;

    public void setFakeProximity(View view)
    {
        map.resetLastProxyMuseum();
        if(map.getFakeProximity() == false) {
            map.setFakeProximity(true);
        }

    }

    public void onClickNavigate(View view)
    {
        if(map.getCustomMarkerLatLng() != null)
            map.routeFromCustomMarker();
        else map.route();
    }

    public void onClickBtnIndoor(View view)
    {
        //Intent intent = new Intent(this, newTouchActivity.class);
        //Intent intent = new Intent(this, micc.beaconav.multitouch.TouchActivity.class);
        Intent intent = new Intent(this, micc.beaconav.newTouchActivity.class);

        startActivity(intent);
    }

    public void onClickBtnTestLocation1(View view)
    {
        //Intent intent = new Intent(this, newTouchActivity.class);
        //Intent intent = new Intent(this, micc.beaconav.multitouch.TouchActivity.class);
        Intent intent = new Intent(this, testAdaptedLocationActivity.class);
        startActivity(intent);
    }
    public void onClickBtnTestLocation2(View view)
    {
        //Intent intent = new Intent(this, newTouchActivity.class);
        //Intent intent = new Intent(this, micc.beaconav.multitouch.TouchActivity.class);
        Intent intent = new Intent(this, testLastLocationActivity.class);

        startActivity(intent);
    }

    public void onClickBtnJsonTest(View view)
    {
        Intent intent = new Intent(this, JSONTest.class);
        startActivity(intent);
    }

}
