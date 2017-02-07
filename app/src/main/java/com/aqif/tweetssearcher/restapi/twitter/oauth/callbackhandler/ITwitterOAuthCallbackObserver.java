package com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler;

/**
 * Created by aqifhamid on 2/5/17.
 */

public interface ITwitterOAuthCallbackObserver {
    void onTwitterOAuthSuccess(String accessToken);
    void onTwitterOAuthFailed(String message);
}
