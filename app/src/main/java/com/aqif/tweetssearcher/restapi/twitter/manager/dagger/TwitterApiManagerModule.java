package com.aqif.tweetssearcher.restapi.twitter.manager.dagger;

import com.aqif.tweetssearcher.restapi.twitter.manager.ITwitterApiManager;
import com.aqif.tweetssearcher.restapi.twitter.manager.TwitterApiManager;
import com.aqif.tweetssearcher.restapi.twitter.manager.nextpageidextractor.INextPageIdExtractor;
import com.aqif.tweetssearcher.restapi.twitter.manager.nextpageidextractor.NextPageIdExtractor;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.ITwitterApiManagerOAuthObservable;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.ITwitterApiManagerOAuthObserver;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.ITwitterApiManagerTweetsLoadObservable;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.ITwitterApiManagerTweetsLoadObserver;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.TwitterApiManagerOAuthObservable;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.TwitterApiManagerTweetsLoadObservable;
import com.aqif.tweetssearcher.restapi.twitter.oauth.requesthandler.ITwitterOAuthRequestHandler;
import com.aqif.tweetssearcher.restapi.twitter.searchtweets.requesthandler.ITweetsSearchRequestHandler;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Module
public class TwitterApiManagerModule
{
    @Provides
    public List<ITwitterApiManagerOAuthObserver> provideTwitterApiManagerOAuthObserverList()
    {
        return new ArrayList<>();
    }

    @Provides
    public ITwitterApiManagerOAuthObservable provideTwitterApiManagerOAuthObservable(List<ITwitterApiManagerOAuthObserver> twitterApiManagerOAuthObservers)
    {
        return new TwitterApiManagerOAuthObservable(twitterApiManagerOAuthObservers);
    }

    @Provides
    public List<ITwitterApiManagerTweetsLoadObserver> provideTwitterApiManagerTweetsLoadObserverList()
    {
        return new ArrayList<>();
    }

    @Provides
    public ITwitterApiManagerTweetsLoadObservable provideTwitterApiManagerTweetsLoadObservable(List<ITwitterApiManagerTweetsLoadObserver> twitterApiManagerTweetsLoadObservers)
    {
        return new TwitterApiManagerTweetsLoadObservable(twitterApiManagerTweetsLoadObservers);
    }

    @Provides
    public INextPageIdExtractor provideNextPageIdExtractor()
    {
        return new NextPageIdExtractor();
    }

    @Provides
    public ITwitterApiManager provideTwitterApiManager(ITwitterOAuthRequestHandler twitterOAuthCallbackHandler
            , ITweetsSearchRequestHandler tweetsSearchRequestHandler
            , ITwitterApiManagerOAuthObservable twitterApiManagerOAuthObservable
            , ITwitterApiManagerTweetsLoadObservable twitterApiManagerTweetsLoadObservable
            , INextPageIdExtractor nextPageIdExtractor)
    {
        return new TwitterApiManager(twitterOAuthCallbackHandler, tweetsSearchRequestHandler, twitterApiManagerOAuthObservable, twitterApiManagerTweetsLoadObservable, nextPageIdExtractor);
    }


}
