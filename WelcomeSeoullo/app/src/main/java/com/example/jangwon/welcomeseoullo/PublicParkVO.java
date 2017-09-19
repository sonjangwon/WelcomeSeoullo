package com.example.jangwon.welcomeseoullo;

public class PublicParkVO {
    private String ParkName;
    private String ParkLongitude;
    private String ParkLatitude;

    public PublicParkVO(){}

    public PublicParkVO(String ParkName, String ParkLongitude, String ParkLatitude){
        this.ParkName = ParkName;
        this.ParkLongitude = ParkLongitude;
        this.ParkLatitude = ParkLatitude;
    }

    public String getParkName(){
        return this.ParkName;
    }
    public void setParkName(String ParkName){
        this.ParkName = ParkName;
    }

    public String getParkLongitude(){
        return this.ParkLongitude;
    }
    public void setParkLongitude(String ParkLongitude){
        this.ParkLongitude = ParkLongitude;
    }

    public String getParkLatitude(){
        return this.ParkLatitude;
    }
    public void setParkLatitude(String ParkLatitude){
        this.ParkLatitude = ParkLatitude;
    }

}
