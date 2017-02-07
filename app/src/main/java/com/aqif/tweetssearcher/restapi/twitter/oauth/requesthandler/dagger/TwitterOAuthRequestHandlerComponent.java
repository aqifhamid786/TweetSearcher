package com.aqif.tweetssearcher.restapi.twitter.oauth.requesthandler.dagger;

import com.aqif.tweetssearcher.restapi.twitter.oauth.basictoken.dagger.TwitterBasicTokenComponent;
import com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler.dagger.TwitterOAuthCallbackComponent;
import com.aqif.tweetssearcher.restapi.twitter.oauth.requesthandler.ITwitterOAuthRequestHandler;
import com.aqif.tweetssearcher.restapi.twitter.oauth.requesthandler.ITwitterOAuthRetrofitService;

import dagger.Component;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Component(
        modules={
                TwitterOAuthRequestHandlerModule.class
        },
        dependencies = {
                TwitterBasicTokenComponent.class,
                TwitterOAuthCallbackComponent.class
        })
public interface TwitterOAuthRequestHandlerComponent
{
    ITwitterOAuthRequestHandler getTwitterOAuthHandler();
    ITwitterOAuthRetrofitService getTwitterOAuthRetrofitService();
}
