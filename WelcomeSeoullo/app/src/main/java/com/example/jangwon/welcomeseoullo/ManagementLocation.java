package com.example.jangwon.welcomeseoullo;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

    public class ManagementLocation {
    //현재위치,상세주소 관리하는 싱글톤 클래스

    private static ManagementLocation managementLocation;

    // 사용자 위치 수신기
    private LocationManager locationManager;
    private LocationListener locationListener;
    Location currentLocation=null;
    double currentLatitude;
    double currentLongitude;
    String currentAddress;
    String sortSpinner="";
    double distanceSpinner;
    boolean requestLocationPermission=false;

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

    public void setSortSpinner(String sortSpinner){
        this.sortSpinner = sortSpinner;
    }

    public String getSortSpinner()
    {
        return sortSpinner;
    }

    public void setRequestLocationPermission(boolean requestLocation){

        this.requestLocationPermission=requestLocation;
    }

    public boolean getRequestLocationPermission()
    {
        return requestLocationPermission;
    }

}

