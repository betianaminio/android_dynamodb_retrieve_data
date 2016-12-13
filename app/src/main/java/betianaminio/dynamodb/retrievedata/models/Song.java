package betianaminio.dynamodb.retrievedata.models;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;


@DynamoDBTable(tableName = "Song_per_album")
public class Song {

    private int mTrack;
    private String mAlbum;
    private String mSongName;


    @DynamoDBRangeKey( attributeName = "track")
    public int getTrack() {
        return mTrack;
    }

    @DynamoDBHashKey( attributeName = "album")
    public String getAlbum(){ return this.mAlbum; }

    @DynamoDBAttribute( attributeName = "name")
    public String getSongName() {
        return mSongName;
    }

    public void setTrack(int mId) {
        this.mTrack = mId;
    }

    public void setAlbum( String mAlbum ){
        this.mAlbum = mAlbum;
    }

    public void setSongName(String mSongName) {
        this.mSongName = mSongName;
    }

}
