package edu.upc.eetac.dsa.listviewfromretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

public class TrackList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_list);

        List<Track> tracks = (List<Track>) getIntent().getSerializableExtra("tracks");
        ListView lv = (ListView) findViewById(R.id.lvTrackList);

        TrackListAdapter adaptador = new TrackListAdapter(this, tracks);
        lv.setAdapter(adaptador);
    }
}
