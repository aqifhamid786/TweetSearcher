package com.aqif.tweetssearcher.restapi.twitter.searchtweets.callbackhandler;

/**
 * Created by aqifhamid on 2/5/17.
 */

public interface ITweetsSearchCallbackObservable {
    void registerTweetsSearchCallbackObserver(ITweetsSearchCallbackObserver TweetsSearchCallbackObserver);
    void unregisterTweetsSearchCallbackObservable(ITweetsSearchCallbackObserver TweetsSearchCallbackObserver);
}
