package edu.upc.eetac.dsa.listviewfromretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lluis on 31/5/17.
 */

public class TrackListAdapter extends BaseAdapter {

    private List<Track> tracks;
    private LayoutInflater lInflater;
    private Context context;

    public TrackListAdapter(Context context, List<Track> tracks) {
        this.context = context;
        this.tracks = tracks;
        //this.lInflater = LayoutInflater.from(context);
        this.lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tracks.size();
    }

    @Override
    public Track getItem(int position) {
        return tracks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        //LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = lInflater.inflate(R.layout.list_row, parent, false);

        TextView  nomCantant = (TextView) rowView.findViewById(R.id.tvListCantant);
        TextView  nomTema = (TextView)  rowView.findViewById(R.id.tvListTema);

        Track track = getItem(position);

        nomCantant.setText(track.getSinger());
        nomTema.setText(track.getTitle());

        return rowView;
    }

    class ContentView {
        TextView nomCantant;
        TextView nomTema;
    }
}
