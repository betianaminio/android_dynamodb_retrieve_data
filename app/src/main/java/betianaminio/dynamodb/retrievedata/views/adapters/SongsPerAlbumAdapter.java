package betianaminio.dynamodb.retrievedata.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import betianaminio.dynamodb.retrievedata.R;
import betianaminio.dynamodb.retrievedata.models.Song;



public class SongsPerAlbumAdapter extends RecyclerView.Adapter<SongsPerAlbumAdapter.SongsViewHolder> {

    private List<Song> mSongs;

    public SongsPerAlbumAdapter(List<Song> songs){

        this.mSongs = songs;
    }

    @Override
    public SongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_information_layout, parent, false);
        return new SongsPerAlbumAdapter.SongsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SongsViewHolder holder, int position) {
        holder.track.setText( Integer.toString(this.mSongs.get(position).getTrack()));
        holder.name.setText( this.mSongs.get(position).getSongName());
    }

    @Override
    public int getItemCount() {
        return this.mSongs.size();
    }

    public static class SongsViewHolder extends RecyclerView.ViewHolder{

        public TextView track;
        public TextView name;

        public SongsViewHolder(View itemView) {
            super(itemView);

            track   = (TextView) itemView.findViewById(R.id.tx_track);
            name    = (TextView) itemView.findViewById(R.id.tx_song_name);
        }
    }

}
