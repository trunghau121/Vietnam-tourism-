package com.example.hau.dulichviet.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hau.dulichviet.Interfaces.OnClickItem;
import com.example.hau.dulichviet.Models.Constant;
import com.example.hau.dulichviet.Models.DataPlace;
import com.example.hau.dulichviet.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HAU on 11/16/2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private ArrayList<DataPlace.Place> places;
    private LayoutInflater inflater;
    private Context mContext;
    private float scale;
    private int width;
    private int height;
    private OnClickItem clickItem;

    public RecyclerViewAdapter(Context mContext, ArrayList<DataPlace.Place> places) {
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.places = places;
        scale = mContext.getResources().getDisplayMetrics().density;
        width = mContext.getResources().getDisplayMetrics().widthPixels - (int) (14 * scale + 0.5f);
        height = (width / 16) * 9;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.custom_list_tour, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(mContext).load(Constant.PICASSO + places.get(position).getImage_id() + Constant.ORIGIN1).centerCrop().placeholder(R.drawable.image_default).into(holder.ivTour);
//        String url =Constant.PICASSO + places.get(position).getImage_id() + Constant.ORIGIN1;
//        Picasso.with(mContext).load(url).placeholder(R.drawable.image_default).error(R.drawable.image_default).fit().into(holder.ivTour);
        holder.txtName.setText(places.get(position).getName().toUpperCase());
        String address = places.get(position).getAddress();
        holder.txtAddress.setText(Html.fromHtml(address));
        String content = places.get(position).getDescription();
        holder.txtContent.setText(Html.fromHtml(content));
    }

    public void setOnClickItem(OnClickItem clickItem) {
        this.clickItem = clickItem;
    }

    @Override
    public int getItemCount() {
        return places.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.tvName)
        TextView txtName;
        @InjectView(R.id.tvContent)
        TextView txtContent;
        @InjectView(R.id.tvAddress)
        TextView txtAddress;
        @InjectView(R.id.ivTour)
        ImageView ivTour;
        @InjectView(R.id.btnMap)
        ImageButton btnMap;
        @InjectView(R.id.btnShare)
        ImageButton btnShare;
        @InjectView(R.id.btnReadMore)
        Button btnReadMore;

        public MyViewHolder(View v) {
            super(v);
            ButterKnife.inject(this, v);
            btnMap.setOnClickListener(this);
            btnShare.setOnClickListener(this);
            btnReadMore.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnMap) {
                if (clickItem != null) {
                    clickItem.mapClick(v, getPosition());
                }
            } else if (v.getId() == R.id.btnShare) {
                if (clickItem != null) {
                    clickItem.shareClick(v, getPosition());
                }

            } else if (v.getId() == R.id.btnReadMore) {
                if (clickItem != null) {
                    clickItem.readMoreClick(v, getPosition());
                }

            }
        }
    }

}
