package com.aqif.tweetssearcher.searcher.activity.dagger;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.aqif.tweetssearcher.R;
import com.aqif.tweetssearcher.searcher.activity.TweetsSearchActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aqifhamid on 2/6/17.
 */

@Module
public class TweetsSearchActivityModule
{
    private TweetsSearchActivity mTweetsSearchActivity;

    public TweetsSearchActivityModule(TweetsSearchActivity tweetsSearchActivity)
    {
        mTweetsSearchActivity = tweetsSearchActivity;
    }

    @Provides
    Activity provideActivity()
    {
        return mTweetsSearchActivity;
    }

    @Provides
    TweetsSearchActivity provideTweetsSearchActivity()
    {
        return mTweetsSearchActivity;
    }

    @Provides
    RecyclerView provideTweetsRecyclerView()
    {
        return (RecyclerView) mTweetsSearchActivity.findViewById(R.id.recycler_view_tweets);
    }

    @Provides
    SwipeRefreshLayout provideSwipeRefreshLayout()
    {
        return (SwipeRefreshLayout) mTweetsSearchActivity.findViewById(R.id.swipe_refresh_layout_tweets);
    }

    @Provides
    Toolbar provideToolbar()
    {
        return (Toolbar) mTweetsSearchActivity.findViewById(R.id.toolbar);
    }

    @Provides
    DrawerLayout provideDrawerLayout()
    {
        return (DrawerLayout) mTweetsSearchActivity.findViewById(R.id.drawer_layout);
    }

    @Provides
    NavigationView provideNavigationView()
    {
        return (NavigationView) mTweetsSearchActivity.findViewById(R.id.navigation_view_for_refresh_rate);
    }


}
