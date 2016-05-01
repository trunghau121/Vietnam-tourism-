package com.example.hau.dulichviet.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hau.dulichviet.Constants;
import com.example.hau.dulichviet.models.DataPlace;
import com.example.hau.dulichviet.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by HAU on 12/9/2015.
 */
public class AdapterInfoWindow implements GoogleMap.InfoWindowAdapter {
    private Activity context;
    private DataPlace.Place place;
    private float scale;
    private int width;
    private int height;

    public AdapterInfoWindow(Activity context, DataPlace.Place place) {
        this.context = context;
        this.place = place;
        scale = context.getResources().getDisplayMetrics().density;
        width = context.getResources().getDisplayMetrics().widthPixels - (int) (14 * scale + 0.5f);
        height = (width / 16) * 9;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View v = this.context.getLayoutInflater().inflate(R.layout.custom_infowindow, null);
        MyViewHolder holder =new MyViewHolder(v);
        holder.Load(Constants.PICASSO + place.image_id + Constants.ORIGIN0);
        holder.txtTitle.setText(place.name.toUpperCase());
        String address = place.address;
        holder.txtLocation.setText(Html.fromHtml(address));
        String content = place.description;
        holder.txtDetail.setText(Html.fromHtml(content));
        return v;
    }
    class MyViewHolder implements View.OnClickListener {
        @Bind(R.id.tvTitle)
        TextView txtTitle;
        @Bind(R.id.tvDetail)
        TextView txtDetail;
        @Bind(R.id.tvLocation)
        TextView txtLocation;
        @Bind(R.id.iv_Image)
        ImageView ivTour;
        @Bind(R.id.ib_navigation)
        ImageButton ibNavigation;
        @Bind(R.id.ib_share)
        ImageButton ibShare;
        @Bind(R.id.btMore)
        Button btMore;

        public MyViewHolder(View v) {
            ButterKnife.bind(this, v);
            ibNavigation.setOnClickListener(this);
            ibShare.setOnClickListener(this);
            btMore.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.ib_share) {

            } else if (v.getId() == R.id.ib_navigation) {


            } else if (v.getId() == R.id.btMore) {


            }
        }
        public void Load(String url){
            new Load_Image().execute(url);
        }
        class Load_Image extends AsyncTask<String,Bitmap,Void>{

            @Override
            protected Void doInBackground(String... params) {
                Bitmap bmImg =null;
                try {
                    URL url = new URL(params[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bmImg = BitmapFactory.decodeStream(is);
                }catch (Exception ex){
                     ex.printStackTrace();
                }
                publishProgress(bmImg);
                return null;
            }

            @Override
            protected void onProgressUpdate(Bitmap... values) {
                super.onProgressUpdate(values);
                ivTour.setImageBitmap(values[0]);
            }
        }
    }
}
