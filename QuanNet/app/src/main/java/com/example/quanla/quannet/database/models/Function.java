package com.example.quanla.quannet.database.models;

/**
 * Created by QuanLA on 3/13/2017.
 */

public class Function {
    private String nameIcon;
    private int imgIcon;

    public Function(String nameIcon, int imgIcon) {
        this.nameIcon = nameIcon;
        this.imgIcon = imgIcon;
    }

    public String getNameIcon() {
        return nameIcon;
    }

    public void setNameIcon(String nameIcon) {
        this.nameIcon = nameIcon;
    }

    public int getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(int imgIcon) {
        this.imgIcon = imgIcon;
    }
}
