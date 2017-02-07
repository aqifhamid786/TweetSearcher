package com.aqif.tweetssearcher.searcher.search.dagger;

import android.support.v7.widget.SearchView;
import android.widget.ProgressBar;

import com.aqif.tweetssearcher.restapi.twitter.manager.ITwitterApiManager;
import com.aqif.tweetssearcher.searcher.search.model.ITweetsSearchModel;
import com.aqif.tweetssearcher.searcher.search.model.TweetsSearchModel;
import com.aqif.tweetssearcher.searcher.search.viewmodel.ITweetsSearchViewModel;
import com.aqif.tweetssearcher.searcher.search.viewmodel.TweetsSearchViewModel;
import com.aqif.tweetssearcher.searcher.search.viewmodel.observer.ITweetsSearchViewModelObservable;
import com.aqif.tweetssearcher.searcher.search.viewmodel.observer.ITweetsSearchViewModelObserver;
import com.aqif.tweetssearcher.searcher.search.viewmodel.observer.TweetsSearchViewModelObservable;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aqifhamid on 2/7/17.
 */

@Module
public class TweetsSearchModule
{

    private SearchView mSearchView;
    private ProgressBar mProgressBar;

    public TweetsSearchModule(SearchView searchView, ProgressBar progressBar)
    {
        mSearchView = searchView;
        mProgressBar = progressBar;
    }

    @Provides
    ITweetsSearchModel provideTweetsSearchModel(ITwitterApiManager twitterApiManager)
    {
        return new TweetsSearchModel(twitterApiManager);
    }

    @Provides
    ITweetsSearchViewModelObservable provideTweetsSearchViewModelObservable()
    {
        return new TweetsSearchViewModelObservable(new ArrayList<ITweetsSearchViewModelObserver>());
    }

    @Provides
    ITweetsSearchViewModel provideTweetsSearchViewModel(ITweetsSearchViewModelObservable tweetsSearchViewModelObservable, ITweetsSearchModel tweetsSearchModel)
    {
        return new TweetsSearchViewModel(mSearchView, mProgressBar, tweetsSearchViewModelObservable, tweetsSearchModel);
    }
}
