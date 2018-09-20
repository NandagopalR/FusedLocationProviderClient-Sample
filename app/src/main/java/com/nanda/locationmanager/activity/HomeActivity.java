package com.nanda.locationmanager.activity;

import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;

import com.nanda.locationmanager.R;
import com.nanda.locationmanager.base.BaseActivity;
import com.nanda.locationmanager.helper.LocationHelper;
import com.nanda.locationmanager.helper.LocationManager;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements LocationManager {

    @BindView(R.id.latitude)
    TextView tvLatitude;
    @BindView(R.id.longitude)
    TextView tvLongitude;

    private LocationHelper locationHelper;
    private Location lastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_start_track)
    void onTrackingButtonClicked() {
        locationHelper = new LocationHelper(HomeActivity.this, this);
        locationHelper.startLocationUpdates();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (locationHelper != null) {
            locationHelper.startLocationUpdates();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (locationHelper != null) {
            locationHelper.stopLocationUpdates();
        }
    }

    @Override
    public void getLastKnownLocation(Location lastLocation) {
        if (lastLocation != null) {
            this.lastLocation = lastLocation;
            updateViews();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            lastLocation = location;
            updateViews();
        }
    }

    private void updateViews() {
        if (lastLocation != null) {
            tvLatitude.setText(String.format(Locale.getDefault(), "Latitude - %f", lastLocation.getLatitude()));
            tvLongitude.setText(String.format(Locale.getDefault(), "Longitude - %f", lastLocation.getLongitude()));
        }
    }
}
