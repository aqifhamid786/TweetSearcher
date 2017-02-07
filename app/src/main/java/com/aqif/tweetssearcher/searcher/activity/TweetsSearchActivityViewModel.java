package com.aqif.tweetssearcher.searcher.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import android.widget.ProgressBar;

import com.aqif.tweetssearcher.R;

import com.aqif.tweetssearcher.restapi.twitter.manager.dagger.DaggerTwitterApiManagerComponent;
import com.aqif.tweetssearcher.restapi.twitter.manager.dagger.TwitterApiManagerComponent;
import com.aqif.tweetssearcher.restapi.twitter.oauth.dagger.DaggerTwitterOAuthApiComponent;
import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.dagger.DaggerTweetsSearchApiComponent;
import com.aqif.tweetssearcher.searcher.activity.dagger.DaggerTweetsSearchActivityComponent;
import com.aqif.tweetssearcher.searcher.activity.dagger.TweetsSearchActivityComponent;
import com.aqif.tweetssearcher.searcher.activity.dagger.TweetsSearchActivityModule;
import com.aqif.tweetssearcher.searcher.recycler.dagger.DaggerTweetsRecyclerComponent;
import com.aqif.tweetssearcher.searcher.recycler.dagger.TweetsRecyclerComponent;
import com.aqif.tweetssearcher.searcher.recycler.dagger.TweetsRecyclerModule;
import com.aqif.tweetssearcher.searcher.recycler.view.TweetsRecyclerView;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.TweetsRecyclerViewModel.InjectableTweetsRecyclerViewModelField;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.observer.ITweetsRecyclerViewModelObserver;
import com.aqif.tweetssearcher.searcher.recycler.model.TweetModel;
import com.aqif.tweetssearcher.searcher.refresh.dagger.DaggerTweetsRefreshLayoutComponent;
import com.aqif.tweetssearcher.searcher.refresh.dagger.TweetsRefreshLayoutComponent;
import com.aqif.tweetssearcher.searcher.refresh.viewmodel.TweetsRefreshViewModel.InjectableTweetsRefreshLayoutViewModelField;
import com.aqif.tweetssearcher.searcher.refresh.viewmodel.observer.ITweetsRefreshViewModelObserver;
import com.aqif.tweetssearcher.searcher.search.dagger.DaggerTweetsSearchComponent;
import com.aqif.tweetssearcher.searcher.search.viewmodel.TweetsSearchViewModel.InjectableTweetsSearchViewModelField;
import com.aqif.tweetssearcher.searcher.search.dagger.TweetsSearchComponent;
import com.aqif.tweetssearcher.searcher.search.dagger.TweetsSearchModule;
import com.aqif.tweetssearcher.searcher.search.viewmodel.observer.ITweetsSearchViewModelObserver;
import com.aqif.tweetssearcher.searcher.refresh.dagger.TweetsRefreshLayoutModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/7/17.
 */

