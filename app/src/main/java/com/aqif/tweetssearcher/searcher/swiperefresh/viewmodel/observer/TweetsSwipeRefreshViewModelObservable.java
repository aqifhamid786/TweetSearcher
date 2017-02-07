package com.aqif.tweetssearcher.searcher.swiperefresh.viewmodel.observer;

import com.aqif.tweetssearcher.searcher.recycler.viewmodel.observer.ITweetsRecyclerViewModelObserver;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/7/17.
 */

public class TweetsSwipeRefreshViewModelObservable implements ITweetsSwipeRefreshViewModelObservable
{

    protected List<ITweetsSwipeRefreshViewModelObserver> mTweetsSwipeRefreshViewModelObservers;

    @Inject
    public TweetsSwipeRefreshViewModelObservable(List<ITweetsSwipeRefreshViewModelObserver> tweetsSwipeRefreshViewModelObservers)
    {
        mTweetsSwipeRefreshViewModelObservers = tweetsSwipeRefreshViewModelObservers;
    }

    @Override
    public void registerTweetsSwipeRefreshViewModelObserver(ITweetsSwipeRefreshViewModelObserver tweetsSwipeRefreshViewModelObserver)
    {

        if(!mTweetsSwipeRefreshViewModelObservers.contains(tweetsSwipeRefreshViewModelObserver))
        {
            mTweetsSwipeRefreshViewModelObservers.add(tweetsSwipeRefreshViewModelObserver);
        }
    }

    @Override
    public void unregisterTweetsSwipeRefreshViewModelObserver(ITweetsSwipeRefreshViewModelObserver tweetsSwipeRefreshViewModelObserver)
    {

        if(mTweetsSwipeRefreshViewModelObservers.contains(tweetsSwipeRefreshViewModelObserver))
        {
            mTweetsSwipeRefreshViewModelObservers.remove(tweetsSwipeRefreshViewModelObserver);
        }
    }

    @Override
    public void notifySwipeRefresh()
    {

        for(int lop=0; lop<mTweetsSwipeRefreshViewModelObservers.size(); lop++)
        {
            mTweetsSwipeRefreshViewModelObservers.get(lop).onSwipeRefresh();
        }
    }
}
