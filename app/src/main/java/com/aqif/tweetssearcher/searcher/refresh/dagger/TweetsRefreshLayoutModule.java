package com.aqif.tweetssearcher.searcher.refresh.dagger;

import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.SwipeRefreshLayout;

import com.aqif.tweetssearcher.searcher.refresh.model.ITweetsRefreshModel;
import com.aqif.tweetssearcher.searcher.refresh.model.TweetsRefreshModel;
import com.aqif.tweetssearcher.searcher.refresh.model.TweetsRefreshTimedEvent;
import com.aqif.tweetssearcher.searcher.refresh.viewmodel.ITweetsRefreshViewModel;
import com.aqif.tweetssearcher.searcher.refresh.viewmodel.TweetsRefreshViewModel;
import com.aqif.tweetssearcher.searcher.refresh.viewmodel.observer.ITweetsRefreshViewModelObserver;
import com.aqif.tweetssearcher.searcher.refresh.viewmodel.observer.TweetsRefreshViewModelObservable;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aqifhamid on 2/7/17.
 */

@Module
public class TweetsRefreshLayoutModule
{

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NavigationView mNavigationView;

    public TweetsRefreshLayoutModule(SwipeRefreshLayout swipeSwipeRefreshLayout, NavigationView navigationView)
    {
        mSwipeRefreshLayout = swipeSwipeRefreshLayout;
        mNavigationView = navigationView;
    }

    @Provides
    ITweetsRefreshModel provideTweetsRefreshModel()
    {
        return new TweetsRefreshModel(new Handler(), new TweetsRefreshTimedEvent());
    }

    @Provides
    ITweetsRefreshViewModel provideTweetsRefreshViewModel(ITweetsRefreshModel tweetsRefreshModel)
    {
        return new TweetsRefreshViewModel(mSwipeRefreshLayout,
                mNavigationView,
                tweetsRefreshModel,
                new TweetsRefreshViewModelObservable(new ArrayList<ITweetsRefreshViewModelObserver>()));
    }
}
