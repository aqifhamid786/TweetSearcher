package com.aqif.tweetssearcher.searcher.search.model;

import com.aqif.tweetssearcher.searcher.recycler.model.TweetModel;

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
        void OnTweetsSearchDataUpdated(ArrayList<TweetModel> mTweetModels, boolean isLastPage);
        void ShowErrorMessage(String error);
    }

}
