package com.aqif.tweetssearcher.searcher.search.viewmodel.observer;

import com.aqif.tweetssearcher.searcher.recycler.model.TweetModel;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/5/17.
 */

public class TweetsSearchViewModelObservable implements ITweetsSearchViewModelObservable
{

    protected List<ITweetsSearchViewModelObserver> mTweetsSearchViewModelObservers;

    @Inject
    public TweetsSearchViewModelObservable(List<ITweetsSearchViewModelObserver> tweetsDataChangeObservers)
    {
        mTweetsSearchViewModelObservers = tweetsDataChangeObservers;
    }


    @Override
    public void registerTweetsSearchViewModelObserver(ITweetsSearchViewModelObserver tweetsDataChangeObserver)
    {
        if(!mTweetsSearchViewModelObservers.contains(tweetsDataChangeObserver))
        {
            mTweetsSearchViewModelObservers.add(tweetsDataChangeObserver);
        }
    }

    @Override
    public void unregisterTweetsSearchViewModelObserver(ITweetsSearchViewModelObserver tweetsDataChangeObserver)
    {
        if(mTweetsSearchViewModelObservers.contains(tweetsDataChangeObserver))
        {
            mTweetsSearchViewModelObservers.remove(tweetsDataChangeObserver);
        }
    }

    @Override
    public void notifyDataChanged(List<TweetModel> tweetModels, boolean isLastPage)
    {
        for(int lop=0; lop<mTweetsSearchViewModelObservers.size(); lop++)
        {
            mTweetsSearchViewModelObservers.get(lop).onDataChanged(tweetModels, isLastPage);
        }

    }

    @Override
    public void notifyClearData()
    {
        for(int lop=0; lop<mTweetsSearchViewModelObservers.size(); lop++)
        {
            mTweetsSearchViewModelObservers.get(lop).onClearData();
        }

    }

    @Override
    public void notifyFailure()
    {
        for(int lop=0; lop<mTweetsSearchViewModelObservers.size(); lop++)
        {
            mTweetsSearchViewModelObservers.get(lop).onFailure();
        }

    }


}
