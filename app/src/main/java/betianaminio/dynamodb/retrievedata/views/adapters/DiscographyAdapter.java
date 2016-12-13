package betianaminio.dynamodb.retrievedata.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import betianaminio.dynamodb.retrievedata.models.Album;
import betianaminio.dynamodb.retrievedata.R;


public class DiscographyAdapter extends RecyclerView.Adapter<DiscographyAdapter.DiscographyViewHolder> {

    private List<Album> discographylist;
    private Context mContext;
    private View.OnClickListener mListener = null;


    //Represents an item in the list
    static class DiscographyViewHolder extends RecyclerView.ViewHolder {
        
        ImageView cover;
        TextView name;
        TextView company;
        TextView year;

        DiscographyViewHolder(View v) {
            super(v);
            cover   = (ImageView) v.findViewById(R.id.img_cover);
            name    = (TextView) v.findViewById(R.id.tx_album_name);
            year    = (TextView) v.findViewById(R.id.tx_album_year);
            company = (TextView) v.findViewById(R.id.tx_album_company);
        }
    }


    public DiscographyAdapter(Context context, List<Album> items, View.OnClickListener listener) {

        this.mContext = context;
        this.discographylist = items;
        this.mListener = listener;
    }

    @Override
    public DiscographyAdapter.DiscographyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_information_card_view, parent, false);

        v.setOnClickListener(this.mListener);

        return new DiscographyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DiscographyAdapter.DiscographyViewHolder holder,int position) {

        holder.name.setText( this.discographylist.get(position).getName());
        holder.company.setText( this.discographylist.get(position).getCompay());
        holder.year.setText( this.discographylist.get(position).getYear());

        Glide.with(this.mContext).load( this.discographylist.get(position).getCover()).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {

                return false;
            }
        }).into(holder.cover);


    }

    @Override
    public int getItemCount() {
        return this.discographylist.size();
    }
}
