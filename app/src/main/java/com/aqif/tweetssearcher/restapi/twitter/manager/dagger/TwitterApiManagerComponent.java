package com.aqif.tweetssearcher.restapi.twitter.manager.dagger;

import com.aqif.tweetssearcher.restapi.twitter.manager.ITwitterApiManager;
import com.aqif.tweetssearcher.restapi.twitter.manager.nextpageidextractor.INextPageIdExtractor;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.ITwitterApiManagerOAuthObservable;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.ITwitterApiManagerOAuthObserver;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.ITwitterApiManagerTweetsLoadObservable;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.ITwitterApiManagerTweetsLoadObserver;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.TwitterApiManagerOAuthObservable;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.TwitterApiManagerTweetsLoadObservable;
import com.aqif.tweetssearcher.restapi.twitter.oauth.requesthandler.dagger.TwitterOAuthRequestHandlerComponent;
import com.aqif.tweetssearcher.restapi.twitter.searchtweets.requesthandler.dagger.TweetsSearchRequestHandlerComponent;

import java.util.ArrayList;
import java.util.List;

import dagger.Component;
import dagger.Provides;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Component(
        modules = TwitterApiManagerModule.class,
        dependencies =
        {
            TwitterOAuthRequestHandlerComponent.class,
            TweetsSearchRequestHandlerComponent.class
        })
interface TwitterApiManagerComponent
{
    
    List<ITwitterApiManagerOAuthObserver> getTwitterApiManagerOAuthObserverList();
    ITwitterApiManagerOAuthObservable getTwitterApiManagerOAuthObservable();

    List<ITwitterApiManagerTweetsLoadObserver> getTwitterApiManagerTweetsLoadObserverList();
    ITwitterApiManagerTweetsLoadObservable getTwitterApiManagerTweetsLoadObservable();
    
    INextPageIdExtractor getNextPageIdExtractor();
    ITwitterApiManager getTwitterApiManager();
}
