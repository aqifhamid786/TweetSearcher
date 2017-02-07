package com.aqif.tweetssearcher.searcher.swiperefresh.viewmodel;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;

import com.aqif.tweetssearcher.searcher.recycler.viewmodel.observer.ITweetsRecyclerViewModelObservable;
import com.aqif.tweetssearcher.searcher.search.viewmodel.ITweetsSearchViewModel;
import com.aqif.tweetssearcher.searcher.swiperefresh.viewmodel.observer.ITweetsSwipeRefreshViewModelObservable;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/7/17.
 */

public class TweetsSwipeRefreshViewModel implements
        ITweetsSwipeRefreshViewModel,
        SwipeRefreshLayout.OnRefreshListener
{

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ITweetsSwipeRefreshViewModelObservable mTweetsSwipeRefreshViewModelObservable;

    @Inject
    public TweetsSwipeRefreshViewModel(SwipeRefreshLayout swipeRefreshLayout, ITweetsSwipeRefreshViewModelObservable tweetsRecyclerViewModelObservable)
    {
        mTweetsSwipeRefreshViewModelObservable = tweetsRecyclerViewModelObservable;
        mSwipeRefreshLayout = swipeRefreshLayout;
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#1976d2"), Color.parseColor("#2196f3"));
    }

    @Override
    public ITweetsSwipeRefreshViewModelObservable getTweetsSwipeRefreshViewModelObservable() {
        return mTweetsSwipeRefreshViewModelObservable;
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
    public boolean isLoading()
    {
        return mSwipeRefreshLayout.isRefreshing();
    }

    @Override
    public void onRefresh()
    {
        mTweetsSwipeRefreshViewModelObservable.notifySwipeRefresh();
    }


    /** Injectable Fields Composer
     *
     * We cannot inject private fields. We are composing it into an object so that we can hide and inject it as well.
     *
     * */

    public static class InjectableTweetsSwipeRefreshLayoutViewModelField
    {
        @Inject
        public ITweetsSwipeRefreshViewModel tweetsSwipeRefreshViewModel;
    }

}
