package com.example.jangwon.welcomeseoullo;

/**
 * Created by Jangwon on 2017-09-19.
 */

public class ListItemView {

    private String titleStr ;
    private String descStr ;

    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }

    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }


}
