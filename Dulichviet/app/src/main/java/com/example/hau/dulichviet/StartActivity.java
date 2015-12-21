package com.example.hau.dulichviet;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.hau.dulichviet.Adapter.RecyclerViewAdapter;
import com.example.hau.dulichviet.Models.DataPlace;
import com.example.hau.dulichviet.Utils.PlaceApi;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by HAU on 10/4/2015.
 */
public class StartActivity extends AppCompatActivity {
    private ArrayList<DataPlace.Place> arrayPlace = new ArrayList<DataPlace.Place>();
    public static int RADIUS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_start_app);
        getPlaceFromService();
    }

    private void getPlaceFromService() {
        PlaceApi placeApi = new PlaceApi();
        placeApi.service().getPlace(new Callback<DataPlace>() {
            @Override
            public void success(DataPlace dataPlace, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }

        });
    }


}
