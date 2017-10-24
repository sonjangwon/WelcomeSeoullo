package com.example.jangwon.welcomeseoullo.PublicData;

public class TraditionalMarketVO {
    private String MarketName;
    private String MarketLatitude;
    private String MarketLongitude;

    public TraditionalMarketVO(){}

    public TraditionalMarketVO(String MarketName, String MarketLatitude, String MarketLongitude){
        this.MarketName = MarketName;
        this.MarketLatitude = MarketLatitude;
        this.MarketLongitude = MarketLongitude;
    }

    public String getMarketName(){
        return this.MarketName;
    }
    public void setMarketName(String MarketName){
        this.MarketName = MarketName;
    }

    public String getMarketLatitude(){
        return this.MarketLatitude;
    }
    public void setMarketLatitude(String MarketLatitude){
        this.MarketLatitude = MarketLatitude;
    }

    public String getMarketLongitude(){
        return this.MarketLongitude;
    }
    public void setMarketLongitude(String MarketLongitude){
        this.MarketLongitude = MarketLongitude;
    }
}
