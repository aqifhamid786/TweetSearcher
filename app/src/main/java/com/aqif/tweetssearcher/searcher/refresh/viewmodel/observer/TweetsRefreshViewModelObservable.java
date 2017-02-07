package com.aqif.tweetssearcher.searcher.refresh.viewmodel.observer;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/7/17.
 */

public class TweetsRefreshViewModelObservable implements ITweetsRefreshViewModelObservable
{

    protected List<ITweetsRefreshViewModelObserver> mTweetsRefreshViewModelObservers;

    @Inject
    public TweetsRefreshViewModelObservable(List<ITweetsRefreshViewModelObserver> tweetsRefreshViewModelObservers)
    {
        mTweetsRefreshViewModelObservers = tweetsRefreshViewModelObservers;
    }

    @Override
    public void registerTweetsRefreshViewModelObserver(ITweetsRefreshViewModelObserver tweetsRefreshViewModelObserver)
    {

        if(!mTweetsRefreshViewModelObservers.contains(tweetsRefreshViewModelObserver))
        {
            mTweetsRefreshViewModelObservers.add(tweetsRefreshViewModelObserver);
        }
    }

    @Override
    public void unregisterTweetsRefreshViewModelObserver(ITweetsRefreshViewModelObserver tweetsRefreshViewModelObserver)
    {

        if(mTweetsRefreshViewModelObservers.contains(tweetsRefreshViewModelObserver))
        {
            mTweetsRefreshViewModelObservers.remove(tweetsRefreshViewModelObserver);
        }
    }

    @Override
    public void notifyRefresh()
    {

        for(int lop=0; lop<mTweetsRefreshViewModelObservers.size(); lop++)
        {
            mTweetsRefreshViewModelObservers.get(lop).onRefresh();
        }
    }
}
