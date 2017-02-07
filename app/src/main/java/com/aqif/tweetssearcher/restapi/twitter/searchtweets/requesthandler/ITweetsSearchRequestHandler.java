package com.aqif.tweetssearcher.restapi.twitter.searchtweets.requesthandler;

import com.aqif.tweetssearcher.restapi.twitter.searchtweets.callbackhandler.ITweetsSearchCallbackObservable;

public interface ITweetsSearchRequestHandler
{
    ITweetsSearchCallbackObservable getTweetsSearchCallbackObservable();

    void searchTweets(String accessToken, String tag);
    void loadMoreTweets(String accessToken, String tag, String maxId);
    void cancelCalls();
}
