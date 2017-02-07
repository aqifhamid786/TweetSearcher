package com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler;

/**
 * Created by aqifhamid on 2/5/17.
 */

public interface ITwitterOAuthCallbackObservable {
    void registerTwitterOAuthCallbackObserver(ITwitterOAuthCallbackObserver twitterOAuthCallbackObserver);
    void unregisterTwitterOAuthCallbackObservable(ITwitterOAuthCallbackObserver twitterOAuthCallbackObserver);
}
