package com.example.hau.dulichviet.models;


import com.example.hau.dulichviet.interfaces.Resourceble;

/**
 * Created by Konstantin on 23.12.2014.
 */
public class SlideMenuItem  implements Resourceble {
    private int position;
    private int imageRes;

    public SlideMenuItem(int position, int imageRes) {
        this.position = position;
        this.imageRes = imageRes;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}
