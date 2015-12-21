package com.example.hau.dulichviet.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hau.dulichviet.Models.DataPlace;
import com.example.hau.dulichviet.R;

import java.util.ArrayList;

/**
 * Created by HAU on 11/30/2015.
 */
public class AdapterAutoComplete extends CursorAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<DataPlace.Place> places;
    private String textSearch = "";

    public AdapterAutoComplete(Context context, Cursor c) {
        super(context, c);
        this.mContext = context;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = inflater.inflate(R.layout.item_search_autocomplete, parent, false);
        return v;
    }

    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtAutocomplete = (TextView) view.findViewById(R.id.tvAutocomplete);
        String name =cursor.getString(1);
        int indexStart = getIndexOf(cursor.getString(1),textSearch);
        int indexEnd = textSearch.length()+indexStart;
        SpannableString textSpan = new SpannableString(name);
        textSpan.setSpan(new BackgroundColorSpan(mContext.getResources().getColor(R.color.grey)), indexStart, indexEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtAutocomplete.setText(textSpan);

    }

    private int getIndexOf(String text, String textSearch) {
        int n=text.toUpperCase().indexOf(textSearch.toUpperCase());
        return n;
    }
}
