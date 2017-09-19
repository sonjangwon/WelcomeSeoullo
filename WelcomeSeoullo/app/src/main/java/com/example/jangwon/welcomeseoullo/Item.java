package com.example.jangwon.welcomeseoullo;

/**
 * Created by woga1 on 2017-09-19.
 */

public class Item {
    String date;
    String title;
    String getDate() {
        return this.date;
    }
    String getTitle() {
        return this.title;
    }


    Item(String title, String date) {
        this.title = title;
        this.date = date;
    }
}