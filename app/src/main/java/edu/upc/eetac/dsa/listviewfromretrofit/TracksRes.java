package edu.upc.eetac.dsa.listviewfromretrofit;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TracksRes {

    @SerializedName("tracks")
    @Expose
    private List<Track> tracks;

    public TracksRes() {

    }

    public TracksRes(List<Track> tracks) {
        super();
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "{" + tracks +  "}";
    }
}
