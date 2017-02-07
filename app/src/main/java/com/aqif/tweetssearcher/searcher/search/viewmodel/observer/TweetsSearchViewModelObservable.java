package com.aqif.tweetssearcher.searcher.search.viewmodel.observer;

import com.aqif.tweetssearcher.searcher.search.Tweet;

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
    public void notifyDataChanged(List<Tweet> tweets)
    {
        for(int lop=0; lop<mTweetsSearchViewModelObservers.size(); lop++)
        {
            mTweetsSearchViewModelObservers.get(lop).onDataChanged(tweets);
        }

    }


}
