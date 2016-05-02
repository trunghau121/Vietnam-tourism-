package com.example.hau.dulichviet.ui.main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.MatrixCursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.example.hau.dulichviet.adapter.AdapterAutoComplete;
import com.example.hau.dulichviet.adapter.RecyclerViewAdapter;
import com.example.hau.dulichviet.models.database.Place;
import com.example.hau.dulichviet.ui.base.BaseFragment;
import com.example.hau.dulichviet.ui.map_tour.MapTour;
import com.example.hau.dulichviet.R;
import com.example.hau.dulichviet.utils.Share;
import java.util.List;

import butterknife.Bind;


/**
 * Created by HAU on 10/3/2015.
 */
public class FragmentTour extends BaseFragment implements FragmentTourPresenter.View {
    @Bind(R.id.rvTour)
    RecyclerView rvTour;
    private SearchView searchView;
    private MatrixCursor c = new MatrixCursor(new String[]{BaseColumns._ID, "Name"});
    private AdapterAutoComplete autoComplete;
    private FragmentTourPresenter presenter;
    private RecyclerViewAdapter adapter;

    @Nullable
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        presenter = new FragmentTourPresenter();
        presenter.bindView(this);
        adapter = new RecyclerViewAdapter(getActivity().getLayoutInflater());
        adapter.setOnClickItem(this);
        rvTour.setAdapter(adapter);
        autoComplete = new AdapterAutoComplete(getActivity(), c);
        presenter.getIntent(getArguments());
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            rvTour.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        } else {
            rvTour.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void shareClick(View v, Place place) {
        Share.shareURL(place.share_link, getActivity());
    }

    @Override
    public void readMoreClick(View v, Place place) {
        presenter.openDetailTour(place);
    }

    @Override
    public void mapClick(View v, Place place) {
        Intent it = new Intent(getActivity(), MapTour.class);
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
                presenter.openSearchTour(position);
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
                autoComplete.setTextSearch(newText);
                presenter.searchTour(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);

    }


    @Override
    public void showDataSearch(MatrixCursor c) {
        autoComplete.changeCursor(c);
    }

    @Override
    public void showDataPlace(@NonNull List<Place> places) {
        adapter.setDataSource(places);
    }
}
