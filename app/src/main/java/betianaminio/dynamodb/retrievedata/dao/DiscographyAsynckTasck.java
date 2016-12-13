package betianaminio.dynamodb.retrievedata.dao;

import android.content.Context;
import android.os.AsyncTask;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;

import betianaminio.dynamodb.retrievedata.models.Album;


public class DiscographyAsynckTasck extends AsyncTask<Void,Void,PaginatedList<Album>> {

    private Context mContext = null;
    private IDynamoDBListener mDynamoDBListener = null;

    public DiscographyAsynckTasck(Context context, IDynamoDBListener dynamoDBListener ){

        this.mContext = context;
        this.mDynamoDBListener = dynamoDBListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(PaginatedList<Album> result) {
        super.onPostExecute(result);

        this.mDynamoDBListener.onRetreiveData(result);

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected PaginatedList<Album> doInBackground(Void... voids) {

        try {
            //DynamoDB calls go here

            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();


            PaginatedList<Album> result = DynamoDBManager.getInstance(this.mContext).getMapper().scan(Album.class, scanExpression);

            if ( result != null)
                return result;


        }catch (AmazonServiceException e ){
            this.mDynamoDBListener.onError(e.getMessage());
        }catch (AmazonClientException e){
            this.mDynamoDBListener.onError(e.getMessage());
        }

        return null;
    }
}
