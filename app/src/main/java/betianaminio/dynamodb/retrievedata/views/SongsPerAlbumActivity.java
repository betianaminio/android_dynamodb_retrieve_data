package betianaminio.dynamodb.retrievedata.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import betianaminio.dynamodb.retrievedata.dao.IDynamoDBListener;
import betianaminio.dynamodb.retrievedata.R;
import betianaminio.dynamodb.retrievedata.models.Song;
import betianaminio.dynamodb.retrievedata.dao.SongsPerAlbumAsyncTask;
import betianaminio.dynamodb.retrievedata.views.adapters.SongsPerAlbumAdapter;

public class SongsPerAlbumActivity extends Activity {

    private ArrayList<Song> mSongs = null;

    private RecyclerView mRecycleView          = null;
    private RecyclerView.Adapter       mRecycleAdapter       = null;
    private RecyclerView.LayoutManager mRecycleLayoutManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_per_album);

        initializeUI();

    }

    private void initializeUI(){

        Intent intent = getIntent();
        final String album_name = intent.getExtras().getString("album_name");
        final String album_cover = intent.getExtras().getString("album_cover");

        TextView txAlbumName = (TextView)findViewById(R.id.tx_album_name);
        txAlbumName.setText(album_name);

        ImageView imgCover = ( ImageView )findViewById(R.id.img_album_cover);

        Glide.with(this).load( album_cover).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {


                retrieveDataFromDynamo(album_name);


                return false;
            }
        }).into(imgCover);

    }


    private void initializeList(){


        this.mRecycleView = (RecyclerView)findViewById(R.id.recycle_view_songs);
        this.mRecycleView.setHasFixedSize(true);

        this.mRecycleLayoutManager = new LinearLayoutManager(this);
        this.mRecycleView.setLayoutManager(this.mRecycleLayoutManager);

        this.mRecycleAdapter = new SongsPerAlbumAdapter(this.mSongs);
        this.mRecycleView.setAdapter(this.mRecycleAdapter);

    }

    private void retrieveDataFromDynamo( String filter_condition){

        new SongsPerAlbumAsyncTask(SongsPerAlbumActivity.this.getApplicationContext(), filter_condition, new IDynamoDBListener() {
            @Override
            public void onRetreiveData(PaginatedList result) {

                if( result!=null) {
                    mSongs = new ArrayList<Song>(result);

                    initializeList();
                }
            }

            @Override
            public void onError(String message) {
                Log.d("DynamoDB","Error: " + message);
            }
        }).execute();

    }

}
