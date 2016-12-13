package betianaminio.dynamodb.retrievedata.dao;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;

public interface IDynamoDBListener {

    void onRetreiveData( PaginatedList result );
    void onError( String message );
}
