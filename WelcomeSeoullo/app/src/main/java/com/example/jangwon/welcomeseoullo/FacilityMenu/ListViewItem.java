package com.example.jangwon.welcomeseoullo.FacilityMenu;

import android.graphics.drawable.Drawable;

public class ListViewItem {

    private Drawable iconDrawable ;
    private String titleStr ;
    private String descStr ;

    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }

    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public Drawable getIcon() {
        return this.iconDrawable ;
    }

}
