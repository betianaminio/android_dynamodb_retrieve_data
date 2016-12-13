package betianaminio.dynamodb.retrievedata.dao;

import android.content.Context;
import android.util.Log;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.auth.CognitoCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;



class DynamoDBManager {

    private final String AWS_ACCOUNT_ID         = "YOUR_ACCOUNT_ID";
    private final String IDENTITY_POOL_ID        = "YOUR_IDENTITY_POOL_ID";
    private final String ARN_AUTHENTICATED_ROL   = "YOUR_ARN_AUTHENTICATED_ROL";
    private final String ARN_UNAUTHENTICATED_ROL = "YOUR_ARN_UNAUTHENTICATED_ROL";

    private Context mContext = null;
    private CognitoCredentialsProvider mCredentialsProvider = null;
    private DynamoDBMapper mMapper = null;


    static DynamoDBManager s_instance = null;

    static synchronized DynamoDBManager getInstance( Context context){

        if ( s_instance == null )
            s_instance = new DynamoDBManager(context);

        return s_instance;
    }


    private DynamoDBManager(Context context){

        this.mContext = context;

        initializeCredentials();

        initializeDynamoDB();

    }

    private void initializeCredentials(){

        // initialize a credentials provider object with your Activity’s context and
        // the values from your identity pool
        this.mCredentialsProvider = new CognitoCachingCredentialsProvider(
                this.mContext,
                AWS_ACCOUNT_ID,
                IDENTITY_POOL_ID,
                ARN_AUTHENTICATED_ROL,
                ARN_UNAUTHENTICATED_ROL,
                Regions.US_EAST_1
        );

    }

    private void initializeDynamoDB(){

        try{

            AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(this.mCredentialsProvider);
            this.mMapper = new DynamoDBMapper(ddbClient);

        }catch ( AmazonClientException e){
            Log.d("DynamoDB","An error occurred while trying to initialize amazon db: " + e.getMessage());
        }
    }

    DynamoDBMapper getMapper(){ return this.mMapper; }
}
