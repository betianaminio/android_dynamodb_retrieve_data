package betianaminio.dynamodb.retrievedata.dao;

import android.content.Context;
import android.os.AsyncTask;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;

import betianaminio.dynamodb.retrievedata.models.Song;


public class SongsPerAlbumAsyncTask extends AsyncTask<Void,Void,PaginatedList<Song>> {

    private Context mContext = null;
    private String mAlbumName = null;
    private IDynamoDBListener mDynamoDBListener = null;

    public SongsPerAlbumAsyncTask( Context context, String albumname, IDynamoDBListener listener ){

        this.mContext = context;
        this.mAlbumName = albumname;
        this.mDynamoDBListener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(PaginatedList<Song> songs) {
        super.onPostExecute(songs);

        this.mDynamoDBListener.onRetreiveData(songs);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected PaginatedList<Song> doInBackground(Void... voids) {

        try {
            //DynamoDB calls go here

            Song song = new Song();
            song.setAlbum(this.mAlbumName);


            DynamoDBQueryExpression<Song> queryExpression = new DynamoDBQueryExpression<Song>();
            queryExpression.withHashKeyValues(song);
            queryExpression.setScanIndexForward(true);


            PaginatedList<Song> result = DynamoDBManager.getInstance(this.mContext).getMapper().query(Song.class, queryExpression);

            if ( result != null){
                return result;
            }


        }catch (AmazonServiceException e ){
            this.mDynamoDBListener.onError(e.getMessage());
        }catch (AmazonClientException e){
            this.mDynamoDBListener.onError(e.getMessage());
        }

        return null;
    }
}
