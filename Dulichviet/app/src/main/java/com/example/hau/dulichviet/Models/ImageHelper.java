package com.example.hau.dulichviet.models;

import com.example.hau.dulichviet.R;

/**
 * Created by HAU on 12/10/2015.
 */
public class ImageHelper {
    public static int getIconMarker(int catelogy){
        int icon=0;
       switch (catelogy){
           case 1:
               icon= R.drawable.category1;
               break;
           case 2:
               icon= R.drawable.category2;
               break;
           case 3:
               icon= R.drawable.category3;
               break;
           case 4:
               icon= R.drawable.category4;
               break;
           case 5:
               icon= R.drawable.category5;
               break;
           case 6:
               icon= R.drawable.category6;
               break;
           case 7:
               icon= R.drawable.category7;
               break;
           case 8:
               icon= R.drawable.category8;
               break;
           case 9:
               icon= R.drawable.category9;
               break;
           case 10:
               icon= R.drawable.category10;
               break;
           case 11:
               icon= R.drawable.category11;
               break;
           case 12:
               icon= R.drawable.category12;
               break;
           case 13:
               icon= R.drawable.category13;
               break;
           case 14:
               icon= R.drawable.category14;
               break;
           case 15:
               icon= R.drawable.category15;
               break;
           case 16:
               icon= R.drawable.category16;
               break;
           case 17:
               icon= R.drawable.category17;
               break;
           default:
               icon= R.drawable.clustering_marker;


       }
        return  icon;
    }
}
