package com.example.jangwon.welcomeseoullo;

public class PublicToiletVO {
    private String ToiletName;
    private String ToiletCategory;
    private String ToiletLatitude;
    private String ToiletLongitude;

    public PublicToiletVO(){}

    public PublicToiletVO(String ToiletName, String ToiletCategory, String ToiletLatitude, String ToiletLongitude){
        this.ToiletName = ToiletName;
        this.ToiletCategory = ToiletCategory;
        this.ToiletLatitude = ToiletLatitude;
        this.ToiletLongitude = ToiletLongitude;
    }

    public String getToiletName(){
        return this.ToiletName;
    }
    public void setToiletName(String ToiletName){
        this.ToiletName = ToiletName;
    }

    public String getToiletCategory(){
        return this.ToiletCategory;
    }
    public void setToiletCategory(String ToiletCategory){
        this.ToiletCategory = ToiletCategory;
    }

    public String getToiletLatitude(){
        return this.ToiletLatitude;
    }
    public void setToiletLatitude(String ToiletLatitude){
        this.ToiletLatitude = ToiletLatitude;
    }

    public String getToiletLongitude(){
        return this.ToiletLongitude;
    }
    public void setToiletLongitude(String ToiletLongitude){
        this.ToiletLongitude = ToiletLongitude;
    }

}
