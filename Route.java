package com.example.easytransportation.easyriderapp;

/**
 * Created by arjunpatidar on 26/12/17.
 */

import com.example.easytransportation.easyriderapp.Modules.Distance;
import com.example.easytransportation.easyriderapp.Modules.Duration;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class Route {
    public Distance distance;
    public Duration duration;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startLocation;

    public List<LatLng> points;
}
