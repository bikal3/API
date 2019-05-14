package com.example.travelnepalapp.Model;

import android.support.v7.app.AppCompatActivity;

public class DashboardModel extends AppCompatActivity {
    private String placename;
    private int image;

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
