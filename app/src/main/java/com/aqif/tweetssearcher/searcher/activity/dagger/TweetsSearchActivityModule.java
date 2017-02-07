package com.aqif.tweetssearcher.searcher.activity.dagger;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.aqif.tweetssearcher.R;
import com.aqif.tweetssearcher.searcher.activity.ITweetsSearchActivityViewModel;
import com.aqif.tweetssearcher.searcher.activity.TweetsSearchActivityViewModel;
import com.aqif.tweetssearcher.searcher.activity.observer.ITweetsSearchActivityObservable;
import com.aqif.tweetssearcher.searcher.activity.observer.ITweetsSearchActivityObserver;
import com.aqif.tweetssearcher.searcher.activity.observer.TweetsSearchActivityObservable;
import com.aqif.tweetssearcher.searcher.recycler.view.TweetsRecyclerView;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.TweetsRecyclerViewModel;
import com.aqif.tweetssearcher.searcher.search.viewmodel.TweetsSearchViewModel;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aqifhamid on 2/6/17.
 */

@Module
public class TweetsSearchActivityModule
{
    private AppCompatActivity mActivity;

    public TweetsSearchActivityModule(AppCompatActivity activity)
    {
        mActivity = activity;
    }

    @Provides
    AppCompatActivity provideAppCompatActivity()
    {
        return mActivity;
    }

    @Provides
    TweetsRecyclerView provideTweetsRecyclerView()
    {
        return (TweetsRecyclerView) mActivity.findViewById(R.id.recycler_view_tweets);
    }

    @Provides
    SwipeRefreshLayout provideSwipeRefreshLayout()
    {
        return (SwipeRefreshLayout) mActivity.findViewById(R.id.swipe_refresh_layout_tweets);
    }

    @Provides
    NavigationView provideNavigationView()
    {
        return (NavigationView) mActivity.findViewById(R.id.navigation_view_for_refresh_rate);
    }

    @Provides
    Toolbar provideToolbar()
    {
        return (Toolbar) mActivity.findViewById(R.id.toolbar);
    }

    @Provides
    DrawerLayout provideDrawerLayout()
    {
        return (DrawerLayout) mActivity.findViewById(R.id.drawer_layout);
    }

    @Provides
    ITweetsSearchActivityObservable provideTweetsSearchActivityObservable()
    {
        return new TweetsSearchActivityObservable(new ArrayList<ITweetsSearchActivityObserver>());
    }

    @Provides
    ITweetsSearchActivityViewModel provideTweetsSearchActivityViewModel()
    {
        return new TweetsSearchActivityViewModel(
                new TweetsSearchActivityViewModel.InjectableActivityFields(),
                new TweetsRecyclerViewModel.InjectableTweetsRecyclerViewModelField(),
                new TweetsSearchViewModel.InjectableTweetsSearchViewModelField());
    }





}
