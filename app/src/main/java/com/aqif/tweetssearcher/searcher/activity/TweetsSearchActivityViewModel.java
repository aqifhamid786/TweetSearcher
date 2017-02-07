package com.aqif.tweetssearcher.searcher.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
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
import com.aqif.tweetssearcher.searcher.search.dagger.DaggerTweetsSearchComponent;
import com.aqif.tweetssearcher.searcher.search.viewmodel.TweetsSearchViewModel.InjectableTweetsSearchViewModelField;
import com.aqif.tweetssearcher.searcher.search.dagger.TweetsSearchComponent;
import com.aqif.tweetssearcher.searcher.search.dagger.TweetsSearchModule;
import com.aqif.tweetssearcher.searcher.search.viewmodel.observer.ITweetsSearchViewModelObserver;
import com.aqif.tweetssearcher.searcher.swiperefresh.dagger.DaggerTweetsSwipeRefreshLayoutComponent;
import com.aqif.tweetssearcher.searcher.swiperefresh.dagger.TweetsSwipeRefreshLayoutComponent;
import com.aqif.tweetssearcher.searcher.swiperefresh.dagger.TweetsSwipeRefreshLayoutModule;
import com.aqif.tweetssearcher.searcher.swiperefresh.viewmodel.TweetsSwipeRefreshViewModel.InjectableTweetsSwipeRefreshLayoutViewModelField;
import com.aqif.tweetssearcher.searcher.swiperefresh.viewmodel.observer.ITweetsSwipeRefreshViewModelObserver;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/7/17.
 */

public class TweetsSearchActivityViewModel implements
        ITweetsSearchActivityViewModel,
        ITweetsSearchViewModelObserver,
        ITweetsRecyclerViewModelObserver,
        ITweetsSwipeRefreshViewModelObserver
{



    private InjectableActivityFields mActivityFields;
    private InjectableTweetsRecyclerViewModelField mTweetsRecyclerViewModelField;
    private InjectableTweetsSearchViewModelField mTweetsSearchViewModelField;
    private InjectableTweetsSwipeRefreshLayoutViewModelField mTweetsSwipeRefreshLayoutViewModelField;

    public TweetsSearchActivityViewModel(InjectableActivityFields activityFields,
                                         InjectableTweetsRecyclerViewModelField tweetsRecyclerViewModelField,
                                         InjectableTweetsSearchViewModelField tweetsSearchViewModelField,
                                         InjectableTweetsSwipeRefreshLayoutViewModelField tweetsSwipeRefreshLayoutViewModelField)
    {
        mActivityFields = activityFields;
        mTweetsRecyclerViewModelField = tweetsRecyclerViewModelField;
        mTweetsSearchViewModelField = tweetsSearchViewModelField;
        mTweetsSwipeRefreshLayoutViewModelField = tweetsSwipeRefreshLayoutViewModelField;
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

        TweetsSwipeRefreshLayoutComponent tweetsSwipeRefreshLayoutComponent = DaggerTweetsSwipeRefreshLayoutComponent.builder()
                .tweetsSwipeRefreshLayoutModule(new TweetsSwipeRefreshLayoutModule(mActivityFields.swipeRefreshLayout))
                .build();
        tweetsSwipeRefreshLayoutComponent.inject(mTweetsSwipeRefreshLayoutViewModelField);
        mTweetsSwipeRefreshLayoutViewModelField.tweetsSwipeRefreshViewModel.getTweetsSwipeRefreshViewModelObservable().registerTweetsSwipeRefreshViewModelObserver(this);



        TweetsRecyclerComponent tweetsRecyclerComponent = DaggerTweetsRecyclerComponent.builder()
                .tweetsRecyclerModule(new TweetsRecyclerModule(mActivityFields.activity, mActivityFields.tweetsRecyclerView))
                .build();
        tweetsRecyclerComponent.inject(mTweetsRecyclerViewModelField);
        mTweetsRecyclerViewModelField.tweetsRecyclerViewModel.getTweetsRecyclerViewModelObserver().registerRecyclerViewModelObserver(this);


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
        if(mTweetsSwipeRefreshLayoutViewModelField.tweetsSwipeRefreshViewModel!=null)
        {
            mTweetsSwipeRefreshLayoutViewModelField.tweetsSwipeRefreshViewModel.getTweetsSwipeRefreshViewModelObservable().unregisterTweetsSwipeRefreshViewModelObserver(this);
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
    public void onBackPressed()
    {

    }


    /**********  SearchViewModel (VM) callbacks *******/

    @Override
    public void onDataChanged(List<TweetModel> tweetModels, boolean isLastpage)
    {
        mTweetsRecyclerViewModelField.tweetsRecyclerViewModel.setRecyclerViewData(tweetModels, isLastpage);
        if(mTweetsSwipeRefreshLayoutViewModelField.tweetsSwipeRefreshViewModel.isLoading())
            mTweetsSwipeRefreshLayoutViewModelField.tweetsSwipeRefreshViewModel.hideLoader();
    }

    /**********  RecyclerViewModel (VM) callbacks *******/

    @Override
    public void loadMoreTweetsData()
    {
        mTweetsSearchViewModelField.tweetsSearchViewModel.loadMoreTweets();
    }
    /**********  SwipeRefreshLayoutViewModel (VM) callbacks *******/
    @Override
    public void onSwipeRefresh()
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
    }



}
