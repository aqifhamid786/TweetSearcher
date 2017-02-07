package com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler.dagger;

import com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler.ITwitterOAuthCallbackHandler;
import com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler.ITwitterOAuthCallbackObserver;

import java.util.List;

import dagger.Component;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Component(modules={TwitterOAuthCallbackModule.class})
public interface TwitterOAuthCallbackComponent
{

    List<ITwitterOAuthCallbackObserver> getTwitterOAuthCallbackObserversList();
    ITwitterOAuthCallbackHandler getTwitterOAuthCallbackHandler();
}
