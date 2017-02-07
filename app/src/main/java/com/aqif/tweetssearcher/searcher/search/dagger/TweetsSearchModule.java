package com.aqif.tweetssearcher.searcher.search.dagger;

import com.aqif.tweetssearcher.restapi.twitter.manager.ITwitterApiManager;
import com.aqif.tweetssearcher.searcher.search.ITweetsSearchViewModel;
import com.aqif.tweetssearcher.searcher.search.TweetsSearchViewModel;
import com.aqif.tweetssearcher.searcher.search.observer.ITweetsDataChangeObservable;
import com.aqif.tweetssearcher.searcher.search.observer.ITweetsDataChangeObserver;
import com.aqif.tweetssearcher.searcher.search.observer.TweetsDataChangeObservable;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aqifhamid on 2/7/17.
 */

@Module
public class TweetsSearchModule
{


    @Provides
    List<ITweetsDataChangeObserver> provideTweetsDataChangeObserversList()
    {
        return new ArrayList<ITweetsDataChangeObserver>();
    }

    @Provides
    ITweetsDataChangeObservable provideTweetsDataChangeObservable(List<ITweetsDataChangeObserver> tweetsDataChangeObservers)
    {
        return new TweetsDataChangeObservable(tweetsDataChangeObservers);
    }

    @Provides
    ITweetsSearchViewModel provideTweetsSearchViewModel(ITweetsDataChangeObservable tweetsDataChangeObservable, ITwitterApiManager twitterApiManager)
    {
        return new TweetsSearchViewModel(tweetsDataChangeObservable, twitterApiManager);
    }
}
