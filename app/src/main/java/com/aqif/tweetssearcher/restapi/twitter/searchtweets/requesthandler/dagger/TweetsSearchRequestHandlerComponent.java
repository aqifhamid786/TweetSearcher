package com.aqif.tweetssearcher.restapi.twitter.searchtweets.requesthandler.dagger;

import com.aqif.tweetssearcher.restapi.twitter.searchtweets.callbackhandler.dagger.TweetsSearchCallbackComponent;
import com.aqif.tweetssearcher.restapi.twitter.searchtweets.requesthandler.ITweetsSearchRequestHandler;
import com.aqif.tweetssearcher.restapi.twitter.searchtweets.requesthandler.ITweetsSearchRetrofitService;

import dagger.Component;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Component(
        modules={
                TweetsSearchRequestHandlerModule.class
        },
        dependencies = {
                TweetsSearchCallbackComponent.class
        })
public interface TweetsSearchRequestHandlerComponent
{
    ITweetsSearchRequestHandler getTweetsSearchRequestHandler();
    ITweetsSearchRetrofitService getTweetsSearchRetrofitService();
}