public class TweetsSearchActivityViewModel implements
        ITweetsSearchActivityViewModel,
        ITweetsSearchViewModelObserver,
        ITweetsRecyclerViewModelObserver,
        ITweetsRefreshViewModelObserver
{



    private InjectableActivityFields mActivityFields;
    private InjectableTweetsRecyclerViewModelField mTweetsRecyclerViewModelField;
    private InjectableTweetsSearchViewModelField mTweetsSearchViewModelField;
    private InjectableTweetsRefreshLayoutViewModelField mTweetsRefreshLayoutViewModelField;

    public TweetsSearchActivityViewModel(InjectableActivityFields activityFields,
                                         InjectableTweetsRecyclerViewModelField tweetsRecyclerViewModelField,
                                         InjectableTweetsSearchViewModelField tweetsSearchViewModelField,
                                         InjectableTweetsRefreshLayoutViewModelField tweetsRefreshLayoutViewModelField)
    {
        mActivityFields = activityFields;
        mTweetsRecyclerViewModelField = tweetsRecyclerViewModelField;
        mTweetsSearchViewModelField = tweetsSearchViewModelField;
        mTweetsRefreshLayoutViewModelField = tweetsRefreshLayoutViewModelField;
    }


    @Override
    public void onActivityCreateCalled(AppCompatActivity activity)
    {

        TweetsSearchActivityComponent tweetsSearchActivityComponent = DaggerTweetsSearchActivityComponent.builder()
                                .tweetsSearchActivityModule(new TweetsSearchActivityModule(activity))
                                .build();
        tweetsSearchActivityComponent.inject(mActivityFields);

        mActivityFields.activity.setSupportActionBar(mActivityFields.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                mActivityFields.activity,
                mActivityFields.drawerLayout,
                mActivityFields.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mActivityFields.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        TweetsRefreshLayoutComponent tweetsRefreshLayoutComponent = DaggerTweetsRefreshLayoutComponent.builder()
                .tweetsRefreshLayoutModule(new TweetsRefreshLayoutModule(mActivityFields.swipeRefreshLayout, mActivityFields.navigationView))
                .build();
        tweetsRefreshLayoutComponent.inject(mTweetsRefreshLayoutViewModelField);
        mTweetsRefreshLayoutViewModelField.tweetsRefreshViewModel.getTweetsRefreshViewModelObservable().registerTweetsRefreshViewModelObserver(this);

        TweetsRecyclerComponent tweetsRecyclerComponent = DaggerTweetsRecyclerComponent.builder()
                .tweetsRecyclerModule(new TweetsRecyclerModule(mActivityFields.activity, mActivityFields.tweetsRecyclerView))
                .build();
        tweetsRecyclerComponent.inject(mTweetsRecyclerViewModelField);
        mTweetsRecyclerViewModelField.tweetsRecyclerViewModel.getTweetsRecyclerViewModelObserver().registerRecyclerViewModelObserver(this);
    }

    @Override
    public void onActivityStartCalled()
    {

    }

    @Override
    public void onActivityResumeCalled()
    {
        if(mTweetsRefreshLayoutViewModelField.tweetsRefreshViewModel!=null)
        {
            mTweetsRefreshLayoutViewModelField.tweetsRefreshViewModel.onActivityResumeCalled();
        }
    }

    @Override
    public void onActivityPauseCalled()
    {

    }

    @Override
    public void onActiviyStopCalled()
    {
        if(mTweetsRefreshLayoutViewModelField.tweetsRefreshViewModel!=null)
        {
            mTweetsRefreshLayoutViewModelField.tweetsRefreshViewModel.onActiviyStopCalled();
        }
    }

    @Override
    public void onMenuCreated(Menu menu)
    {

        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        TwitterApiManagerComponent twitterApiManagerComponent = DaggerTwitterApiManagerComponent.builder()
                .twitterOAuthApiComponent(DaggerTwitterOAuthApiComponent.builder().build())
                .tweetsSearchApiComponent(DaggerTweetsSearchApiComponent.builder().build())
                .build();

        TweetsSearchComponent tweetsSearchComponent = DaggerTweetsSearchComponent.builder()
                .twitterApiManagerComponent(twitterApiManagerComponent)
                .tweetsSearchModule(new TweetsSearchModule(searchView, mActivityFields.progressBar))
                .build();

        tweetsSearchComponent.inject(mTweetsSearchViewModelField);

        mTweetsSearchViewModelField.tweetsSearchViewModel.getTweetsSearchViewModelObservable().registerTweetsSearchViewModelObserver(this);

    }

    @Override
    public void onActivityDestroyCalled()
    {
        if(mTweetsRefreshLayoutViewModelField.tweetsRefreshViewModel!=null)
        {
            mTweetsRefreshLayoutViewModelField.tweetsRefreshViewModel.getTweetsRefreshViewModelObservable().unregisterTweetsRefreshViewModelObserver(this);
            mTweetsRefreshLayoutViewModelField.tweetsRefreshViewModel.onActiviyDestroyCalled();
        }

        if(mTweetsSearchViewModelField.tweetsSearchViewModel!=null)
        {
            mTweetsSearchViewModelField.tweetsSearchViewModel.getTweetsSearchViewModelObservable().unregisterTweetsSearchViewModelObserver(this);
        }

        if(mTweetsRecyclerViewModelField.tweetsRecyclerViewModel!=null)
        {
            mTweetsRecyclerViewModelField.tweetsRecyclerViewModel.getTweetsRecyclerViewModelObserver().unregisterRecyclerViewModelObserver(this);
        }
    }

    @Override
    public boolean onBackPressed()
    {
        DrawerLayout drawer = mActivityFields.drawerLayout;
        if (drawer!=null && drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }


    /**********  SearchViewModel (VM) callbacks *******/

    @Override
    public void onDataChanged(List<TweetModel> tweetModels, boolean isLastpage)
    {
        mTweetsRecyclerViewModelField.tweetsRecyclerViewModel.setRecyclerViewData(tweetModels, isLastpage);
        if(mTweetsRefreshLayoutViewModelField.tweetsRefreshViewModel.isLoading())
            mTweetsRefreshLayoutViewModelField.tweetsRefreshViewModel.hideLoader();
    }

    @Override
    public void onClearData()
    {
        mTweetsRecyclerViewModelField.tweetsRecyclerViewModel.setRecyclerViewData(new ArrayList<TweetModel>(), false);
        if(mTweetsRefreshLayoutViewModelField.tweetsRefreshViewModel.isLoading())
            mTweetsRefreshLayoutViewModelField.tweetsRefreshViewModel.hideLoader();
    }

    @Override
    public void onFailure()
    {
        mTweetsRecyclerViewModelField.tweetsRecyclerViewModel.onLoadDataFailed();
        if(mTweetsRefreshLayoutViewModelField.tweetsRefreshViewModel.isLoading())
            mTweetsRefreshLayoutViewModelField.tweetsRefreshViewModel.hideLoader();
    }

    /**********  RecyclerViewModel (VM) callbacks *******/

    @Override
    public void loadMoreTweetsData()
    {
        mTweetsSearchViewModelField.tweetsSearchViewModel.loadMoreTweets();
    }

    @Override
    public void loadTweets(String hashtag)
    {
        mTweetsSearchViewModelField.tweetsSearchViewModel.searchTweets(hashtag);
    }

    /**********  SwipeRefreshLayoutViewModel (VM) callbacks *******/
    @Override
    public void onRefresh()
    {
        mTweetsSearchViewModelField.tweetsSearchViewModel.reloadTweets();
    }

    /** Injectable Fields Composer
     *
     * We cannot inject private fields. Composing them into an object so that we can hide and inject it as well.
     *
     * */


    public static class InjectableActivityFields
    {
        @Inject public AppCompatActivity activity;
        @Inject public Toolbar toolbar;
        @Inject public SwipeRefreshLayout swipeRefreshLayout;
        @Inject public DrawerLayout drawerLayout;
        @Inject public ProgressBar progressBar;
        @Inject public TweetsRecyclerView tweetsRecyclerView;
        @Inject public NavigationView navigationView;
    }



}
