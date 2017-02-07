package com.aqif.tweetssearcher.searcher.search.model;

import com.aqif.tweetssearcher.searcher.search.Tweet;

import java.util.ArrayList;

/**
 * Created by aqifhamid on 2/7/17.
 */

public interface ITweetsSearchModel
{
    boolean searchTweets(String hashtag);
    boolean loadMoreTweets();
    void setTweetsSearchModelListener(ITweetsSearchModelListener tweetsSearchModelListener);

    interface ITweetsSearchModelListener
    {
        void OnInitialized();
        void OnTweetsSearchDataUpdated(ArrayList<Tweet> mTweets);
        void ShowErrorMessage(String error);
    }

}
