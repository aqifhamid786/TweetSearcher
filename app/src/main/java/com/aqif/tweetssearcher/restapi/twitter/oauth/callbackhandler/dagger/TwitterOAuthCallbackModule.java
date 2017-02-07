package com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler.dagger;

import com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler.ITwitterOAuthCallbackHandler;
import com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler.ITwitterOAuthCallbackObserver;
import com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler.TwitterOAuthCallbackHandler;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Module
public class TwitterOAuthCallbackModule
{

    @Provides
    public List<ITwitterOAuthCallbackObserver> provideTwitterOAuthCallbackObserversList()
    {
        return new ArrayList<ITwitterOAuthCallbackObserver>();
    }

    @Provides
    public ITwitterOAuthCallbackHandler provideTwitterOAuthCallbackHandler(List<ITwitterOAuthCallbackObserver> twitterOAuthCallbackObservers)
    {
        return new TwitterOAuthCallbackHandler(twitterOAuthCallbackObservers);
    }


}
