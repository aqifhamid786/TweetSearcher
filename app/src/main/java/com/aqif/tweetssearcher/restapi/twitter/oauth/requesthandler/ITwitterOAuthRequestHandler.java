package com.aqif.tweetssearcher.restapi.twitter.oauth.requesthandler;

import com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler.ITwitterOAuthCallbackObservable;

public interface ITwitterOAuthRequestHandler
{
    ITwitterOAuthCallbackObservable getTwitterOAuthObservable();
    void performTwitterOAuth();
    void cancelCalls();
}
