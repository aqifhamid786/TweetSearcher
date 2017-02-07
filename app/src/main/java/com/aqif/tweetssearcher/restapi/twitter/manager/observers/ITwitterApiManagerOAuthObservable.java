package com.aqif.tweetssearcher.restapi.twitter.manager.observers;


/**
 * Created by aqifhamid on 2/5/17.
 */

public interface ITwitterApiManagerOAuthObservable
{
    void registerTwitterApiManagerOAuthObserver(ITwitterApiManagerOAuthObserver twitterApiManagerOAuthObserver);
    void unregisterTwitterApiManagerOAuthObserver(ITwitterApiManagerOAuthObserver twitterApiManagerOAuthObserver);

    void notifyOAuthSuccess();
    void notifyOAuthFailure(String message);
}
