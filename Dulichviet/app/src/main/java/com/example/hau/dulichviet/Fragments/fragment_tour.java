package com.example.hau.dulichviet.Fragments;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.MatrixCursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hau.dulichviet.Adapter.AdapterAutoComplete;
import com.example.hau.dulichviet.Adapter.RecyclerViewAdapter;
import com.example.hau.dulichviet.Interfaces.OnClickItem;
import com.example.hau.dulichviet.MainActivity;
import com.example.hau.dulichviet.MainDetail;
import com.example.hau.dulichviet.MapTour;
import com.example.hau.dulichviet.Models.DataPlace;
import com.example.hau.dulichviet.Network.Networking;
import com.example.hau.dulichviet.R;
import com.example.hau.dulichviet.SearchTour;
import com.example.hau.dulichviet.Utils.Share;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;


/**
 * Created by HAU on 10/3/2015.
 */
public class fragment_tour extends Fragment implements OnClickItem {
    @InjectView(R.id.rvTour)
    RecyclerView rvTour;
    private ArrayList<DataPlace.Place> arrayPlace = new ArrayList<DataPlace.Place>();
    private ArrayList<DataPlace.Place> list = new ArrayList<DataPlace.Place>();
    private ArrayList<DataPlace.Place> listSuggestion = new ArrayList<DataPlace.Place>();
    private SearchView searchView;
    private MatrixCursor c = new MatrixCursor(new String[]{BaseColumns._ID, "Name"});
    private AdapterAutoComplete autoComplete;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        ButterKnife.inject(this, v);
        autoComplete = new AdapterAutoComplete(getActivity(), c);
        int category_id = getArguments().getInt("category_id");

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            rvTour.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        }
        else{
            rvTour.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }
        arrayPlace = MainActivity.arrayPlace;
        //Toast.makeText(getActivity(),"ok :"+arrayPlace.get(0).getCategory_id(),Toast.LENGTH_SHORT).show();
       for (int i = 0; i < arrayPlace.size(); i++) {
               if (arrayPlace.get(i).getCategory_id()!=null&&Integer.parseInt(arrayPlace.get(i).getCategory_id()) == category_id) {
                   list.add(arrayPlace.get(i));
               }

        }
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), list);
        adapter.setOnClickItem(this);
        rvTour.setAdapter(adapter);
        return v;
    }

    @Override
    public void shareClick(View v, int position) {
      //  if (Networking.isCheckConnect(getActivity()))
            Share.shareURL(list.get(position).getShare_link(),getActivity());
//        else
//        {
//            new MaterialDialog.Builder(getActivity())
//                    .title(null)
//                    .content("Điện thoại của bạn hiện giờ không có internet.")
//                    .positiveText("OK")
//                    .negativeColor(Color.BLUE)
//                    .backgroundColor(Color.WHITE)
//                    .show();
//        }

    }

    @Override
    public void readMoreClick(View v, int position) {
        Intent it = new Intent(getActivity(), MainDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", list.get(position));
        it.putExtra("bundle", bundle);
        getActivity().startActivity(it);

    }

    @Override
    public void mapClick(View v, int position) {
        Intent it =new Intent(getActivity(),MapTour.class);
        startActivity(it);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        SearchManager manager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            searchView = (SearchView) menuItem.getActionView();
        } else {
            searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        }
        searchView.setSearchableInfo(manager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setSuggestionsAdapter(autoComplete);
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return true;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                Intent it = new Intent(getActivity(), SearchTour.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", listSuggestion.get(position));
                it.putExtra("bundle", bundle);
                searchView.setQuery(listSuggestion.get(position).getName(),
                        true);
                getActivity().startActivity(it);
                return true;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                populateAdapter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);

    }

    private void populateAdapter(String query) {
        c = new MatrixCursor(new String[]{BaseColumns._ID, "cityName"});
        listSuggestion=new ArrayList<>();
        String name="";
        if (query.trim().length() > 0) {
            for (int i = 0; i < arrayPlace.size(); i++) {
                name = arrayPlace.get(i).getName();
                int n = name.toUpperCase().indexOf(query.toUpperCase());
                if (n != -1) {
                    listSuggestion.add(arrayPlace.get(i));
                    c.addRow(new Object[]{i, name});
                    autoComplete.setTextSearch(query);
                }
            }
        }
        autoComplete.changeCursor(c);
    }

}