package edu.upc.eetac.dsa.listviewfromretrofit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class MainActivity extends Activity {

    public static final String BASE_URL = "http://10.0.2.2:8080/";


    private EditText etCantant;
    private EditText etTitol;
    private EditText etTrackId;

    private TrackAPI trackServices;
    private Call<Track> calltrack;
    private Call<String> callstring;
    private Call<List<Track>> calllist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init views
        etCantant = (EditText) findViewById(R.id.etCantant);
        etTitol = (EditText) findViewById(R.id.etTitol);
        etTrackId = (EditText) findViewById(R.id.etTrackId);

        //init API service
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        trackServices = retrofit.create(TrackAPI.class);


    }

    @Override
    protected void onStop() {
        super.onStop();
//        if (calltrack != null) {
//            calltrack.cancel();
//        }
//        if (callstring != null) {
//            callstring.cancel();
//        }

    }

    void getTrack() {

        calltrack = trackServices.getTrack();
        calltrack.enqueue(new Callback<Track>() {

            @Override
            public void onResponse(Call<Track> call, Response<Track> response) {
                int statusCode = response.code();
                if (response.isSuccessful()) {
                    Track track = response.body();
                    Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode));
                    String singer = track.getSinger().toString();
                    String title = track.getTitle().toString();
                    etCantant.setText(singer);
                    etTitol.setText(title);
                } else {
                    Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode));
                }


            }

            @Override
            public void onFailure(Call<Track> call, Throwable t) {
                    // log error here since request failed
                Log.d("Request: ", "error loading API" + t.toString());
            }
        });
    }

    void getTrackById(Integer id) {

        calltrack = trackServices.getTrackById(id);
        calltrack.enqueue(new Callback<Track>() {

            @Override
            public void onResponse(Call<Track> call, Response<Track> response) {
                int statusCode = response.code();
                if (response.isSuccessful()) {
                    Track track = response.body();
                    Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode));
                    String singer = track.getSinger();
                    String title = track.getTitle();
                    etCantant.setText(singer);
                    etTitol.setText(title);
                } else {
                    Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode));
                }
            }

            @Override
            public void onFailure(Call<Track> call, Throwable t) {
                // log error here since request failed
                Log.d("Request: ", "error loading API" + t.toString());

            }

        });


    }

    void getTrackAll() {
        calllist = trackServices.getTrackAll();
        calllist.enqueue(new Callback<List<Track>>() {

            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                int statusCode = response.code();
                if (response.isSuccessful()) {
                    List<Track> tracks = response.body();
                    Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode));

                    Intent in1 = new Intent(MainActivity.this, TrackList.class);

                    in1.putExtra("tracks", (Serializable) tracks);
                    startActivity(in1);
                } else {

                    Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode));
                }
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                // log error here since request failed
                Log.d("Request: ", "error loading API" + t.toString());

            }

        });
    }

    void newTrack(String singer, String title) {

        Track track = new Track(singer, title);

        callstring = trackServices.newTrack(track);
        callstring.enqueue( new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                int statusCode = response.code();

                if (response.isSuccessful()) {

                    Toast.makeText(MainActivity.this, "Done! New track at position:" + response.body(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(MainActivity.this, "Error! No track added:" + statusCode, Toast.LENGTH_LONG).show();

                }

                Log.d("Post", "onResponse. Code: " + Integer.toString(statusCode));
                Log.d("Post", "onResponse. Body: " + response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // log error here since request failed
                Log.d("onResponse ", "error on post API" + t.toString());

            }

        });


    }

    void delTrackById(Integer id) {

        callstring = trackServices.delTrackById(id);
        callstring.enqueue( new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                int statusCode = response.code();
                etCantant.setText("");
                etTitol.setText("");
                if (statusCode == 201) {

                    Toast.makeText(MainActivity.this, "Done! Track deleted:" + response.body(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(MainActivity.this, "Error! No track deleted:" + statusCode, Toast.LENGTH_LONG).show();

                }

                Log.d("Post", "onResponse. Code: " + Integer.toString(statusCode));
                Log.d("Post", "onResponse. Body: " + response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // log error here since request failed
                Log.d("onResponse ", "error on del API" + t.toString());

            }

        });


    }


    public void onClickGetTrack(View v) {

        getTrack();

    }


    public void onClickNewTrack(View v)  {

        String singer = etCantant.getText().toString();
        String title = etTitol.getText().toString();

        newTrack(singer, title);

    }

    public void onClickGetTrackById(View v) {

        String s = etTrackId.getText().toString();
        Integer id = Integer.parseInt(s);

        if (id == 999) {
            // request all tracks
            getTrackAll();
        } else {
            // request track
            getTrackById(id);
        }
    }

    public void onClickDelTrackById(View v) {

        String s = etTrackId.getText().toString();
        Integer id = Integer.parseInt(s);

        if (id == 999) {
            // No delete
            Toast.makeText(MainActivity.this, "Track Id has to be below 999:", Toast.LENGTH_LONG).show();

        } else {
            // request track
            delTrackById(id);
        }
    }
}

