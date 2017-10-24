package com.example.jangwon.welcomeseoullo.HomeMenu;

/**
 * Created by woga1 on 2017-09-19.
 */

public class Item {
    String date;
    String title;
    String url;
    String getDate() {
        return this.date;
    }
    String getTitle() {
        return this.title;
    }
    String getUrl() {return this.url;}


    Item(String title, String date, String url) {
        this.title = title;
        this.date = date;
        this.url = url;
    }
}