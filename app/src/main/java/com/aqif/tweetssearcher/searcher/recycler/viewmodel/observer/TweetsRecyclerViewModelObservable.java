package com.aqif.tweetssearcher.searcher.recycler.viewmodel.observer;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/5/17.
 */

public class TweetsRecyclerViewModelObservable implements ITweetsRecyclerViewModelObservable
{

    protected List<ITweetsRecyclerViewModelObserver> mTweetsRecyclerViewModelObservers;

    @Inject
    public TweetsRecyclerViewModelObservable(List<ITweetsRecyclerViewModelObserver> tweetsRecyclerViewModelObservers)
    {
        mTweetsRecyclerViewModelObservers = tweetsRecyclerViewModelObservers;
    }


    @Override
    public void registerRecyclerViewModelObserver(ITweetsRecyclerViewModelObserver tweetsRecyclerViewModelObserver)
    {
        if(!mTweetsRecyclerViewModelObservers.contains(tweetsRecyclerViewModelObserver))
        {
            mTweetsRecyclerViewModelObservers.add(tweetsRecyclerViewModelObserver);
        }
    }

    @Override
    public void unregisterRecyclerViewModelObserver(ITweetsRecyclerViewModelObserver tweetsRecyclerViewModelObserver)
    {
        if(mTweetsRecyclerViewModelObservers.contains(tweetsRecyclerViewModelObserver))
        {
            mTweetsRecyclerViewModelObservers.remove(tweetsRecyclerViewModelObserver);
        }
    }

    @Override
    public void notifyLoadMoreTweetsData()
    {
        for(int lop=0; lop<mTweetsRecyclerViewModelObservers.size(); lop++)
        {
            mTweetsRecyclerViewModelObservers.get(lop).loadMoreTweetsData();
        }

    }


}
