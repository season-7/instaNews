package com.example.benard.habari;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class newsItem extends AppCompatActivity {
    private String newsHeading,newsDesc,time,date,image,imageurl;

    //private string imageurl;

    public newsItem(String newsHeading, String newsDesc, String date,String time,  String imageurl, String image) {
        this.newsHeading = newsHeading;
        this.newsDesc = newsDesc;
        this.time = time;
        this.date = date;
        this.imageurl = imageurl;
        this.image = image;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_item);
    }

    public String getNewsHeading() {
        return newsHeading;
    }

    public String getNewsDesc() {
        return newsDesc;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getImageurl() {
        return imageurl;
    }
}
