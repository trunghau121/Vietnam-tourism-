package com.example.hau.dulichviet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hau.dulichviet.Constants;
import com.example.hau.dulichviet.interfaces.OnClickItem;
import com.example.hau.dulichviet.R;
import com.example.hau.dulichviet.models.database.Place;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by HAU on 11/16/2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<Place> places;
    private LayoutInflater inflater;
    private Context mContext;
    private float scale;
    private int width;
    private int height;
    private OnClickItem clickItem;

    public RecyclerViewAdapter(Context mContext, List<Place> places) {
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
        Glide.with(mContext).load(Constants.PICASSO + places.get(position).image_id + Constants.ORIGIN1).centerCrop().placeholder(R.drawable.image_default).into(holder.ivTour);
        holder.txtName.setText(places.get(position).name.toUpperCase());
        String address = places.get(position).address;
        holder.txtAddress.setText(Html.fromHtml(address));
        String content = places.get(position).description;
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
        @Bind(R.id.tvName)
        TextView txtName;
        @Bind(R.id.tvContent)
        TextView txtContent;
        @Bind(R.id.tvAddress)
        TextView txtAddress;
        @Bind(R.id.ivTour)
        ImageView ivTour;
        @Bind(R.id.btnMap)
        ImageButton btnMap;
        @Bind(R.id.btnShare)
        ImageButton btnShare;
        @Bind(R.id.btnReadMore)
        Button btnReadMore;

        public MyViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
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
