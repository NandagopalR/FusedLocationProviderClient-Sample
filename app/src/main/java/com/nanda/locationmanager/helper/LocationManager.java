package com.nanda.locationmanager.helper;

import android.location.Location;

public interface LocationManager {

    void getLastKnownLocation(Location lastLocation);

    void onLocationChanged(Location location);

}
