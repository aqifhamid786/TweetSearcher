package com.aqif.tweetssearcher.searcher.swiperefresh.dagger;

import android.support.v4.widget.SwipeRefreshLayout;

import com.aqif.tweetssearcher.searcher.swiperefresh.viewmodel.ITweetsSwipeRefreshViewModel;
import com.aqif.tweetssearcher.searcher.swiperefresh.viewmodel.TweetsSwipeRefreshViewModel;
import com.aqif.tweetssearcher.searcher.swiperefresh.viewmodel.observer.ITweetsSwipeRefreshViewModelObserver;
import com.aqif.tweetssearcher.searcher.swiperefresh.viewmodel.observer.TweetsSwipeRefreshViewModelObservable;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aqifhamid on 2/7/17.
 */

@Module
public class TweetsSwipeRefreshLayoutModule
{

    private SwipeRefreshLayout mSwipeRefreshLayout;

    public TweetsSwipeRefreshLayoutModule(SwipeRefreshLayout swipeRefreshLayout)
    {
        mSwipeRefreshLayout = swipeRefreshLayout;
    }

    @Provides
    ITweetsSwipeRefreshViewModel provideTweetsSwipeRefreshViewModel()
    {
        return new TweetsSwipeRefreshViewModel(mSwipeRefreshLayout, new TweetsSwipeRefreshViewModelObservable(new ArrayList<ITweetsSwipeRefreshViewModelObserver>()));
    }
}
