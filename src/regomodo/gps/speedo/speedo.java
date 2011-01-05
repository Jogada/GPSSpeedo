package regomodo.gps.speedo;

import android.app.Activity;
import android.os.Bundle;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;
import android.widget.Toast;

public class speedo extends Activity {
	TextView spd, alt,trip;
	LocationManager lm;
	Toast msg;
	Location last_loc;
	Float trip_dist = (float) 0.0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        spd = (TextView) findViewById(R.id.speedo);
        alt = (TextView) findViewById(R.id.accuracy);
        trip = (TextView) findViewById(R.id.trip_dist);
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        last_loc = null;
        request_updates();
    }
        
    public void request_updates() {
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        	Toast.makeText(this, "GPS is Enabled in your device", Toast.LENGTH_SHORT).show();
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0, 0, locationListener);
        } 
        else {        	
        	Toast.makeText(this, "GPS is Disabled in your device", Toast.LENGTH_SHORT).show();	
        }
    }
    

//    public void onStop() {
//    	lm.removeUpdates(locationListener);
//    }
//    
//    public void onRestart() {
//    	request_updates();
//    }

    

    
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
    	int speed_now = (int) (loc.getSpeed() * 60 * 60)/1601;
    	
    	if (speed_now > 0) {
    		// Getting spurious values when not moving
    		// Probably accuracy related
    		if (last_loc != null) {
    			trip_dist += loc.distanceTo(last_loc);
    			
    		}
    		last_loc = loc;
    	}
    	
    	spd.setText(Integer.toString(speed_now));
    	alt.setText(Integer.toString((int) loc.getAccuracy()));
    	trip.setText(Float.toString(trip_dist));
    	
    	
    }
 




}
