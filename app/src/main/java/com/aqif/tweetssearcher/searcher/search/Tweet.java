package com.aqif.tweetssearcher.searcher.search;

/**
 * Created by aqifhamid on 2/5/17.
 */

public class Tweet
{
    private String mName;
    private String mUserName;
    private String mTweetText;
    private int mReTweetCount;
    private int mFavouritiesCount;

    public Tweet(String name, String userName, String tweetText, int reTweetCount, int favouritiesCount)
    {
        this.mName = name;
        this.mUserName = userName;
        this.mTweetText = tweetText;
        this.mReTweetCount = reTweetCount;
        this.mFavouritiesCount = favouritiesCount;
    }

    public String getName()
    {
        return mName;
    }

    public String getUserName()
    {
        return mUserName;
    }

    public String getTweetText()
    {
        return mTweetText;
    }

    public int getReTweetCount()
    {
        return mReTweetCount;
    }

    public int getFavouritiesCount()
    {
        return mFavouritiesCount;
    }
}
