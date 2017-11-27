package edu.upc.eetac.dsa.listviewfromretrofit;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Track implements Serializable {

    @SerializedName("singer")
    @Expose
    private String singer;

    @SerializedName("title")
    @Expose
    private String title;

    public Track() {

    }

    public Track(String singer, String title) {
        super();
        this.singer = singer;
        this.title = title;
    }

    public String getSinger() {

        return singer;
    }

    public void setSinger(String singer) {

        this.singer = singer;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    @Override
    public String toString() {
        return "{" +
                "singer='" + singer + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
