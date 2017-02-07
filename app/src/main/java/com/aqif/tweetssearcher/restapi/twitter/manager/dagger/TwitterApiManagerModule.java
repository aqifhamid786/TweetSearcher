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
import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.requesthandler.ITweetsSearchRequestHandler;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Module
public class TwitterApiManagerModule
{

    @Provides
    public ITwitterApiManagerOAuthObservable provideTwitterApiManagerOAuthObservable()
    {
        return new TwitterApiManagerOAuthObservable(new ArrayList<ITwitterApiManagerOAuthObserver>());
    }

    @Provides
    public ITwitterApiManagerTweetsLoadObservable provideTwitterApiManagerTweetsLoadObservable()
    {
        return new TwitterApiManagerTweetsLoadObservable(new ArrayList<ITwitterApiManagerTweetsLoadObserver>());
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
