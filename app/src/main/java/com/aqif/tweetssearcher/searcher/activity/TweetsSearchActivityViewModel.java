package com.aqif.tweetssearcher.searcher.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.aqif.tweetssearcher.R;
import com.aqif.tweetssearcher.BR;

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
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.TweetsRecyclerAdapter;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.TweetsRecyclerViewModel.InjectableTweetsRecyclerViewModelField;
import com.aqif.tweetssearcher.searcher.search.Tweet;
import com.aqif.tweetssearcher.searcher.search.dagger.DaggerTweetsSearchComponent;
import com.aqif.tweetssearcher.searcher.search.viewmodel.TweetsSearchViewModel.InjectableTweetsSearchViewModelField;
import com.aqif.tweetssearcher.searcher.search.dagger.TweetsSearchComponent;
import com.aqif.tweetssearcher.searcher.search.dagger.TweetsSearchModule;
import com.aqif.tweetssearcher.searcher.search.viewmodel.observer.ITweetsSearchViewModelObserver;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/7/17.
 */

public class TweetsSearchActivityViewModel implements
        ITweetsSearchActivityViewModel,
        ITweetsSearchViewModelObserver
{



    private InjectableActivityFields mActivityFields;
    private InjectableTweetsRecyclerViewModelField mTweetsRecyclerViewModelField;
    private InjectableTweetsSearchViewModelField mTweetsSearchViewModelField;

    public TweetsSearchActivityViewModel(InjectableActivityFields activityFields,
                                         InjectableTweetsRecyclerViewModelField tweetsRecyclerViewModelField,
                                         InjectableTweetsSearchViewModelField tweetsSearchViewModelField)
    {
        mActivityFields = activityFields;
        mTweetsRecyclerViewModelField = tweetsRecyclerViewModelField;
        mTweetsSearchViewModelField = tweetsSearchViewModelField;
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


        TweetsRecyclerComponent tweetsRecyclerComponent = DaggerTweetsRecyclerComponent.builder()
                .tweetsRecyclerModule(new TweetsRecyclerModule(mActivityFields.activity, mActivityFields.tweetsRecyclerView))
                .build();
        tweetsRecyclerComponent.inject(mTweetsRecyclerViewModelField);


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
                .tweetsSearchModule(new TweetsSearchModule(searchView))
                .build();

        tweetsSearchComponent.inject(mTweetsSearchViewModelField);

        mTweetsSearchViewModelField.tweetsSearchViewModel.getTweetsSearchViewModelObservable().registerTweetsSearchViewModelObserver(this);

    }

    @Override
    public void onActivityDestroyCalled()
    {
        mTweetsSearchViewModelField.tweetsSearchViewModel.getTweetsSearchViewModelObservable().unregisterTweetsSearchViewModelObserver(this);
    }

    @Override
    public void onBackPressed()
    {

    }

    @Override
    public void onDataChanged(List<Tweet> tweets)
    {

        System.out.println("data size: "+tweets);
        mTweetsRecyclerViewModelField.tweetsRecyclerViewModel.setRecyclerViewData(tweets);
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
        @Inject public DrawerLayout drawerLayout;
        @Inject public TweetsRecyclerView tweetsRecyclerView;
    }



}
