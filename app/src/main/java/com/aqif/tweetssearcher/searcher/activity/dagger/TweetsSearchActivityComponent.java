package com.aqif.tweetssearcher.searcher.activity.dagger;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.aqif.tweetssearcher.searcher.activity.TweetsSearchActivity;

import dagger.Component;


/**
 * Created by aqifhamid on 2/6/17.
 */

@Component(modules = {TweetsSearchActivityModule.class})
public interface TweetsSearchActivityComponent
{
    Activity getActivity();
    TweetsSearchActivity getTweetsSearchActivity();
    RecyclerView getTweetsRecyclerView();
    SwipeRefreshLayout getSwipeRefreshLayout();
    DrawerLayout getDrawerLayout();
    Toolbar getToolbar();
    NavigationView getNavigationView();

}
