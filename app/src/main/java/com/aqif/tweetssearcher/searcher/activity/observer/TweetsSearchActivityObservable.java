package com.aqif.tweetssearcher.searcher.activity.observer;

import java.util.List;

/**
 * Created by aqifhamid on 2/7/17.
 */

public class TweetsSearchActivityObservable implements ITweetsSearchActivityObservable
{


    List<ITweetsSearchActivityObserver> mTweetsSearchActivityObservers;

    @Override
    public void registerOnTweetsSearchActivityObserver(ITweetsSearchActivityObserver tweetsSearchActivityObserver)
    {
        if(!mTweetsSearchActivityObservers.contains(tweetsSearchActivityObserver))
        {
            mTweetsSearchActivityObservers.add(tweetsSearchActivityObserver);
        }
    }

    @Override
    public void unregisterOnTweetsSearchActivityObserver(ITweetsSearchActivityObserver tweetsSearchActivityObserver)
    {
        if(mTweetsSearchActivityObservers.contains(tweetsSearchActivityObserver))
        {
            mTweetsSearchActivityObservers.remove(tweetsSearchActivityObserver);
        }
    }

    @Override
    public void notifyActivityCreated()
    {
        for(int lop=0; lop<mTweetsSearchActivityObservers.size(); lop++)
        {
            mTweetsSearchActivityObservers.get(lop).onActivityCreated();
        }
    }

    @Override
    public void notifyMenuCreated()
    {
        for(int lop=0; lop<mTweetsSearchActivityObservers.size(); lop++)
        {
            mTweetsSearchActivityObservers.get(lop).onMenuCreated();
        }
    }
}
