package com.example.travelnepalapp.Models;

import android.support.v7.app.AppCompatActivity;

public class DashboardModel extends AppCompatActivity {
    private String placename;
    private int image;
    private String name;
    private String desc;
    private String _id;
    public DashboardModel(String placename, int image, String name, String desc, String _id) {
        this.placename = placename;
        this.image = image;
        this.name = name;
        this.desc = desc;
        this._id = _id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    public DashboardModel(int image, String placename) {
        this.image = image;
        this.placename = placename;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }
}
