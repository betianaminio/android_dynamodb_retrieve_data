package betianaminio.dynamodb.retrievedata.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;

import java.util.ArrayList;

import betianaminio.dynamodb.retrievedata.models.Album;
import betianaminio.dynamodb.retrievedata.dao.DiscographyAsynckTasck;
import betianaminio.dynamodb.retrievedata.dao.IDynamoDBListener;
import betianaminio.dynamodb.retrievedata.R;
import betianaminio.dynamodb.retrievedata.views.adapters.DiscographyAdapter;


public class DiscographyActivity extends Activity {

    private ArrayList<Album> mDiscographies = null;

    private RecyclerView               mRecycleView          = null;
    private RecyclerView.Adapter       mRecycleAdapter       = null;
    private RecyclerView.LayoutManager mRecycleLayoutManager = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discography);

        retrieveDataFromDynamo();
    }

    private void initializeUI(){


        this.mRecycleView = (RecyclerView)findViewById(R.id.recycle_album_list);
        this.mRecycleView.setHasFixedSize(true);

        this.mRecycleLayoutManager = new LinearLayoutManager(this);
        this.mRecycleView.setLayoutManager(this.mRecycleLayoutManager);

        this.mRecycleAdapter = new DiscographyAdapter(this.getApplicationContext(), this.mDiscographies, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = mRecycleView.getChildAdapterPosition(view);

                if (pos >= 0 && pos < mRecycleAdapter.getItemCount()) {

                    String album_name = DiscographyActivity.this.mDiscographies.get(pos).getName();
                    String album_cover = DiscographyActivity.this.mDiscographies.get(pos).getCover();

                    Intent i = new Intent(DiscographyActivity.this, SongsPerAlbumActivity.class);
                    i.putExtra("album_name", album_name);
                    i.putExtra("album_cover",album_cover);

                    startActivity(i);

                }

            }
        });
        this.mRecycleView.setAdapter(this.mRecycleAdapter);

    }

    private void retrieveDataFromDynamo(){

        new DiscographyAsynckTasck(this, new IDynamoDBListener() {
            @Override
            public void onRetreiveData(PaginatedList result) {

                if ( result != null ) {
                    mDiscographies = new ArrayList<Album>(result);

                    initializeUI();
                }

                hideProgress();
            }

            @Override
            public void onError(String message) {

                hideProgress();

            }
        }).execute();

    }

    private void hideProgress(){

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressbar_loading_albums);
        progressBar.setVisibility(View.GONE);


    }
}
