package com.example.hau.dulichviet.adapter;

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
import com.example.hau.dulichviet.ui.base.BaseAdapter;
import com.example.hau.dulichviet.ui.base.BaseHolder;
import butterknife.Bind;

/**
 * Created by HAU on 11/16/2015.
 */
public class RecyclerViewAdapter extends BaseAdapter<Place,BaseHolder> {
    private OnClickItem clickItem;

    public RecyclerViewAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.custom_list_tour,parent,false));
    }

    public void setOnClickItem(OnClickItem clickItem) {
        this.clickItem = clickItem;
    }


    class MyViewHolder extends BaseHolder<Place> {
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
        }

        protected Place place;

        @Override
        public void bindData(Place data) {
            this.place = data;
            Glide.with(itemView.getContext()).load(Constants.PICASSO + data.image_id + Constants.ORIGIN1).centerCrop().placeholder(R.drawable.image_default).into(ivTour);
            txtName.setText(data.name.toUpperCase());
            String address = data.address;
            txtAddress.setText(Html.fromHtml(address));
            String content = data.description;
            txtContent.setText(Html.fromHtml(content));
        }

        @Override
        public void bindEvent() {
            btnMap.setOnClickListener(v ->{
                if (clickItem != null) {
                    clickItem.mapClick(v, place);
                }
            });
            btnReadMore.setOnClickListener(v ->{
                if (clickItem != null) {
                    clickItem.readMoreClick(v,place);
                }
            });
            btnShare.setOnClickListener(v ->{
                if (clickItem != null) {
                    clickItem.shareClick(v,place);
                }
            });

        }


    }

}
