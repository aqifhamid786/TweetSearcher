package com.aqif.tweetssearcher.restapi.twitter.tweetssearch.requesthandler;

import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.callbackhandler.ITweetsSearchCallbackObservable;

public interface ITweetsSearchRequestHandler
{
    ITweetsSearchCallbackObservable getTweetsSearchCallbackObservable();

    void searchTweets(String accessToken, String tag);
    void loadMoreTweets(String accessToken, String tag, String maxId);
    void cancelCalls();
}
