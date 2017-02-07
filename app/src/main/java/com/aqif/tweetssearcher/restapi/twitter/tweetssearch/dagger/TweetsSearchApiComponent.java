package com.aqif.tweetssearcher.restapi.twitter.tweetssearch.dagger;

import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.callbackhandler.ITweetsSearchCallbackHandler;
import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.requesthandler.ITweetsSearchRequestHandler;
import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.requesthandler.ITweetsSearchRetrofitService;

import dagger.Component;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Component(modules={TweetsSearchApiModule.class})
public interface TweetsSearchApiComponent
{

    ITweetsSearchCallbackHandler getTweetsSearchCallbackHandler();
    ITweetsSearchRequestHandler getTweetsSearchRequestHandler();
    ITweetsSearchRetrofitService getTweetsSearchRetrofitService();
}
