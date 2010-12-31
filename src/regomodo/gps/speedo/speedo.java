package regomodo.gps.speedo;

import android.app.Activity;
import android.os.Bundle;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;
import android.widget.Toast;

public class speedo extends Activity {
	TextView spd, alt;
	LocationManager lm;
	Toast msg;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        spd = (TextView) findViewById(R.id.speedo);
        alt = (TextView) findViewById(R.id.altitude);
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        Location lastKnownLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        new_loc(lastKnownLocation);
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        	Toast.makeText(this, "GPS is Enabled in your device", Toast.LENGTH_SHORT).show();
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0, 0, locationListener);
        } 
        else {        	
        	Toast.makeText(this, "GPS is Disabled in your device", Toast.LENGTH_SHORT).show();	
        }
    }
    

    
    LocationListener locationListener = new LocationListener() {
    	   public void onLocationChanged(Location location) {
    		   new_loc(location);
    	   }

		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
    };
    
    public void new_loc(Location loc){
    	float speed_now = (loc.getSpeed() * 60 * 60)/1601;
    	spd.setText(Float.toString(speed_now));
    	alt.setText(Float.toString(loc.getAccuracy()));
    }
 




}
