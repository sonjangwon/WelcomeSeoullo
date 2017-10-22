package com.example.jangwon.welcomeseoullo.FacilityMenu;

/**
 * Created by Jangwon on 2017-09-19.
 */

public class ManageListToMap {
    //GuideInfoFragment의 ListGuideFragment에서 한 장소를 클릭했을 경우
    //Fragment의 상태변화 정보 저장과 해당 장소의 위도 경도를 set,get하는 싱글톤 클래스

    private static ManageListToMap manageListToMap;

    double clickedLatitude;
    double clickedLongitude;
    String clickedPlaceName;
    String fragmentCondition;
    boolean clickedListView = false;


    public static ManageListToMap getInstance(){
        if(manageListToMap == null){
            manageListToMap = new ManageListToMap();
        }
        return manageListToMap;
    }

    public void setClickedPlaceName(String clikedPlaceName)
    {
        this.clickedPlaceName = clikedPlaceName;
    }

    public String getClickedPlaceName()
    {
        return clickedPlaceName;
    }

    public void setClickedListView(boolean clickedListView)
    {
        this.clickedListView = clickedListView;
    }

    public boolean getClickedListView()
    {
        return clickedListView;
    }

    public void setClickedLatitude(double clickedLatitude)
    {
        this.clickedLatitude = clickedLatitude;
    }

    public double getClickedLatitude()
    {
        return clickedLatitude;
    }

    public void setClickedLongitude(double clickedLongitude)
    {
        this.clickedLongitude = clickedLongitude;
    }

    public double getClickedLongitude()
    {
        return clickedLongitude;
    }

    public void setFragmentCondition(String fragmentCondition)
    {
        this.fragmentCondition = fragmentCondition;
    }

    public String getFragmentCondition()
    {
        return fragmentCondition;
    }
}
