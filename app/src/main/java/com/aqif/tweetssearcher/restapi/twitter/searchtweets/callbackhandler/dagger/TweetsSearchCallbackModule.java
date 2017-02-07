package com.aqif.tweetssearcher.restapi.twitter.searchtweets.callbackhandler.dagger;

import com.aqif.tweetssearcher.restapi.twitter.searchtweets.callbackhandler.ITweetsSearchCallbackHandler;
import com.aqif.tweetssearcher.restapi.twitter.searchtweets.callbackhandler.ITweetsSearchCallbackObserver;
import com.aqif.tweetssearcher.restapi.twitter.searchtweets.callbackhandler.TweetsSearchCallbackHandler;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Module
public class TweetsSearchCallbackModule
{

    @Provides
    public List<ITweetsSearchCallbackObserver> provideTweetsSearchCallbackObserverList()
    {
        return new ArrayList<ITweetsSearchCallbackObserver>();
    }

    @Provides
    public ITweetsSearchCallbackHandler provideTweetsSearchCallbackHandler(List<ITweetsSearchCallbackObserver> tweetsSearchCallbackObservers)
    {
        return new TweetsSearchCallbackHandler(tweetsSearchCallbackObservers);
    }
}
