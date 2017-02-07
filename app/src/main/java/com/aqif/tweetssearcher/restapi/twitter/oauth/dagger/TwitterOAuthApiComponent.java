package com.aqif.tweetssearcher.restapi.twitter.oauth.dagger;

import com.aqif.tweetssearcher.restapi.twitter.oauth.basictoken.ITwitterBasicTokenGenerator;
import com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler.ITwitterOAuthCallbackHandler;
import com.aqif.tweetssearcher.restapi.twitter.oauth.requesthandler.ITwitterOAuthRequestHandler;
import com.aqif.tweetssearcher.restapi.twitter.oauth.requesthandler.ITwitterOAuthRetrofitService;

import dagger.Component;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Component(modules={TwitterOAuthApiModule.class})
public interface TwitterOAuthApiComponent
{
    ITwitterBasicTokenGenerator getTwitterTokenGenerator();
    ITwitterOAuthCallbackHandler getTwitterOAuthCallbackHandler();
    ITwitterOAuthRetrofitService getTwitterOAuthRetrofitService();
    ITwitterOAuthRequestHandler getTwitterOAuthHandler();
}
