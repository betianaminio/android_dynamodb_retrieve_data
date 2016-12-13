package betianaminio.dynamodb.retrievedata.models;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;


@DynamoDBTable(tableName = "Discography")
public class Album {

    private String mName;
    private String mCompay;
    private String mYear;
    private String mCover;

    @DynamoDBHashKey
    @DynamoDBIndexHashKey( attributeName = "name")
    public String getName() {return this.mName;}

    @DynamoDBAttribute( attributeName = "company")
    public String getCompay() {return this.mCompay;}

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "year-local-index", attributeName = "year")
    public String getYear() {return this.mYear;}

    @DynamoDBAttribute( attributeName = "cover")
    public String getCover() { return this.mCover;}

    public void setName(String mName) {this.mName = mName;}
    public void setCompay(String mCompay) {this.mCompay = mCompay;}
    public void setYear(String mYear) {this.mYear = mYear;}
    public void setCover(String mCover){this.mCover = mCover;}

}
