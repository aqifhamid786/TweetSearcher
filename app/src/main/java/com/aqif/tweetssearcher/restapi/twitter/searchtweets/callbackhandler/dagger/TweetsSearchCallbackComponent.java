package com.aqif.tweetssearcher.restapi.twitter.searchtweets.callbackhandler.dagger;

import com.aqif.tweetssearcher.restapi.twitter.searchtweets.callbackhandler.ITweetsSearchCallbackHandler;
import com.aqif.tweetssearcher.restapi.twitter.searchtweets.callbackhandler.ITweetsSearchCallbackObserver;

import java.util.List;

import dagger.Component;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Component(modules={TweetsSearchCallbackModule.class})
public interface TweetsSearchCallbackComponent
{

    List<ITweetsSearchCallbackObserver> getTweetsSearchCallbackObserverList();
    ITweetsSearchCallbackHandler getTweetsSearchCallbackHandler();
}
