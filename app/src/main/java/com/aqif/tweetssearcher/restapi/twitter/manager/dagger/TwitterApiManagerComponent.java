package com.aqif.tweetssearcher.restapi.twitter.manager.dagger;

import com.aqif.tweetssearcher.restapi.twitter.manager.ITwitterApiManager;
import com.aqif.tweetssearcher.restapi.twitter.manager.nextpageidextractor.INextPageIdExtractor;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.ITwitterApiManagerOAuthObservable;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.ITwitterApiManagerTweetsLoadObservable;
import com.aqif.tweetssearcher.restapi.twitter.oauth.dagger.TwitterOAuthApiComponent;
import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.dagger.TweetsSearchApiComponent;

import dagger.Component;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Component(
        modules = TwitterApiManagerModule.class,
        dependencies =
        {
            TwitterOAuthApiComponent.class,
            TweetsSearchApiComponent.class
        })
public interface TwitterApiManagerComponent
{
    ITwitterApiManagerOAuthObservable getTwitterApiManagerOAuthObservable();
    ITwitterApiManagerTweetsLoadObservable getTwitterApiManagerTweetsLoadObservable();
    
    INextPageIdExtractor getNextPageIdExtractor();
    ITwitterApiManager getTwitterApiManager();
}
