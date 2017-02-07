package com.aqif.tweetssearcher.searcher.search.dagger;

import com.aqif.tweetssearcher.restapi.twitter.manager.ITwitterApiManager;
import com.aqif.tweetssearcher.restapi.twitter.manager.dagger.DaggerTwitterApiManagerComponent;
import com.aqif.tweetssearcher.searcher.search.ITweetsSearchViewModel;
import com.aqif.tweetssearcher.searcher.search.TweetsSearchViewModel;
import com.aqif.tweetssearcher.searcher.search.observer.ITweetsDataChangeObservable;
import com.aqif.tweetssearcher.searcher.search.observer.ITweetsDataChangeObserver;
import com.aqif.tweetssearcher.searcher.search.observer.TweetsDataChangeObservable;

import java.util.ArrayList;
import java.util.List;

import dagger.Component;
import dagger.Provides;

/**
 * Created by aqifhamid on 2/7/17.
 */

@Component(modules = {TweetsSearchModule.class}, dependencies = {DaggerTwitterApiManagerComponent.class})
public interface TweetsSearchComponent
{

    void inject();
    List<ITweetsDataChangeObserver> getTweetsDataChangeObserversList();
    ITweetsDataChangeObservable getTweetsDataChangeObservable();
    ITweetsSearchViewModel getTweetsSearchViewModel();
}
