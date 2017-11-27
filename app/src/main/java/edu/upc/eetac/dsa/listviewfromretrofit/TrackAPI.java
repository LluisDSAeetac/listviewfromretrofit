package edu.upc.eetac.dsa.listviewfromretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TrackAPI {

    @GET("/myapp/json/get")
    Call<Track> getTrack();

    @GET("/myapp/json/got/{id}")
    Call<Track> getTrackById(@Path("id") int trackid);

    @GET("/myapp/json/getAll")
    Call<List<Track>> getTrackAll();

    @POST("myapp/json/new")
    Call<String> newTrack(@Body Track track);

    @DELETE("/myapp/json/del/{id}")
    Call<String> delTrackById(@Path("id") int trackid);

}
