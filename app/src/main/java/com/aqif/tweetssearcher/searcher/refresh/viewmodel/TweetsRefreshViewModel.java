package com.aqif.tweetssearcher.searcher.refresh.viewmodel;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MenuItem;

import com.aqif.tweetssearcher.searcher.refresh.model.ITweetsRefreshModel;
import com.aqif.tweetssearcher.searcher.refresh.model.ITweetsRefreshTimedEvent.ITweetsRefreshTimedEventListener;
import com.aqif.tweetssearcher.searcher.refresh.viewmodel.observer.ITweetsRefreshViewModelObservable;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/7/17.
 */

public class TweetsRefreshViewModel implements
        ITweetsRefreshViewModel,
        ITweetsRefreshTimedEventListener,
        SwipeRefreshLayout.OnRefreshListener,
        NavigationView.OnNavigationItemSelectedListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NavigationView mNavigationView;
    private ITweetsRefreshModel mTweetsRefreshModel;



    private ITweetsRefreshViewModelObservable mTweetsRefreshViewModelObservable;

    @Inject
    public TweetsRefreshViewModel(SwipeRefreshLayout swipeSwipeRefreshLayout,
                                  NavigationView navigationView,
                                  ITweetsRefreshModel tweetsRefreshModel,
                                  ITweetsRefreshViewModelObservable tweetsRecyclerViewModelObservable)
    {
        mSwipeRefreshLayout = swipeSwipeRefreshLayout;
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setEnabled(false);
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#1976d2"), Color.parseColor("#2196f3"));

        mTweetsRefreshModel = tweetsRefreshModel;
        mTweetsRefreshModel.setTweetsRefreshTimedEventListener(this);

        mNavigationView = navigationView;
        navigationView.setNavigationItemSelectedListener(this);

        mTweetsRefreshViewModelObservable = tweetsRecyclerViewModelObservable;


    }


    @Override
    public void hideLoader()
    {
        if(mSwipeRefreshLayout.isRefreshing())
        {

            mSwipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }, 100);
        }
    }

    @Override
    public ITweetsRefreshViewModelObservable getTweetsRefreshViewModelObservable()
    {
        return mTweetsRefreshViewModelObservable;
    }

    @Override
    public boolean isLoading()
    {
        return mSwipeRefreshLayout.isRefreshing();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        mTweetsRefreshModel.onDrawerOptionSelection(item.getItemId());
        mSwipeRefreshLayout.setEnabled(mTweetsRefreshModel.shouldEnableSwipeRefreshLayout());

        return true;
    }

    @Override
    public void onRefresh()
    {
        mTweetsRefreshViewModelObservable.notifyRefresh();
    }

    @Override
    public void onActivityResumeCalled()
    {
        mTweetsRefreshModel.resumeTimedEvent();
    }

    @Override
    public void onActiviyStopCalled()
    {
        mTweetsRefreshModel.stopTimedEvent();
    }

    @Override
    public void reset()
    {
        mTweetsRefreshModel.resetTimedEvent();
    }

    @Override
    public void onActiviyDestroyCalled()
    {
        mTweetsRefreshModel.setTweetsRefreshTimedEventListener(null);
        mTweetsRefreshModel.stopTimedEvent();
    }

    @Override
    public void onTimedEvent()
    {
        mSwipeRefreshLayout.setRefreshing(true);
        mTweetsRefreshViewModelObservable.notifyRefresh();
    }

    /** Injectable Fields Composer
     *
     * We cannot inject private fields. We are composing it into an object so that we can hide and inject it as well.
     *
     * */

    public static class InjectableTweetsRefreshLayoutViewModelField
    {
        @Inject
        public ITweetsRefreshViewModel tweetsRefreshViewModel;
    }

}
