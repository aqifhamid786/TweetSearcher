package com.aqif.tweetssearcher.searcher.search.observer;

import com.aqif.tweetssearcher.searcher.search.Tweet;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/5/17.
 */

public class TweetsDataChangeObservable implements ITweetsDataChangeObservable
{

    protected List<ITweetsDataChangeObserver> mTweetsDataChangeObservers;

    @Inject
    public TweetsDataChangeObservable(List<ITweetsDataChangeObserver> tweetsDataChangeObservers)
    {
        mTweetsDataChangeObservers = tweetsDataChangeObservers;
    }


    @Override
    public void registerTweetsDataChangeObserver(ITweetsDataChangeObserver tweetsDataChangeObserver)
    {
        if(!mTweetsDataChangeObservers.contains(tweetsDataChangeObserver))
        {
            mTweetsDataChangeObservers.add(tweetsDataChangeObserver);
        }
    }

    @Override
    public void unregisterTweetsDataChangeObserver(ITweetsDataChangeObserver tweetsDataChangeObserver)
    {
        if(mTweetsDataChangeObservers.contains(tweetsDataChangeObserver))
        {
            mTweetsDataChangeObservers.remove(tweetsDataChangeObserver);
        }
    }

    @Override
    public void notifyDataChanged(List<Tweet> tweets)
    {
        for(int lop=0; lop<mTweetsDataChangeObservers.size(); lop++)
        {
            mTweetsDataChangeObservers.get(lop).onDataChanged(tweets);
        }

    }


}
