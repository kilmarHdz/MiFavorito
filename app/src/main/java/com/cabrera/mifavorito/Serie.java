package com.cabrera.mifavorito;

/**
 * Created by Kilmar Cabrera on 25/4/2018.
 */

public class Serie {
    private String name;
    private String caps;
    private int img;
    private String desc;
    private boolean fav;

    //Constructor del objecto Serie
    public Serie(String name, String caps, int img, String desc) {
        this.name = name;
        this.caps = caps;
        this.img = img;
        this.desc = desc;
        fav = false;
    }

    /* Getters y Setters de los elementos */
    public String getName() {
        return name;
    }

    public String getCaps() {
        return caps;
    }

    public int getImg() {
        return img;
    }

    public String getDesc() {
        return desc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setCaps(String caps) {
        this.caps = caps;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }
}
