package com.aqif.tweetssearcher.searcher.activity.dagger;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;

import com.aqif.tweetssearcher.searcher.activity.ITweetsSearchActivityViewModel;
import com.aqif.tweetssearcher.searcher.activity.TweetsSearchActivity;
import com.aqif.tweetssearcher.searcher.activity.TweetsSearchActivityViewModel;
import com.aqif.tweetssearcher.searcher.recycler.view.TweetsRecyclerView;

import dagger.Component;


/**
 * Created by aqifhamid on 2/6/17.
 */

@Component(modules = {TweetsSearchActivityModule.class})
public interface TweetsSearchActivityComponent
{

    void inject(TweetsSearchActivityViewModel.InjectableActivityFields fields);
    void inject(TweetsSearchActivity activty);

    AppCompatActivity provideAppCompatActivity();
    TweetsRecyclerView getTweetsRecyclerView();
    SwipeRefreshLayout getSwipeRefreshLayout();
    DrawerLayout getDrawerLayout();
    Toolbar getToolbar();
    ProgressBar getProgressBar();
    NavigationView getNavigationView();

    ITweetsSearchActivityViewModel getTweetsSearchActivityViewModel();

}
