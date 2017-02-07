package com.aqif.tweetssearcher.restapi.twitter.manager.observers;


/**
 * Created by aqifhamid on 2/5/17.
 */

public interface ITwitterApiManagerOAuthObserver
{
    void onOAuthSuccess();
    void onOAuthFailure(String message);

}
