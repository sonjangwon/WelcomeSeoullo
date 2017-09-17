package com.example.jangwon.welcomeseoullo;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

/**
 * Created by Jangwon on 2017-09-17.
 */

public class ManagementLocation {

    private static ManagementLocation managementLocation;

    // 사용자 위치 수신기
    private LocationManager locationManager;
    private LocationListener locationListener;
    Location currentLocation=null;
    double currentLatitude;
    double currentLongitude;
    String currentAddress;

    public static ManagementLocation getInstance(){
        if(managementLocation == null){
            managementLocation = new ManagementLocation();
        }
        return managementLocation;
    }


    public void setCurrentLatitude(double currentLatitude)
    {
        this.currentLatitude = currentLatitude;
    }

    public double getCurrentLatitude()
    {
        return currentLatitude;
    }

    public void setCurrentLongitude(double currentLongitude)
    {
        this.currentLongitude = currentLongitude;
    }

    public double getCurrentLongitude()
    {
        return currentLongitude;
    }

    public void setCurrentAddress(String currentAddress)
    {
        this.currentAddress = currentAddress;
    }

    public String getCurrentAddress()
    {
        return currentAddress;
    }

}

