package com.example.jangwon.welcomeseoullo.PublicData;

public class PublicParkingLotVO {
    private String ParkingLotName;
    private String ParkingLotType;
    private String ParkingLotLatitude;
    private String ParkingLotLongitude;

    public PublicParkingLotVO(){}

    public PublicParkingLotVO(String ParkingLotName, String ParkingLotType, String ParkingLotLatitude, String ParkingLotLongitude){
        this.ParkingLotName = ParkingLotName;
        this.ParkingLotType = ParkingLotType;
        this.ParkingLotLatitude = ParkingLotLatitude;
        this.ParkingLotLongitude = ParkingLotLongitude;
    }

    public String getParkingLotName(){
        return this.ParkingLotName;
    }
    public void setParkingLotName(String ParkingLotName){
        this.ParkingLotName = ParkingLotName;
    }

    public String getParkingLotType(){
        return this.ParkingLotType;
    }
    public void setParkingLotType(String ParkingLotType){
        this.ParkingLotType = ParkingLotType;
    }

    public String getParkingLotLatitude(){
        return this.ParkingLotLatitude;
    }
    public void setParkingLotLatitude(String ParkingLotLatitude){
        this.ParkingLotLatitude = ParkingLotLatitude;
    }

    public String getParkingLotLongitude(){
        return this.ParkingLotLongitude;
    }
    public void setParkingLotLongitude(String ParkingLotLongitude){
        this.ParkingLotLongitude = ParkingLotLongitude;
    }

}
